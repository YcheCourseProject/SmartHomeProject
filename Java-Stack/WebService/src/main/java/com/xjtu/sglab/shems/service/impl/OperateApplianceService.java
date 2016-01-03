package com.xjtu.sglab.shems.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.IAirConditionDAO;
import com.xjtu.sglab.shems.dao.ICurtainDAO;
import com.xjtu.sglab.shems.dao.ILampDAO;
import com.xjtu.sglab.shems.dao.ISheSwitchDAO;
import com.xjtu.sglab.shems.dao.IWaterHeaterDAO;
import com.xjtu.sglab.shems.dao.impl.AirConditionStatusDAO;
import com.xjtu.sglab.shems.dao.impl.CurtainStatusDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.LampStatusDAO;
import com.xjtu.sglab.shems.dao.impl.SheSwitchDAO;
import com.xjtu.sglab.shems.dao.impl.SheSwitchStatusDAO;
import com.xjtu.sglab.shems.dao.impl.WaterHeaterDAO;
import com.xjtu.sglab.shems.dao.impl.WaterHeaterStatusDAO;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionStatus;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.CurtainStatus;
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.SheSwitch;
import com.xjtu.sglab.shems.entity.SheSwitchStatus;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterStatus;
import com.xjtu.sglab.shems.service.IOperateApplianceService;

