package com.xjtu.sglab.shems.dao.unit;


import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xjtu.sglab.shems.dao.IActivityTypeDAO;
import com.xjtu.sglab.shems.dao.IGpsInfoDAO;
import com.xjtu.sglab.shems.dao.ILocationTypeDAO;
import com.xjtu.sglab.shems.dao.IMovingStatusDAO;
import com.xjtu.sglab.shems.dao.ISocialInfoDAO;
import com.xjtu.sglab.shems.dao.ISocialSourceDAO;
import com.xjtu.sglab.shems.dao.IWearableDeviceInfoDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.GpsInfoDAO;
import com.xjtu.sglab.shems.dao.impl.LocationTypeDAO;
import com.xjtu.sglab.shems.dao.impl.MovingStatusDAO;
import com.xjtu.sglab.shems.dao.impl.WearableDeviceInfoDAO;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LocationType;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class DaoTest {
	@Resource
	EntityManagerHelper emh;
	//gps
	@Resource
    IGpsInfoDAO gpsInfoDAO;
	@Resource
	ILocationTypeDAO locationTypeDAO;
	//wristband
	@Resource
	IWearableDeviceInfoDAO wearableDeviceInfoDAO;
	@Resource
	IMovingStatusDAO movingStatusDAO;
	//social media
	@Resource
	ISocialSourceDAO socialSourceDAO;
	@Resource
	ISocialInfoDAO socialInfoDAO;
	@Resource
	IActivityTypeDAO activityTypeDAO;
	
	@Ignore
	@Test
	public void testGPSService(){
		emh.beginTransaction();
		LocationType locationType=locationTypeDAO.findById(1);
		GpsInfo gpsInfo=new GpsInfo();
		gpsInfo.setGpsLatitude(101d);
		gpsInfo.setGpsLongitude(200d);
		gpsInfo.setGpsRecordTime(new Timestamp(System.currentTimeMillis()));
		gpsInfo.setLocationType(locationType);
		gpsInfo.setDistanceFromHome(100f);
		gpsInfoDAO.save(gpsInfo);
		emh.commit();
	}
	@Ignore
	@Test
	public void testWearableService(){
		emh.beginTransaction();
		WearableDeviceInfo wearableDeviceInfo=new WearableDeviceInfo();
		wearableDeviceInfo.setMovingStatus(movingStatusDAO.findById(1));
		wearableDeviceInfo.setAcceleratedSpeedX(100f);
		wearableDeviceInfo.setAcceleratedSpeedY(100f);
		wearableDeviceInfo.setAcceleratedSpeedZ(100f);
		wearableDeviceInfo.setBodyTemperature(10f);
		wearableDeviceInfo.setGyroscopeX(200f);
		wearableDeviceInfo.setGyroscopeY(200f);
		wearableDeviceInfo.setGyroscopeZ(200f);
		wearableDeviceInfo.setHeartRate(100f);
		wearableDeviceInfo.setSpeed(100f);
		wearableDeviceInfo.setWearableInfoRecordTime(new Timestamp(System.currentTimeMillis()));
		wearableDeviceInfoDAO.save(wearableDeviceInfo);
		emh.commit();
	}
//	@Ignore
	@Test
	public void testSocialService(){
		emh.beginTransaction();
		SocialInfo socialInfo=new SocialInfo();
		socialInfo.setSocialSource(socialSourceDAO.findById(1));
 
		ActivityType set=socialInfo.getActivityType();
	 
//		set.add(activityTypeDAO.findById(3));
//		set.add(activityTypeDAO.findById(4));
		socialInfo.setActivityRecordTime(new Timestamp(System.currentTimeMillis()));
		socialInfo.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
		socialInfoDAO.save(socialInfo);
		emh.commit();
	}
	

 
}
