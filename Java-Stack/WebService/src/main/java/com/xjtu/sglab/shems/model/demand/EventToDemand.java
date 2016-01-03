package com.xjtu.sglab.shems.model.demand;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.AirConditionControlDetail;
import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.AirConditionStatus;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.GpsInfoToDemand;
import com.xjtu.sglab.shems.entity.LocationType;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.RealTimeDecision;
import com.xjtu.sglab.shems.entity.RealTimeDemand;
import com.xjtu.sglab.shems.entity.SocialActivityToDemand;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.WaterHeaterControlDetail;
import com.xjtu.sglab.shems.entity.WaterHeaterRealTimeDecision;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.entity.WearableInfoToDemand;
import com.xjtu.sglab.shems.model.demand.ServerTimer.TimerType;
import com.xjtu.sglab.shems.service.impl.OperateApplianceService;
import com.xjtu.sglab.shems.service.impl.OperateDemandService;
import com.xjtu.sglab.shems.service.impl.OperateGpsService;
import com.xjtu.sglab.shems.service.impl.OperateWearableService;

public class EventToDemand {
	
	private static final String SPORTS = "运动";
	private static final String MEAL = "吃饭";
	private static final String DELAY = "延迟";
	
	private static final String RUNNING = "running";
	private static final String JOGGING = "jogging";
	private static final String IDLE = "idle";
	private static final String UNKOWN = "knwon";
	
	private static final String  GYM = "球场";
	private static final String RESTAURANT = "饭馆";
	private static final String HOSPITAL = "医院";
	private static final String SCHOOL = "学校";
	private static final String HOME = "家";
	
	/**----------DETECT_INTERVAL-----------**/
	/**----检测是否正在运动---------------------**/
	/**----例如：社交上表示15:00要打球------------**/
	/**--那么在15:00+DETECT_INTERVEL时刻检验测**/
	/**--具体检测内容在detectStatus中描述------**/
	private static final int DETECT_INTERVAL = 20000;
	
	private static ArrayList<ServerTimer> timerList  =  new ArrayList<ServerTimer>();
	
