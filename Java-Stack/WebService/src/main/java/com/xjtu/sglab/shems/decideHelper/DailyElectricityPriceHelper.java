package com.xjtu.sglab.shems.decideHelper;

import com.xjtu.sglab.shems.entity.DailyElectricityPrice;

public class DailyElectricityPriceHelper {
	
	private DailyElectricityPrice dailyElectricityPrice;
	
	public DailyElectricityPriceHelper(DailyElectricityPrice dailyElectricityPrice){
		
		this.dailyElectricityPrice = dailyElectricityPrice;
		
	}
	
	public void DailyElectricityPrice(DailyElectricityPrice dailyElectricityPrice){
		
		this.dailyElectricityPrice = dailyElectricityPrice;
		
	}
	
	public DailyElectricityPrice getDailyElectricityPriceEntity(){
		
		return dailyElectricityPrice;
		
	}
	
	public float[] getDailyElectricityPrice(){
		
		float [] array = {
				dailyElectricityPrice.getPricePeriod0(), dailyElectricityPrice.getPricePeriod1(), 
				dailyElectricityPrice.getPricePeriod2(), dailyElectricityPrice.getPricePeriod3(),
				dailyElectricityPrice.getPricePeriod4(), dailyElectricityPrice.getPricePeriod5(), 
				dailyElectricityPrice.getPricePeriod6(), dailyElectricityPrice.getPricePeriod7(),
				dailyElectricityPrice.getPricePeriod8(), dailyElectricityPrice.getPricePeriod9(), 
				dailyElectricityPrice.getPricePeriod10(), dailyElectricityPrice.getPricePeriod11(),
				dailyElectricityPrice.getPricePeriod12(), dailyElectricityPrice.getPricePeriod13(), 
				dailyElectricityPrice.getPircePeriod14(), dailyElectricityPrice.getPricePeriod15(),
				dailyElectricityPrice.getPricePeriod16(), dailyElectricityPrice.getPricePeriod17(), 
				dailyElectricityPrice.getPricePeriod18(), dailyElectricityPrice.getPricePeriod19(),
				dailyElectricityPrice.getPricePeriod20(), dailyElectricityPrice.getPricePeriod21(), 
				dailyElectricityPrice.getPricePeriod22(), dailyElectricityPrice.getPricePeriod23()
		};
		return array;
	}
	
	

}
