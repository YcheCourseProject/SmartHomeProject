package com.xjtu.sglab.shems.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.IMovingStatusDAO;
import com.xjtu.sglab.shems.dao.IWearableDeviceInfoDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.service.IOperateWearableService;

@Service
public class OperateWearableService implements IOperateWearableService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf ;
	@Resource
	IWearableDeviceInfoDAO wearableDeviceInfoDAO;
	@Resource
	IMovingStatusDAO movingStatusDAO;

	@Override
	public void saveWearableInfo(WearableDeviceInfo wearableDeviceInfo)
			throws Exception {
		// TODO Auto-generated method stub
		if (wearableDeviceInfo.getMovingStatus() != null
				&& wearableDeviceInfo.getMovingStatus().getMovingType()!=null
				&& wearableDeviceInfo.getAcceleratedSpeedX() != null
				&& wearableDeviceInfo.getAcceleratedSpeedY() != null
				&& wearableDeviceInfo.getAcceleratedSpeedZ() != null
				&& wearableDeviceInfo.getGyroscopeX() != null
				&& wearableDeviceInfo.getGyroscopeY() != null
				&& wearableDeviceInfo.getHeartRate() != null
				&& wearableDeviceInfo.getSpeed() != null
				&& wearableDeviceInfo.getBodyTemperature() != null
				&& wearableDeviceInfo.getWearableInfoRecordTime()!=null) {
			emh.beginTransaction();
//			wearableDeviceInfo.setMovingStatus(movingStatusDAO.findByProperty("movingType", wearableDeviceInfo.getMovingStatus().getMovingType()).get(0));
//			wearableDeviceInfo.setMovingStatus(movingStatusDAO.findById(wearableDeviceInfo.getMovingStatus().getMovingTypeId()));
			wearableDeviceInfo.setMovingStatus(movingStatusDAO.findById(1));
			wearableDeviceInfoDAO.save(wearableDeviceInfo);
			emh.commit();
		} else {
			throw new Exception();
		}
	}

	@Override
	public WearableDeviceInfo queryWearableInfo() throws Exception {
	    EntityManager em = emh.getEntityManager();
        String sql = "SELECT * from wearable_device_info ORDER BY wearable_info_id desc limit 1";      
        javax.persistence.Query query = em.createNativeQuery(sql,WearableDeviceInfo.class); 
        List<WearableDeviceInfo> userList = query.getResultList();        
        WearableDeviceInfo wearableDeviceInfo = new WearableDeviceInfo();
        wearableDeviceInfo = userList.get(userList.size()-1);	        
      
        em.close(); 
        return wearableDeviceInfo;	
		
	}
	
	
	

}