	private static ArrayList<RealTimeDecision> realTimeDecisionList = new ArrayList<RealTimeDecision>();
	
	
	/**
	 * 处理社交信息
	 * @param socialInfo
	 * @throws Exception 
	 */
	public static void handleSocialInfo(SocialInfo socialInfo) throws Exception{
		
		ActivityType activityType = socialInfo.getActivityType();
		String activity = activityType.getActivityType();
		
		if(activity.equals(SPORTS)){
		
			/**---------------处理时间--------------**/
			/**-------运动则直接查表--------------**/
			/**-------设置检测定时器-------------**/
			/**------------------------------------**/
			
			SocialActivityToDemand socialActivityToDemand = new SocialActivityToDemand();
			socialActivityToDemand.setActivityType(activityType);
			
			
			String beginTime = socialInfo.getStartTime();
			
			if(beginTime != null){
				
				//不考虑非正式格式的时间如“下午”， 只考虑正式格式的时间 YY-MM-DD HH-MM-SS				
				socialActivityToDemand.setSocialActivityBeginTime(getTime(beginTime));
				
			}else{
				
				//默认运动时间----------可加入修正模块
				socialActivityToDemand.setSocialActivityBeginTime(new String("15"));
				
			}
			
			//查数据库得realTimeDemand
			OperateDemandService operateDemandService = 
					new OperateDemandService();
			RealTimeDemand realTimeDemand = operateDemandService.createRealTimeDemand(socialActivityToDemand);
			
			//初始化优化模型
			OptimizeModel optimizeModel = OptimizeModel.getInstance();
			OperateDemandService operateDemand = new OperateDemandService();
			
			//获得DailyElectricityPrice实体------------记录着当天24小时的电价
			DailyElectricityPrice dailyElectricity = operateDemand.queryPrice();
			
			/**----------运行优化模型 ---------------**/
			/**----优化模型构造出RealTimeDecision---**/
			/**----先将RealTimeDecision插入数据库后返回**/
			RealTimeDecision realTimeDecision = optimizeModel.getRealTimeDecision(
					realTimeDemand,
					Integer.valueOf(getTime(beginTime)),
					dailyElectricity);  
			operateDemand.updateRealTimeDecision(realTimeDecision);
			
			realTimeDecisionList.add(realTimeDecision);
			
			//设置detect定时器------检测是否正在运动
			ServerTimer timer = new ServerTimer(0, TimerType.DETECT);
			timerList.add(timer);
			//设置定时器的检测时间
			Date date = new Date(System.currentTimeMillis() + DETECT_INTERVAL);			
			ServerTimerTask serverTimerTask = new ServerTimerTask(realTimeDecision.getRealTimeDecisionId());
			timer.schedule(serverTimerTask, date);
			
		}else if(activity.equals(MEAL)){
			
			/**----------------------------------------------**/
			/**--------吃饭则检测是否有control定时器----**/
			/**-------如果检测到有control定时器， 就撤销control定时器-----**/
			/**-------如果没有，则不做任何操作----------------------**/
			/**----------------------------------------------**/
			
			if(timerList != null && timerList.size() > 0){
				for(ServerTimer serverTimer : timerList){
					if(serverTimer.getTimerType() == TimerType.CONTROL_DETAIL){
						serverTimer.cancel();
					}
				}
			}
			
			
		}else if(activity.equals(DELAY)){

			/**----------------------------------------------**/
			/**--------延迟则检测是否有control定时器----**/
			/**-------如果检测到有control定时器， 就撤销control定时器-----**/
			/**-------如果没有，则不做任何操作----------------------**/
			/**----------------------------------------------**/
			
			if(timerList != null && timerList.size() > 0 ){
				for(ServerTimer serverTimer : timerList){
					if(serverTimer.getTimerType() == TimerType.CONTROL_DETAIL){
						serverTimer.cancel();
					}
				}
			}
			
		}
	}
	
//	/**
//	 * 处理可穿戴设备信息
//	 * @param wearableDeviceInfo
//	 * @throws Exception 
//	 */
//	public static void handleWearableDeviceInfo(WearableDeviceInfo wearableDeviceInfo) throws Exception{
//		
//		MovingStatus movingStatus  = wearableDeviceInfo.getMovingStatus();
//		String status = movingStatus.getMovingType();
//		float heart_rate = wearableDeviceInfo.getHeartRate();
//		float body_temperature = wearableDeviceInfo.getBodyTemperature();
//		
//		if(status.equals(RUNNING)){
//			
//			/**-------------------------------------------------------**/
//			/**--------检测是否在家---------------------------------------**/
//			/**--------如果在家，不做任何操作--------------------------------**/
//			/**------- 如果不在，查表， 设置control定时器----------------------**/
//			/**--------因为Wearable是实时信息，所以不需要detece定时器-----------**/
//			/**------------------------------------------------------**/
//			
//			if(!isAtHome()){
//				
//				WearableInfoToDemand wearableInfoDemand = 
//						new WearableInfoToDemand();
//				
//				wearableInfoDemand.setMovingStatus(movingStatus);
//				wearableInfoDemand.setHeartRate(heart_rate);
//				wearableInfoDemand.setBodyTemperature(body_temperature);
//				
//				/**类似以后socialActivityToDemand， 通过heart_rate、
//				 * moveing_status 以及 body_temperature
//				 * 查出对应的需求**/
//				
//				OperateDemandService operateDemandService = 
//						new OperateDemandService();
//				
////				重载一下createRealTimeDemand 方法
////				RealTimeDemand realTimeDemand = 
////						operateDemandService.createRealTimeDemand(wearableInfoDemand);
//				
//				//为测试用
//				RealTimeDemand realTimeDemand = new RealTimeDemand();
//				
//				//初始化优化模型
//				OptimizeModel optimizeModel = OptimizeModel.getInstance();
//				OperateDemandService operateDemand = new OperateDemandService();
//				
//				//获得DailyElectricityPrice实体------------记录着当天24小时的电价
//				DailyElectricityPrice dailyElectricity = operateDemand.queryPrice();
//				
//				/**----------运行优化模型 ---------------**/
//				/**----优化模型构造出RealTimeDecision---**/
//				/**----先将RealTimeDecision插入数据库后返回**/
//				RealTimeDecision realTimeDecision = optimizeModel.getRealTimeDecision(
//						realTimeDemand,
//						Integer.valueOf(getTime(beginTime)),
//						dailyElectricity);  
//				
//				realTimeDecisionList.add(realTimeDecision);
//				
//				
//				if(realTimeDecision != null){
//					
//					if(realTimeDecision.getAirConditionRealTimeDecisions() != null ){
//						//启动空调控制定时器
//						for(AirConditionRealTimeDecision airConditionRealTimeDecision : realTimeDecision.getAirConditionRealTimeDecisions()){
//							
//							AirConditionControlDetail airConditionControlDetail;
//							airConditionControlDetail = airConditionRealTimeDecision.getAirConditionControlDetail();
//							ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
//							timerList.add(timer);
//							
////							timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
////									airConditionControlDetail.getAirConditionControlStartTime());
//							
//							//为了检测方便--将时间设置为立刻
//							timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
//									new Date(System.currentTimeMillis() + 4000));
//							
//						}
//					}
//					
//					if(realTimeDecision.getWaterHeaterRealTimeDecisions() != null){
//						
//						//启动热水器控制定时器
//						for(WaterHeaterRealTimeDecision waterHeaterRealTimeDecision : realTimeDecision.getWaterHeaterRealTimeDecisions()){
//							
//							WaterHeaterControlDetail waterHeaterControlDetail;
//							waterHeaterControlDetail = waterHeaterRealTimeDecision.getWaterHeaterControlDetail();
//							
//							ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
//							timerList.add(timer);
//							
//							timer.schedule(new WaterHeaterControlDetailTimerTask(waterHeaterControlDetail), 
//									waterHeaterControlDetail.getWaterHeaterStartTime());
//
//						}
//					}
//				
//				}
//			
//			}
//			
//			
//		}else{
//			
//			/**-------------------不做任何处理------------------------------------**/
//			
//		}
//	}
//		
//		
//	
//	
//	/**
//	 * 处理Gps信息
//	 * @param gpsInfo
//	 */
//	public static void handleGpsInfo(GpsInfo gpsInfo){
//		
//		LocationType locationType = gpsInfo.getLocationType();
//		String location = locationType.getLocType();
//		float distance = gpsInfo.getDistanceFromHome();
//		
//		if(location.equals(GYM)){
//			
//			/**---------------处理时间--------------**/
//			/**---------运动则直接查表------------**/
//			/**--------设置control定时器---------**/
//			/**------------------------------------**/
//			
//			GpsInfoToDemand gpsInfoTodemand = 
//					new GpsInfoToDemand();
//			
//			gpsInfoTodemand.setLocationType(locationType);
//			gpsInfoTodemand.setDistanceFromHome(distance);
//			
//			OperateDemandService operateDemandService = 
//					new OperateDemandService();
//			
////			重载一下createRealTimeDemand 方法
////			RealTimeDemand realTimeDemand = 
////					operateDemandService.createRealTimeDemand(gpsInfoTodemand);
//			
//			//为测试用
//			RealTimeDemand realTimeDemand = new RealTimeDemand();
//			
//			//初始化优化模型
//			OptimizeModel optimizeModel = OptimizeModel.getInstance();
//			OperateDemandService operateDemand = new OperateDemandService();
//			
//			//获得DailyElectricityPrice实体------------记录着当天24小时的电价
//			DailyElectricityPrice dailyElectricity = operateDemand.queryPrice();
//			
//			/**----------运行优化模型 ---------------**/
//			/**----优化模型构造出RealTimeDecision---**/
//			/**----先将RealTimeDecision插入数据库后返回**/
//			RealTimeDecision realTimeDecision = optimizeModel.getRealTimeDecision(
//					realTimeDemand,
//					Integer.valueOf(getTime(beginTime)),
//					dailyElectricity);  
//			
//			realTimeDecisionList.add(realTimeDecision);
//			
//			if(realTimeDecision != null){
//				
//				if(realTimeDecision.getAirConditionRealTimeDecisions() != null ){
//					//启动空调控制定时器
//					for(AirConditionRealTimeDecision airConditionRealTimeDecision : realTimeDecision.getAirConditionRealTimeDecisions()){
//						
//						AirConditionControlDetail airConditionControlDetail;
//						airConditionControlDetail = airConditionRealTimeDecision.getAirConditionControlDetail();
//						ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
//						timerList.add(timer);
//						
////						timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
////								airConditionControlDetail.getAirConditionControlStartTime());
//						
//						//为了检测方便--将时间设置为立刻
//						timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
//								new Date(System.currentTimeMillis() + 4000));
//						
//					}
//				}
//				
//				if(realTimeDecision.getWaterHeaterRealTimeDecisions() != null){
//					
//					//启动热水器控制定时器
//					for(WaterHeaterRealTimeDecision waterHeaterRealTimeDecision : realTimeDecision.getWaterHeaterRealTimeDecisions()){
//						
//						WaterHeaterControlDetail waterHeaterControlDetail;
//						waterHeaterControlDetail = waterHeaterRealTimeDecision.getWaterHeaterControlDetail();
//						
//						ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
//						timerList.add(timer);
//						
//						timer.schedule(new WaterHeaterControlDetailTimerTask(waterHeaterControlDetail), 
//								waterHeaterControlDetail.getWaterHeaterStartTime());
//
//					}
//				}
//			
//			}
//			
//		}else if(location.equals(RESTAURANT) || location.equals(HOSPITAL) || location.equals(SCHOOL)){
//			
//			/**----------------------------------------------**/
//			/**-------检测是否有control定时器----**/
//			/**-------如果检测到有control定时器， 就撤销control定时器-----**/
//			/**-------如果没有，则不做任何操作----------------------**/
//			/**----------------------------------------------**/
//			
//			if(timerList != null && timerList.size() > 0 ){
//				for(ServerTimer serverTimer : timerList){
//					if(serverTimer.getTimerType() == TimerType.CONTROL_DETAIL){
//						serverTimer.cancel();
//					}
//				}
//			}
//			
//		}
//		
//	}
	
	
	
