package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.RealTimeDemand;
import com.xjtu.sglab.shems.entity.SocialActivityToDemand;

public interface IOperateDemandService {
	public RealTimeDemand createRealTimeDemand(SocialActivityToDemand socialActivityToDemand) throws Exception;
	
	public AirConditionRealTimeDecision[] queryModelDecision() throws Exception;
}
