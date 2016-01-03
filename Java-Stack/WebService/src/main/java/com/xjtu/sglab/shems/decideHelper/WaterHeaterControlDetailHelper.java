package com.xjtu.sglab.shems.decideHelper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterControlDetail;
import com.xjtu.sglab.shems.entity.WaterHeaterRealTimeDecision;

public class WaterHeaterControlDetailHelper {
	
	private WaterHeaterControlDetail waterHeaterControlDetail;
	
	public WaterHeaterControlDetailHelper(){
		
	}
	
	public WaterHeaterControlDetailHelper(WaterHeaterControlDetail waterHeaterControlDetail){
		
		this.waterHeaterControlDetail = waterHeaterControlDetail;
		
	}
	
	public void setWaterHeaterControlDetail(WaterHeaterControlDetail waterHeaterControlDetail){
		this.waterHeaterControlDetail = waterHeaterControlDetail;
	}
	
	public WaterHeaterControlDetail getWaterHeaterControlDetail(){
		return waterHeaterControlDetail;
	}
	
	public WaterHeaterControlDetail getWaterHeaterControlDetail(
			WaterHeaterRealTimeDecision waterHeaterRealTimeDecision,
			float temperature) throws ParseException{
		
		WaterHeaterControlDetail waterHeaterControlDetail1 = 
				new WaterHeaterControlDetail();
		
		WaterHeater waterHeater = waterHeaterRealTimeDecision.getWaterHeater();
		
		waterHeaterControlDetail1.setWaterHeater(waterHeater);
		
		waterHeaterControlDetail1.setWaterHeaterStartTime(
				waterHeaterRealTimeDecision.getWaterHeaterRealStartTime());
		
		float rated_power = waterHeater.getWaterHeaterRatedPower();
		float total_power = waterHeaterRealTimeDecision.getWaterHeaterConsumeAverageEnergy();
		
		long time = getWorkTime(rated_power, total_power);
		
		Timestamp timestamp = waterHeaterRealTimeDecision.getWaterHeaterRealEndTime();
		
		long endMillionTime = getMillionTime(timestamp) + time;
		
		waterHeaterControlDetail1.setWaterHeaterEndTime(
				new Timestamp(endMillionTime));
		
		waterHeaterControlDetail1.setWaterTemperature(temperature);
		
		waterHeaterControlDetail1.setWaterHeaterRealTimeDecision(waterHeaterRealTimeDecision);
		
		return waterHeaterControlDetail1;
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
