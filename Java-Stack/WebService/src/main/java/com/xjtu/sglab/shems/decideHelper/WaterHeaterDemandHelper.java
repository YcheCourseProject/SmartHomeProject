package com.xjtu.sglab.shems.decideHelper;

import java.sql.Time;

import com.xjtu.sglab.shems.entity.WaterHeaterDemand;

public class WaterHeaterDemandHelper {
	
	private WaterHeaterDemand waterHeaterDemand;
	
	public WaterHeaterDemandHelper(){
		
	}
	
	public WaterHeaterDemandHelper(WaterHeaterDemand waterHeaterDemand){
		this.waterHeaterDemand = waterHeaterDemand;
	}
	
	public void setWaterHeaterDemand(WaterHeaterDemand waterHeaterDemand){
		this.waterHeaterDemand = waterHeaterDemand;
	}

	
	public WaterHeaterDemand getWaterHeaterDemandEntity(){
		return this.waterHeaterDemand;
	}
	
	public String getWaterHeaterDemandStr(){
		
		Integer begin = getTime(waterHeaterDemand.getWaterHeaterRunTime());
		Integer end = getTime(waterHeaterDemand.getWaterHeaterStopTime());
		
		return new String(begin + " " + end + " " + 
				waterHeaterDemand.getWaterHeaterTemperature()+ " " + 
				waterHeaterDemand.getWaterHeaterTemperatureDelta() );
		
	}
	
	
	private Integer getTime(Time time){
		
		String str = time.toString();
		String [] s = str.split(":");
		if(s[0].charAt(0) == '0'){
			return (int) (Integer.valueOf(String.valueOf(s[0].charAt(1)))/waterHeaterDemand.getWaterHeaterDemandInternal());
		}else{
			return (int) (Integer.valueOf(s[0])/waterHeaterDemand.getWaterHeaterDemandInternal());
		}
	}
}
