package com.xjtu.sglab.shems.decideHelper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionControlDetail;
import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;

public class AirConditionControlDetailHelper {
	
	private AirConditionControlDetail airConditionControlDetail;
	
	public AirConditionControlDetailHelper(){
		
	}
	
	public AirConditionControlDetailHelper(AirConditionControlDetail airConditionControlDetail){
		
		this.airConditionControlDetail = airConditionControlDetail;
		
	}
	
	public void setAirConditionControlDetail(AirConditionControlDetail airConditionControlDetail){
		
		this.airConditionControlDetail = airConditionControlDetail;
		
	}
	
	public AirConditionControlDetail getAirConditionControlDetail(){
		return airConditionControlDetail;
	}
	
	/**
	 * 由AirConditionRealTimeDecision
	 * 生成AirConditionControlDetail
	 * @param airConditionRealTimeDecision
	 * @param temperature
	 * @return
	 * @throws ParseException
	 */
	public AirConditionControlDetail getAirConditionControlDetail(
			AirConditionRealTimeDecision airConditionRealTimeDecision, float temperature) 
					throws ParseException{
		
		AirConditionControlDetail airConditionControlDetail = 
				new AirConditionControlDetail();
		
		AirCondition airCondition = airConditionRealTimeDecision.getAirCondition();
		
		airConditionControlDetail.setAirCondition(airCondition);
		
		airConditionControlDetail.setAirConditionControlStartTime(
				airConditionRealTimeDecision.getAirConditionDecideStartTime());
		
		float rated_power = airCondition.getAirConditionRatedPower();
		float total_power = airConditionRealTimeDecision.getAirConditionDecideAverageEnergy();
		
		long time = getWorkTime(rated_power, total_power);
		
		Timestamp startTimestamp = airConditionRealTimeDecision.getAirConditionDecideStartTime();
		
		Long endMillionTime = getMillionTime(startTimestamp) + time;
		
		airConditionControlDetail.setAirConditionControlEndTime(
				new Timestamp(endMillionTime));
		
		airConditionControlDetail.setAirConditionRealTimeDecision(airConditionRealTimeDecision);
		airConditionControlDetail.setAirConditionMode(1);
		airConditionControlDetail.setAirConditionTemperature(temperature);
		
		this.airConditionControlDetail = airConditionControlDetail;
		
		return airConditionControlDetail;
		
	}
	
	/**
	 * 由额定功率以及决策得出的功率估算工作时间
	 * @param rated_power
	 * @param total_power
	 * @return
	 */
	private static long getWorkTime(float rated_power, float total_power){
		
		return  (long) ((total_power * 3600000)/rated_power);
		
	}
	
	/**
	 * 转化为毫秒
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	private static long getMillionTime(Timestamp timestamp) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	    String string = dateFormat.format(timestamp);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
	    
	    long millionSeconds = sdf.parse(string).getTime();// 毫秒
	    System.out.println("毫秒数："+millionSeconds);
		
	    return millionSeconds;
	    
	}
	

}
