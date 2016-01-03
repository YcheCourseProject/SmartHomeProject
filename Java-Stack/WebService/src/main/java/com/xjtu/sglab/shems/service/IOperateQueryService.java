package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.entity.LightSensorData;


public interface IOperateQueryService {

	public LightSensorData queryRoom(int roomID) throws Exception;


}
