package com.xjtu.sglab.shems.decideHelper;

import java.sql.Time;

import com.xjtu.sglab.shems.entity.AirConditionDemand;

public class AirConditionDemandHelper {
	
	private AirConditionDemand airConditionDemand;
	
	public AirConditionDemandHelper(){
		
	}
	
	public AirConditionDemandHelper(AirConditionDemand airConditionDemand){
		
		this.airConditionDemand = airConditionDemand;
		
	}
	
	public void setAirConditionDemand(AirConditionDemand airConditionDemand){
		
		this.airConditionDemand = airConditionDemand;
		
	}
	
	public AirConditionDemand getAirConditionDemand(){
		return this.airConditionDemand;
	}
	
	public String getAirConditionDemandStr(){
		
		Integer begin = getTime(airConditionDemand.getAirConditionRunTime());
		Integer end = getTime(airConditionDemand.getAirCondtionStopTime());
		
		return new String(begin + " " + end + " " + 
				airConditionDemand.getAirConditionTemperature()+ " " + 
				airConditionDemand.getAirConditionTemperatureDelta());
		
	}
	
	private Integer getTime(Time time){
		
		String str = time.toString();
		String [] s = str.split(":");
		if(s[0].charAt(0) == '0'){
			return (int) (Integer.valueOf(String.valueOf(s[0].charAt(1)))/airConditionDemand.getAirConditionDemandInternal());
		}else{
			return (int) (Integer.valueOf(s[0])/airConditionDemand.getAirConditionDemandInternal());
		}
	}
	

}