	/**-----------检测定时器要执行的内容---------------**/
	/**---一个检测定时器对应一个RealTimeDecision-------**/
	static class ServerTimerTask extends TimerTask{
		
		int realTimeDecisionId;
		
		ServerTimerTask(){
			super();
		}
		
		ServerTimerTask(int id){
			realTimeDecisionId = id;
		}

		@Override
		public void run() {
			
			System.out.println("开始工作");
			
			/**---------------------------------------------**/
			/**检查是否用户是否正在运动----------------**/
			/**根据场所以及运动状态---------------------**/
			/**---------------------------------------------**/
			
			
			
			try {
				if(detectStatus()){
					
					RealTimeDecision realTimeDecision = null;
					
					for(RealTimeDecision r : realTimeDecisionList){
						
						if(r.getRealTimeDecisionId() == realTimeDecisionId){
							realTimeDecision = r;
							break;
						}

					}
					
					if(realTimeDecision != null){
						
						if(realTimeDecision.getAirConditionRealTimeDecisions() != null){
							//启动空调控制定时器
							for(AirConditionRealTimeDecision airConditionRealTimeDecision : realTimeDecision.getAirConditionRealTimeDecisions()){
								
								AirConditionControlDetail airConditionControlDetail;
								airConditionControlDetail = airConditionRealTimeDecision.getAirConditionControlDetail();
								ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
								timerList.add(timer);
								System.out.println("启动空调定时器");
//								timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
//										airConditionControlDetail.getAirConditionControlStartTime());
								
								//为了检测方便--将时间设置为立刻
								timer.schedule(new AirConditionControlDetailTimerTask(airConditionControlDetail), 
										new Date(System.currentTimeMillis() + 4000));
								
							}
						}
						
						if(realTimeDecision.getWaterHeaterRealTimeDecisions() != null){
							
							//启动热水器控制定时器
							for(WaterHeaterRealTimeDecision waterHeaterRealTimeDecision : realTimeDecision.getWaterHeaterRealTimeDecisions()){
								
								WaterHeaterControlDetail waterHeaterControlDetail;
								waterHeaterControlDetail = waterHeaterRealTimeDecision.getWaterHeaterControlDetail();
								
								ServerTimer timer = new ServerTimer(0, TimerType.CONTROL_DETAIL);
								timerList.add(timer);
								
								timer.schedule(new WaterHeaterControlDetailTimerTask(waterHeaterControlDetail), 
										waterHeaterControlDetail.getWaterHeaterStartTime());

							}
						}
						
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			
		}
		
	}
	
	/**
	 * 将空调决策信息放置于status中
	 * 家庭网关会轮询status看是否有控制信息
	 * @author 家琪
	 *
	 */
	static class AirConditionControlDetailTimerTask extends TimerTask{
		
		AirConditionControlDetail airConditionControlDetail;
		
		int RealDecisionId;
		
		AirConditionControlDetailTimerTask(){
			super();
		}
		
		AirConditionControlDetailTimerTask(AirConditionControlDetail air){
			airConditionControlDetail = air;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println("将空调控制决策放入status");
			
			OperateApplianceService operateApplianceService =
					new OperateApplianceService();
			try {
				setToAirConditionStatus(airConditionControlDetail);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			
		}
		
	}
	
	/**
	 * 将热水器决策信息放置于status中
	 * @author 家琪
	 */
	static class WaterHeaterControlDetailTimerTask extends TimerTask{
		
		WaterHeaterControlDetail waterHeaterControlDetail;
		
		WaterHeaterControlDetailTimerTask(){
			super();
		}
		
		WaterHeaterControlDetailTimerTask(WaterHeaterControlDetail water){
			this.waterHeaterControlDetail = water;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println("将热水器控制决策放入status");
			setToWaterHeaterStatus(waterHeaterControlDetail);
			
			
		}
		
	}
	
	/**
	 * 对时间进行简单处理
	 * @param time
	 * @return
	 */
	private static String getTime(String time){
		String [] str = time.toString().split(" ");
		System.out.println(str[1]);
		String [] s = str[1].split(":");
		if(s[0].charAt(0) == '0'){
			return String.valueOf(s[0].charAt((1)));
		}else{
			return s[0];
		}
	}
	
	/**
	 * 转化为毫秒
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	private static long getMillionTime(String time) throws ParseException{
		
		Timestamp timestamp = Timestamp.valueOf(time);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	    String string = dateFormat.format(timestamp);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
	    
	    long millionSeconds = sdf.parse(string).getTime();// 毫秒
	    System.out.println("毫秒数："+millionSeconds);
		
	    return millionSeconds;
	    
	}
	
	/**
	 * 检验用户身体状态
	 * 手环以及Gps的级别比社交信息高
	 * 所以检测的时候，检测用户是否正在运动或者用户所处的场所是否在球场
	 * 若是， 则表明用户正在打球，则正是执行决策
	 * 否则取消需求
	 * 
	 * 为了测试方便，一直返回true
	 * @return
	 * @throws Exception
	 */
	private static boolean detectStatus() throws Exception{
		
//		OperateGpsService operateGpsService = new OperateGpsService();	
//		OperateWearableService operateWearableService = new OperateWearableService();
//		
//		GpsInfo gpsInfo = operateGpsService.queryGpsInfo();
//		
//		LocationType locationType = gpsInfo.getLocationType();
//		
//		WearableDeviceInfo wearable = operateWearableService.queryWearableInfo();
//		
//		MovingStatus movingStatus = wearable.getMovingStatus();
//		
//		if(locationType.getLocType() != GYM || movingStatus.getMovingType() != RUNNING ){
//			
//			return false;
//			
//		}
		
		return true;
		
	}
	
	/**
	 * 设置airConditionStatus
	 * @param airConditionControlDetail
	 * @throws Exception
	 */
	private static void setToAirConditionStatus(
			AirConditionControlDetail airConditionControlDetail) throws Exception{
		
		System.out.println("setToAirConditionStatus");
		
		AirConditionStatus airConditionStatus = new AirConditionStatus();
		
		float indoorTemperature = getIndoorTemperature();
		float targetTemperature = airConditionControlDetail.getAirConditionTemperature();
		
		if(indoorTemperature >= targetTemperature){
			airConditionStatus.setAirConditionMode(1);
		}else{
			airConditionStatus.setAirConditionMode(3);
		}
		
		airConditionStatus.setAirCondition(airConditionControlDetail.getAirCondition());
		airConditionStatus.setAirConditionTemperature(airConditionControlDetail.getAirConditionTemperature());
		airConditionStatus.setIsControlledByUser(false);
		airConditionStatus.setIsAlreadyControlled(false);
		airConditionStatus.setAirConditionStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		
		OperateApplianceService operateApplianceService = 
				new OperateApplianceService();
		
		operateApplianceService.saveAirConditionInfo(airConditionStatus);
		
	}
	
	private static void setToWaterHeaterStatus(WaterHeaterControlDetail waterHeaterControlDetail){
		
		System.out.println("setToWaterHeaterStatus");
		
	}
	
	
	/**
	 * 获得室内温度
	 * @return
	 */
	private static float getIndoorTemperature(){
		
		return 30;
		
	}
	
	
	/**
	 * 判断是否在家
	 * @return
	 * @throws Exception
	 */
	private static boolean isAtHome() throws Exception{
		
		OperateGpsService operateGpsService = new OperateGpsService();	
		OperateWearableService operateWearableService = new OperateWearableService();
		
		GpsInfo gpsInfo = operateGpsService.queryGpsInfo();
		
		LocationType locationType = gpsInfo.getLocationType();
		
		if(locationType.getLocType() != HOME){
			
			return false;
			
		}

		return true;
	}
	

}
