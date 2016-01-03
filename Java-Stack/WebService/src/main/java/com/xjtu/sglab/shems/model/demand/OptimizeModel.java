package com.xjtu.sglab.shems.model.demand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.xjtu.sglab.shems.dao.impl.AirConditionDAO;
import com.xjtu.sglab.shems.dao.impl.WaterHeaterDAO;
import com.xjtu.sglab.shems.decideHelper.AirConditionControlDetailHelper;
import com.xjtu.sglab.shems.decideHelper.AirConditionDemandHelper;
import com.xjtu.sglab.shems.decideHelper.DailyElectricityPriceHelper;
import com.xjtu.sglab.shems.decideHelper.WaterHeaterControlDetailHelper;
import com.xjtu.sglab.shems.decideHelper.WaterHeaterDemandHelper;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionControlDetail;
import com.xjtu.sglab.shems.entity.AirConditionDemand;
import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.RealTimeDecision;
import com.xjtu.sglab.shems.entity.RealTimeDemand;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterControlDetail;
import com.xjtu.sglab.shems.entity.WaterHeaterDemand;
import com.xjtu.sglab.shems.entity.WaterHeaterRealTimeDecision;
import com.xjtu.sglab.shems.service.impl.OperateDemandService;

public class OptimizeModel{
	
	private static OptimizeModel optimize;
	
	private OptimizeModel(){
		
	}
	
	public static OptimizeModel getInstance(){
		if(optimize != null){
			return optimize;
		}else{
			return optimize = new OptimizeModel();
		}
	}
	
	/**
	 * 调用优化模型
	 * @param realTimeDemand
	 * @return
	 * @throws Exception 
	 */
	public RealTimeDecision getRealTimeDecision(
			RealTimeDemand realTimeDemand, 
			int eventBeginTime, 
			DailyElectricityPrice dailyElectricity) throws Exception{

		/**
		 * 1. 将para[] 中的数据传入优化模型中
		 * 2. 获得优化模型中的输出
		 * 3. 将优化模型中的输出封装为若干个AirConditionRealTimeDecision
		 * 			以及若干个WaterHeaterRealTimeDecision
		 * 4. 返回RealTimeDecision
		 */

		System.out.println("调用优化模块");
		
		writePara(realTimeDemand, String.valueOf(eventBeginTime), dailyElectricity);
		EventToDemandText eventToDemandText = new EventToDemandText();
		//调用模型
//		eventToDemandText.useModel();
		return getOptimizeModelResult(eventBeginTime);
		
	}
	
