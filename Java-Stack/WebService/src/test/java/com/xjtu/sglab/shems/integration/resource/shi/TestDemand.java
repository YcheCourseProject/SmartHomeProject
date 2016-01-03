package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.dao.impl.ActivityTypeDAO;
import com.xjtu.sglab.shems.dao.impl.AirConditionDemandDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.RealTimeDemandDAO;
import com.xjtu.sglab.shems.dao.impl.SocialActivityToDemandDAO;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.AirConditionControlDetail;
import com.xjtu.sglab.shems.entity.AirConditionDemand;
import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.GasSensor;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.RealTimeDecision;
import com.xjtu.sglab.shems.entity.RealTimeDemand;
import com.xjtu.sglab.shems.entity.SocialActivityToDemand;
import com.xjtu.sglab.shems.service.impl.OperateDemandService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class TestDemand {
	private static final String LOCAL_HOST_IP = "192.168.1.6";
	private static final String LOOP_IP = "localhost";
	private static final String REMOTE_HOST_IP = "202.117.14.247";
//	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
	private static final String HOST_IP = LOOP_IP;           //本机测试用
	
	@Resource
	EntityManagerHelper emh;

	@Resource
	RealTimeDemandDAO realTimeDemandDAO;
	@Resource
	ActivityTypeDAO activityTypeDAO;
	@Resource
	AirConditionDemandDAO airConditionDemandDAO;
	@Resource
	SocialActivityToDemandDAO socialActivityToDemandDAO;

	

	
	@Ignore
	@Test
	
	public void testCreateSocialDemand() throws Exception {

		SocialActivityToDemand socialActivityToDemand =new SocialActivityToDemand();
		OperateDemandService operateDemandService =new OperateDemandService();
		operateDemandService.createRealTimeDemand(socialActivityToDemand);
		
	}
	
	@Ignore
	@Test
	public void testPrice() throws Exception{
		OperateDemandService operateDemandService =new OperateDemandService();
		DailyElectricityPrice price = operateDemandService.queryPrice();
		
		Gson gson = GsonUtil.create();
		String str = gson.toJson(price);
		System.out.println(str);
	}

	@Ignore
	@Test
	public void testReturnRealTimeDecision() throws Exception{
		RealTimeDecision realTimeDecision = new RealTimeDecision();
		realTimeDecision.setRealTimeDecisionRecordTime(new Timestamp(System.currentTimeMillis()));
		realTimeDecision.setRealTimeDemand(null);
		
		Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions = 
				new HashSet<AirConditionRealTimeDecision>();
		realTimeDecision.setAirConditionRealTimeDecisions(airConditionRealTimeDecisions);
		AirConditionRealTimeDecision acetd1 = new AirConditionRealTimeDecision();
		AirConditionRealTimeDecision acetd2 = new AirConditionRealTimeDecision();
		airConditionRealTimeDecisions.add(acetd1);
		airConditionRealTimeDecisions.add(acetd2);
		acetd1.setAirConditionDecideEndTime(new Timestamp(System.currentTimeMillis()));
		acetd1.setAirConditionDecideStartTime(new Timestamp(System.currentTimeMillis()));
		acetd1.setAirConditionDecisionRecordTime(new Timestamp(System.currentTimeMillis()));
		acetd1.setAirConditionDecideAverageEnergy(100f);
		
		AirConditionControlDetail airConditionControlDetail1  = new AirConditionControlDetail();
		acetd1.setAirConditionControlDetail(airConditionControlDetail1);
		airConditionControlDetail1.setAirConditionControlEndTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail1.setAirConditionControlRecordTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail1.setAirConditionControlStartTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail1.setAirConditionMode(2);
		airConditionControlDetail1.setAirConditionTemperature(35f);
		airConditionControlDetail1.setAirConditionRealTimeDecision(acetd1);
		
		
		acetd2.setAirConditionDecideEndTime(new Timestamp(System.currentTimeMillis()));
		acetd2.setAirConditionDecideStartTime(new Timestamp(System.currentTimeMillis()));
		acetd2.setAirConditionDecisionRecordTime(new Timestamp(System.currentTimeMillis()));
		acetd2.setAirConditionDecideAverageEnergy(10f);
		AirConditionControlDetail airConditionControlDetail2 = new AirConditionControlDetail();
		acetd2.setAirConditionControlDetail(airConditionControlDetail2);
		airConditionControlDetail2.setAirConditionControlEndTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail2.setAirConditionControlRecordTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail2.setAirConditionControlStartTime(new Timestamp(System.currentTimeMillis()));
		airConditionControlDetail2.setAirConditionMode(3);
		airConditionControlDetail2.setAirConditionTemperature(34f);
		airConditionControlDetail2.setAirConditionRealTimeDecision(acetd2);
	
		
		OperateDemandService operateDemandService =new OperateDemandService();
		RealTimeDecision rtdTmp = operateDemandService.createRealTimeDecision(realTimeDecision);
//		Gson gson = GsonUtil.create();
//		String tmpstr = gson.toJson(rtdTmp, RealTimeDecis`ion.class);
//		System.out.println(tmpstr);	
	}
	
	 @Test
	 public void testQueryModelResult() throws JsonGenerationException,
		JsonMappingException, IOException {
		 
		 ClientConfig clientConfig = new ClientConfig();
		 clientConfig.register(GsonJsonProvider.class);
		 Client client = ClientBuilder.newClient(clientConfig);
		 Gson gson=GsonUtil.create();
		 WebTarget webTarget = client
					.target("http://"+HOST_IP+":8080/smarthome/socialDemand/modelResultShow");
		 Builder request = webTarget.request();
		 Response response = request.get();
		 AirConditionRealTimeDecision[] airConditionRealTimeDecisions = response.readEntity(AirConditionRealTimeDecision[].class);
		 System.out.println(airConditionRealTimeDecisions.length);
		 System.out.println(gson.toJson(airConditionRealTimeDecisions));
	 }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
