package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.entity.DailyElectricityPrice;

public interface IOperatePriceService {
	public DailyElectricityPrice queryPrice(int month) throws Exception;
}
