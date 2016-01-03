package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.model.weather.HeFengWeatherService;

public interface IOperateWeatherInfoService {
	public HeFengWeatherService getWeatherByCityCode(String cityCode,String AppKey) throws Exception ;
}
