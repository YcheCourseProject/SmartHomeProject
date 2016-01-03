package com.xjtu.sglab.shems.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.ICircuitLineDAO;
import com.xjtu.sglab.shems.dao.IElectricityInfoDAO;
import com.xjtu.sglab.shems.dao.IElectricityMeterDAO;
import com.xjtu.sglab.shems.dao.IElectricityTypeDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.impl.ElectricityInfoDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.CircuitLine;
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.service.IOperateSmartMeterService;

@Service
public class OperateSmartMeterService implements IOperateSmartMeterService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf;

	@Resource
	ICircuitLineDAO CircuitLineDAO;
	@Resource
	IElectricityInfoDAO ElectricityInfoDAO;
	@Resource
	IElectricityMeterDAO ElectricityMeterDAO;
	@Resource
	IElectricityTypeDAO ElectricityTypeDAO;
	@Resource
	IRoomDAO RoomDAO;

	@Override
	public void saveSmartMeterInfo(ElectricityInfo electricityInfo)
			throws Exception {
		if (electricityInfo.getActivePower() != null
				&& electricityInfo.getElectricityInfoCollectTime() != null
				&& electricityInfo.getReactivePower() != null
				&& electricityInfo.getTotalConsumeEnergy() != null) {
			emh.beginTransaction();
			// ElectricityInfoDAO electricityInfoDAO = new ElectricityInfoDAO();
			// //预备无效情况
			ElectricityInfoDAO.save(electricityInfo);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public ElectricityInfo queryElectricityInfo(int ElectricityMeterID)
			throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from electricity_info where electricity_meter_id="
				+ ElectricityMeterID
				+ " ORDER BY electricity_info_id desc limit 1";
		javax.persistence.Query query = em.createNativeQuery(sql,
				ElectricityInfo.class);
		List<ElectricityInfo> userList = query.getResultList();
		ElectricityInfo electricityInfo = new ElectricityInfo();
		electricityInfo = userList.get(userList.size() - 1);
		System.out.println(electricityInfo.getElectricityMeter());
		em.close();
		return electricityInfo;
	}

	private int getElectricityInfoID(ElectricityMeter electricityMeter) {
		return 1;
	}

	@Override
	public ElectricityInfo[] queryElectricityInfoArray(
			Set<Integer> electricityMeterIDSet) throws Exception {
		ElectricityInfo[] electricityInfos = new ElectricityInfo[electricityMeterIDSet
				.size()];
		int index = 0;
		for (Integer gasSensorID : electricityMeterIDSet) {
			electricityInfos[index] = queryElectricityInfo(gasSensorID);
			index++;
		}
		return electricityInfos;
	}

	@Override
	public ElectricityInfo[] queryElectricityInfoEveryHour() throws Exception {
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
	    String sql = "SELECT * FROM (SELECT *,"
	    		+ "DATE(electricity_info_collect_time) AS d,"
	    		+ "SUBSTRING(electricity_info_collect_time,15,2) as h FROM electricity_info"
	    		+ " WHERE electricity_meter_id=1 AND date(electricity_info_collect_time)=curdate()"
	    		+ " ORDER BY electricity_meter_id,active_power,reactive_power,total_consume_energy,electricity_info_collect_time DESC )"
	    		+ " electricity_info"
	    		+ " WHERE h='00'"
	    		+ "  GROUP BY electricity_meter_id,active_power,reactive_power,total_consume_energy,d,h ;";
	    javax.persistence.Query query = em.createNativeQuery(sql,ElectricityInfo.class); 
        List<ElectricityInfo> userList = query.getResultList();
        ElectricityInfo[] electricityInfos =  new ElectricityInfo[userList.size()];   
        System.out.println(userList.size());
        for(int index=0;index<userList.size();index++){
        	electricityInfos[index] = userList.get(index);
        	CircuitLine circuitLine=electricityInfos[index].getElectricityMeter().getCircuitLine();
        	if(circuitLine!=null){
        		circuitLine.setCircuitLine(null);
        		circuitLine.setCircuitLines(null);
        		circuitLine.setElectricityMeter(null);
        		circuitLine.setElectricityMeters(null);
        		circuitLine.setRoom(null);
        	}
        	electricityInfos[index].getElectricityMeter().setCircuitLines(null);
        	electricityInfos[index].getElectricityMeter().setElectricityInfos(null);
        	electricityInfos[index].getElectricityMeter().setElectricityType(null);
        }        
        emh.commit();
		em.close();
		return electricityInfos;
	}

}
