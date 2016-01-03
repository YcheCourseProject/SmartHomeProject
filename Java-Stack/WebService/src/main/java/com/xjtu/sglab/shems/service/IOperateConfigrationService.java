package com.xjtu.sglab.shems.service;

import java.util.Set;

import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.Room;

public interface IOperateConfigrationService {

	public void saveConfigration(Room room) throws Exception;
	
	public Room queryConfiguration(int roomID) throws Exception;
	
	public Room[] queryConfigurationArray(Set<Integer> roomIDSet) throws Exception;

}