@Service
public class OperateApplianceService implements IOperateApplianceService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf;
	@Resource
	ILampDAO LampDAO;
	@Resource
	ICurtainDAO CurtainDAO;
	@Resource
	ISheSwitchDAO SheSwitchDAO;
	@Resource
	IAirConditionDAO AirConditionDAO;
	@Resource
	IWaterHeaterDAO WaterHeaterDAO;

	@Override
	public void saveLampInfo(LampStatus lampStatus) throws Exception {
		if (lampStatus.getIsControlledByUser() != null
				&& lampStatus.getLampStatus() != null
				&& lampStatus.getLampStatusRecordTime() != null) {
			emh.beginTransaction();
			LampStatusDAO lampStatusDAO = new LampStatusDAO();
			lampStatusDAO.save(lampStatus);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void saveCurtainInfo(CurtainStatus curtainStatus) throws Exception {
		if (curtainStatus.getIsControlledByUser() != null
				&& curtainStatus.getCurtainStatus() != null
				&& curtainStatus.getCurtainStatusRecordTime() != null) {
			emh.beginTransaction();
			CurtainStatusDAO curtainStatusDAO = new CurtainStatusDAO();
			curtainStatusDAO.save(curtainStatus);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void saveSheSwitchInfo(SheSwitchStatus sheSwitchStatus)
			throws Exception {
		if (sheSwitchStatus.getSheSwitchStatusRecordTime() != null
				&& sheSwitchStatus.getSheSwitchStatus()!=null) {
			emh.beginTransaction();
			SheSwitchStatusDAO sheSwitchStatusDAO = new SheSwitchStatusDAO();
			sheSwitchStatusDAO.save(sheSwitchStatus);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void saveAirConditionInfo(AirConditionStatus airConditionStatus)
			throws Exception {
		if (airConditionStatus.getIsControlledByUser() != null
				&& airConditionStatus.getAirConditionMode() != null
				&& airConditionStatus.getAirConditionStatusRecordTime() != null
				&& airConditionStatus.getAirConditionTemperature() != null) {
			emh.beginTransaction();
			AirConditionStatusDAO airConditionStatusDAO = new AirConditionStatusDAO();
			airConditionStatusDAO.save(airConditionStatus);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void saveWaterHeaterInfo(WaterHeaterStatus waterHeaterStatus)
			throws Exception {
		if (waterHeaterStatus.getWaterHeaterStatusRecordTime() != null
				&& waterHeaterStatus.getWaterHeaterTemperature() != null) {
			emh.beginTransaction();
			WaterHeaterStatusDAO waterHeaterStatusDAO = new WaterHeaterStatusDAO();
			waterHeaterStatusDAO.save(waterHeaterStatus);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public LampStatus queryLampStatus(int LampID) throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from lamp_status where lamp_id=" + LampID
				+ " ORDER BY lamp_status_id desc limit 1 ";
		javax.persistence.Query query = em.createNativeQuery(sql,
				LampStatus.class);
		List<LampStatus> userList = query.getResultList();
		LampStatus lampStatus = new LampStatus();
		lampStatus = userList.get(userList.size() - 1);
		System.out.println(lampStatus.getLampStatus());
		em.close();
		return lampStatus;
	}

	@Override
	public CurtainStatus queryCurtainStatus(int CurtainID) throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from curtain_status where curtain_id="
				+ CurtainID + " ORDER BY curtain_status_id desc limit 1";
		javax.persistence.Query query = em.createNativeQuery(sql,
				CurtainStatus.class);
		List<CurtainStatus> userList = query.getResultList();
		CurtainStatus curtainStatus = new CurtainStatus();
		curtainStatus = userList.get(userList.size() - 1);
		System.out.println(curtainStatus.getCurtainStatus());
		em.close();
		return curtainStatus;
	}

	@Override
	public SheSwitchStatus querySheSwitchStatus(int SheSwitchID)
			throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from she_switch_status where she_switch_id="
				+ SheSwitchID + " ORDER BY she_switch_status_id desc limit 1";
		Query query = em.createNativeQuery(sql, SheSwitchStatus.class);
		List<SheSwitchStatus> userList = query.getResultList();
		SheSwitchStatus sheSwitchStatus = new SheSwitchStatus();
		sheSwitchStatus = userList.get(userList.size() - 1);
		System.out.println(sheSwitchStatus.getSheSwitchStatus());
		em.close();
		return sheSwitchStatus;
	}

	@Override
	public AirConditionStatus queryAirConditionStatus(int AirConditionID)
			throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from air_condition_status where air_condition_id="
				+ AirConditionID
				+ " ORDER BY air_condition_status_id desc limit 1";
		javax.persistence.Query query = em.createNativeQuery(sql,
				AirConditionStatus.class);
		List<AirConditionStatus> userList = query.getResultList();
		AirConditionStatus airConditionStatus = new AirConditionStatus();
		airConditionStatus = userList.get(userList.size() - 1);
		System.out.println(airConditionStatus.getAirCondition());
		em.close();
		return airConditionStatus;
	}

	@Override
	public WaterHeaterStatus queryWaterHeaterStatus(int WaterHeaterID)
			throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "SELECT * from water_heater_status where water_heater_id="
				+ WaterHeaterID
				+ " ORDER BY water_heater_stauts_id desc limit 1";
		javax.persistence.Query query = em.createNativeQuery(sql,
				WaterHeaterStatus.class);
		List<WaterHeaterStatus> userList = query.getResultList();
		WaterHeaterStatus waterHeaterStatus = new WaterHeaterStatus();
		waterHeaterStatus = userList.get(userList.size() - 1);
		System.out.println(waterHeaterStatus.getWaterHeater());
		em.close();
		return waterHeaterStatus;
	}

	@Override
	public LampStatus[] queryLampStatusArray(Set<Integer> LampIDSet)
			throws Exception {
		LampStatus[] lampStatus = new LampStatus[LampIDSet.size()];
		int index = 0;
		for (Integer lampID : LampIDSet) {
			lampStatus[index] = queryLampStatus(lampID);
			index++;
		}
		return lampStatus;
	}

	@Override
	public CurtainStatus[] queryCurtainStatusArray(Set<Integer> CurtainIDSet)
			throws Exception {
		CurtainStatus[] curtainStatus = new CurtainStatus[CurtainIDSet.size()];
		int index = 0;
		for (Integer curtainID : CurtainIDSet) {
			curtainStatus[index] = queryCurtainStatus(curtainID);
			index++;
		}
		return curtainStatus;
	}

	@Override
	public SheSwitchStatus[] querySheSwitchStatusArray(
			Set<Integer> SheSwitchIDSet) throws Exception {
		SheSwitchStatus[] sheSwitchStatus = new SheSwitchStatus[SheSwitchIDSet.size()];
		int index = 0;
		for (Integer  sheSwitchID : SheSwitchIDSet) {
			sheSwitchStatus[index] = querySheSwitchStatus(sheSwitchID);
			index++;
		}
		return sheSwitchStatus;
	}

	@Override
	public AirConditionStatus[] queryAirConditionStatusArray(
			Set<Integer> AirConditionIDSet) throws Exception {
		AirConditionStatus[] airConditionStatus = new AirConditionStatus[AirConditionIDSet.size()];
		int index = 0;
		for (Integer  airConditionID : AirConditionIDSet) {
			airConditionStatus[index] = queryAirConditionStatus(airConditionID);
			index++;
		}
		return airConditionStatus;
	}

	@Override
	public WaterHeaterStatus[] queryWaterHeaterStatusArray(
			Set<Integer> WaterHeaterIDSet) throws Exception {
		WaterHeaterStatus[] waterHeaterStatus = new WaterHeaterStatus[WaterHeaterIDSet.size()];
		int index = 0;
		for (Integer  waterHeaterID : WaterHeaterIDSet) {
			waterHeaterStatus[index] = queryWaterHeaterStatus(waterHeaterID);
			index++;
		}
		return waterHeaterStatus;
	}

}