	/**
	 * 为优化模型准备数据
	 * @param realTimeDemand
	 * @param eventBeginTime
	 * @param dailyElectricity
	 * @throws IOException
	 */
	private void writePara(RealTimeDemand realTimeDemand, String eventBeginTime, DailyElectricityPrice dailyElectricity) throws IOException{
		//空调需求
		File Rac = new File("E:/data/Rac.txt");
		if (!Rac.exists()) {
			Rac.createNewFile();
	    }
		FileWriter fw = new FileWriter(Rac.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    AirConditionDemandHelper airConditionDemandHelper = 
	    		new AirConditionDemandHelper();
	    for(AirConditionDemand air : realTimeDemand.getAirConditionDemands()){	
	    	airConditionDemandHelper.setAirConditionDemand(air);
	    	 bw.write(airConditionDemandHelper.getAirConditionDemandStr() + "\r\n" );
	    }
	    bw.close();
	    
	    //热水器需求
	    File Rwh = new File("E:/data/Rwh.txt");
		if (!Rwh.exists()) {
			Rwh.createNewFile();
	    }
		fw = new FileWriter(Rwh.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		WaterHeaterDemandHelper waterHeaterDemandHelper = 
				new WaterHeaterDemandHelper();
		for(WaterHeaterDemand water : realTimeDemand.getWaterHeaterDemands()){
			waterHeaterDemandHelper.setWaterHeaterDemand(water);
			bw.write(waterHeaterDemandHelper.getWaterHeaterDemandStr() + "\r\n");
		}
		bw.close();
		
		//状态
		File States = new File("E:/data/States.txt");
		if (!States.exists()) {
			States.createNewFile();
	    }
		fw = new FileWriter(States.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		//26 及 30 为第一次优化所需的默认值
		String states = new String(getCurrentTime() + " 26 30");
		bw.write(states);
		bw.close();
		
		//电价
		File Price =  new File("E:/data/Pirce.txt");
		if (!Price.exists()) {
			Price.createNewFile();
	    }
		fw = new FileWriter(Price.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		StringBuilder strBuilder = new StringBuilder();
		DailyElectricityPriceHelper dailyElectricityPriceHelper = 
				new DailyElectricityPriceHelper(dailyElectricity);
		float [] price = dailyElectricityPriceHelper.getDailyElectricityPrice();
		int length = price.length;
		for(int i =0; i<length; i++){
			strBuilder.append(price[i]);
			strBuilder.append(" ");
			strBuilder.append(price[i]);
			if(i == 23){
				strBuilder.append(" ");
			}else{
				strBuilder.append( "\r\n");
			}
		}
		bw.write(strBuilder.toString());
		bw.close();
	}
	
	/**
	 * 获得优化模型结果
	 * @return
	 * @throws Exception 
	 */
	private RealTimeDecision getOptimizeModelResult(int eventBeginTime) throws Exception{
		
		Set<AirConditionRealTimeDecision> airConditionDecisionList = 
				new HashSet<AirConditionRealTimeDecision>();
		
		Set<WaterHeaterRealTimeDecision> waterHeaterDecisionlList = 
				new HashSet<WaterHeaterRealTimeDecision>();
		
		ArrayList<Float> acPowerList = new ArrayList<Float>();
		ArrayList<Float> whPowerList = new ArrayList<Float>();
		ArrayList<Float> acTemList = new ArrayList<Float>();
		ArrayList<Float> whTemList = new ArrayList<Float>();
		
		RealTimeDecision realTimeDecision = new RealTimeDecision();
		
		File file = new File("E:/data/Result_qac.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while((line = br.readLine()) != null){
			float power = Float.valueOf(line);
			acPowerList.add(power);
		}
		br.close();
		
		File Result_qwh = new File("E:/data/Result_qwh.txt");
		fr = new FileReader(Result_qwh.getAbsoluteFile());
		br = new BufferedReader(fr);
		String line1 = null;
		while((line1 = br.readLine()) != null){
			float power = Float.valueOf(line1);
			whPowerList.add(power);
		}
		br.close();
		
		File Result_Tac = new File("E:/data/Result_Tac.txt");
		fr = new FileReader(Result_Tac.getAbsoluteFile());
		br = new BufferedReader(fr);
		String line2 = null;
		while((line2 = br.readLine()) != null){
			float power = Float.valueOf(line2);
			acTemList.add(power);
		}
		br.close();
		
		File Result_Twh = new File("E:/data/Result_Twh.txt");
		fr = new FileReader(Result_Twh.getAbsoluteFile());
		br = new BufferedReader(fr);
		String line3 = null;
		while((line3 = br.readLine()) != null){
			float power = Float.valueOf(line3);
			whTemList.add(power);
		}
		br.close();
		
		airConditionDecisionList = getACdecision(acPowerList, acTemList, eventBeginTime);
		waterHeaterDecisionlList = getWHdecision(whPowerList, whTemList, eventBeginTime);
		
		realTimeDecision.setAirConditionRealTimeDecisions(airConditionDecisionList);
		realTimeDecision.setWaterHeaterRealTimeDecisions(waterHeaterDecisionlList);
		
		OperateDemandService operateDemand = new OperateDemandService();
		return operateDemand.createRealTimeDecision(realTimeDecision);
		
//		return realTimeDecision;
	}
	
	/**
	 * 格式化Timestamp
	 * @param time
	 * @return
	 */
	private Timestamp getTimestamp(int time){

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		String [] str = timestamp.toString().split(" ");
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(str[0]);
		strBuilder.append(" ");
		int hour = time/2;
		if(time%2 == 0){
			strBuilder.append(hour);
			strBuilder.append(":00:00");
		}else{
			strBuilder.append(hour);
			strBuilder.append(":30:00");
		}
		System.out.println(strBuilder.toString());
		Timestamp stamp1 = Timestamp.valueOf(strBuilder.toString());
		return stamp1;
		
	}
	
	/**
	 * 构造AirConditionRealTimeDecision对象
	 * @param acPowerList
	 * @param acTemList
	 * @param eventBeginTime
	 * @return
	 * @throws ParseException
	 */
	private Set<AirConditionRealTimeDecision> getACdecision(
			ArrayList<Float> acPowerList, 
			ArrayList<Float> acTemList,
			int eventBeginTime) throws ParseException{
		
		Set<AirConditionRealTimeDecision> airConditionDecisionList = 
				new HashSet<AirConditionRealTimeDecision>();
		
		if(acPowerList.size() > 0 && acTemList.size() > 0){
			int hour = eventBeginTime*2;
			for( int i = 0 ; i< acPowerList.size(); i++, hour++ ){
				float power = acPowerList.get(i);
				if(power > 0){
					AirConditionRealTimeDecision airConditionRealTimeDecision =
							new AirConditionRealTimeDecision();
					airConditionRealTimeDecision.setAirConditionDecideAverageEnergy(power);
					airConditionRealTimeDecision.setAirConditionDecideStartTime(getTimestamp(hour));
					airConditionRealTimeDecision.setAirConditionDecideEndTime(getTimestamp(hour+1));
					AirConditionDAO airConditionDAO = new AirConditionDAO();
					AirCondition airCondition = airConditionDAO.findById(1);
//					airCondition.setAirConditionId(1);       //默认要控制的空调的编号为1
					airConditionRealTimeDecision.setAirCondition(airCondition);
					AirConditionControlDetailHelper airConditionControlDetailHelper = 
							new AirConditionControlDetailHelper();
					AirConditionControlDetail air = airConditionControlDetailHelper
							.getAirConditionControlDetail(airConditionRealTimeDecision,acTemList.get(i));
					airConditionRealTimeDecision.setAirConditionControlDetail(air);
					airConditionDecisionList.add(airConditionRealTimeDecision);
				
				}				
			}
			return airConditionDecisionList;
		}else{
			return null;
		}	
	}
	
	/**
	 * 构造WaterHeaterRealTimeDecision对象
	 * @param whPowerList
	 * @param whTemList
	 * @param eventBeginTime
	 * @return
	 * @throws ParseException
	 */
	private Set<WaterHeaterRealTimeDecision> getWHdecision(
			ArrayList<Float> whPowerList, 
			ArrayList<Float> whTemList,
			int eventBeginTime) throws ParseException{
		
		Set<WaterHeaterRealTimeDecision> waterHeaterDecisionlList = 
				new HashSet<WaterHeaterRealTimeDecision>();
		
		if((whPowerList.size() > 0) && (whTemList.size() > 0) ){
			
			int hour = eventBeginTime*2;
			for(int i = 0; i<whPowerList.size(); i++){
				float power = whPowerList.get(i);
				if(power > 0){
					WaterHeaterRealTimeDecision waterHeaterRealTimeDecision = 
							new WaterHeaterRealTimeDecision();
					waterHeaterRealTimeDecision.setWaterHeaterConsumeAverageEnergy(power);
					waterHeaterRealTimeDecision.setWaterHeaterRealStartTime(getTimestamp(hour));
					waterHeaterRealTimeDecision.setWaterHeaterRealEndTime(getTimestamp(hour+1));
					WaterHeater waterHeater = new WaterHeater();
					WaterHeaterDAO waterHeaterDAO = new WaterHeaterDAO();
					waterHeater = waterHeaterDAO.findById(1);
//					waterHeater.setWaterHeaterId(1);
					waterHeaterRealTimeDecision.setWaterHeater(waterHeater);
					WaterHeaterControlDetailHelper waterHeaterControlDetailHelper = 
							new WaterHeaterControlDetailHelper();
					WaterHeaterControlDetail water = waterHeaterControlDetailHelper
							.getWaterHeaterControlDetail(waterHeaterRealTimeDecision,whTemList.get(i));
					waterHeaterRealTimeDecision.setWaterHeaterControlDetail(water);
					waterHeaterDecisionlList.add(waterHeaterRealTimeDecision);	
				}
			}
			return waterHeaterDecisionlList;
			
		}else{
			return null;
		}

	}
	
	private int getCurrentTime(){
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		if(minute > 40){
			return (hour+1)*2;
		}else if(minute >20){
			return hour*2 + 1;
		}else{
			return hour*2;
		}
		
		
	}
	
	
}