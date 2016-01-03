package com.xjtu.sglab.shems.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.service.IOperateQueryService;


@Service
public class OperateQueryService implements IOperateQueryService {

	@Resource
	EntityManagerFactory emf ;
	@Resource
	EntityManagerHelper emh;
    
	
	@Override
	public LightSensorData queryRoom(int roomID) throws Exception {	
		    EntityManager em = emf.createEntityManager();
	        String sql = "SELECT * from light_sensor_data ORDER BY light_data_id desc limit 1";      
	        javax.persistence.Query query = em.createNativeQuery(sql,LightSensorData.class);       
	        List<LightSensorData> userList = query.getResultList();         
	        LightSensorData lightSensorData = new LightSensorData();
	        lightSensorData = userList.get(userList.size()-1);	        
	        System.out.println(lightSensorData.getLightData());
	        em.close(); 
	        return lightSensorData;
	}

}
