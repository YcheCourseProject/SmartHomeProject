package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
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

import com.google.gson.Gson;
import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionStatus;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.CurtainStatus;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.SheSwitch;
import com.xjtu.sglab.shems.entity.SheSwitchStatus;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterStatus;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestRestServiceAppliance {
	@Resource
    IBoxDAO BoxDAO;
	@Resource
	IRoomDAO RoomDAO;
	@Resource
	LampStatus LampStatusDAO;
	private static final String LOCAL_HOST_IP="192.168.1.6";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
//	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
	private static final String HOST_IP = LOOP_IP;           //本机测试用
	
	@Ignore
	@Test
	public void testSaveLampInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/lamp");
		Builder request = webTarget.request();
		
		int lampid = 1;	
		LampStatus lampStatus = new LampStatus();
		lampStatus.setIsControlledByUser(true);
		lampStatus.setLampStatus(2);
		Lamp lamp = new Lamp();
		lamp.setLampId(lampid);
		lampStatus.setLamp(lamp);
		lampStatus.setIsAlreadyControlled(true);
		lampStatus.setLampStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		
     	Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(lampStatus, LampStatus.class);
		System.out.println(tmpstr);	
		Response response = request.post(Entity.entity(lampStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	
	@Ignore
	@Test
	public void testSaveCurtainInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/curtain");
		Builder request = webTarget.request();
		
		int curtainid = 1;
		CurtainStatus curtainStatus = new CurtainStatus();
		Curtain curtain = new Curtain();
		curtain.setCurtainId(curtainid);
		curtainStatus.setCurtain(curtain);
		curtainStatus.setCurtainStatus(10f);
		curtainStatus.setCurtainStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		curtainStatus.setIsControlledByUser(false);

		
     	Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(curtainStatus, CurtainStatus.class);
		System.out.println(tmpstr);	
		Response response = request.post(Entity.entity(curtainStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	@Ignore
	@Test
	public void testSaveSheSwitchInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/sheSwitch");
		Builder request = webTarget.request();
		 
	    int sheSwitchId = 1;
	    SheSwitchStatus sheSwitchStatus = new SheSwitchStatus();
	    SheSwitch sheSwitch = new SheSwitch();
	    sheSwitch.setSheSwitchId(sheSwitchId);
	    sheSwitchStatus.setSheSwitch(sheSwitch);
	    sheSwitchStatus.setIsControlledByUser(true);
	    sheSwitchStatus.setSheSwitchStatus(true);
	    sheSwitchStatus.setSheSwitchStatusRecordTime(new Timestamp(System.currentTimeMillis()));

		
     	Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(sheSwitchStatus, SheSwitchStatus.class);
		System.out.println(tmpstr);	
		Response response = request.post(Entity.entity(sheSwitchStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
//	@Ignore
	@Test
	public void testSaveAirConditionInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/airCondition");
		Builder request = webTarget.request();
		
		
		int airConditionId = 1;
		AirConditionStatus airConditionStatus = new AirConditionStatus();
		AirCondition airCondition = new AirCondition();
		airCondition.setAirConditionId(airConditionId);
		airConditionStatus.setAirCondition(airCondition);
		airConditionStatus.setAirConditionMode(1);
		airConditionStatus.setAirConditionStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		airConditionStatus.setAirConditionTemperature(35f);
		airConditionStatus.setIsControlledByUser(false);
		airConditionStatus.setIsAlreadyControlled(false);
     	Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(airConditionStatus, AirConditionStatus.class);
		System.out.println(tmpstr);	
		Response response = request.post(Entity.entity(airConditionStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	@Ignore
	@Test
	public void testSaveWaterHeaterInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/waterHeater");
		Builder request = webTarget.request();
		
		
		int waterHeaterId = 1;
		WaterHeaterStatus waterHeaterStatus = new WaterHeaterStatus();
		WaterHeater waterHeater = new WaterHeater();
		waterHeater.setWaterHeaterId(waterHeaterId);
		waterHeaterStatus.setWaterHeater(waterHeater);
		waterHeaterStatus.setIsControlledByUser(true);
		waterHeaterStatus.setWaterHeaterStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		waterHeaterStatus.setWaterHeaterTemperature(37f);
		
		
     	Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(waterHeaterStatus, WaterHeaterStatus.class);
		System.out.println(tmpstr);	
		Response response = request.post(Entity.entity(waterHeaterStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}

	@Ignore
	@Test
	public void testQueryLampStatus() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/lamp/1");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		
		Response response = request.get();
		LampStatus lampStatus = response.readEntity(LampStatus.class);
		System.out.println(gson.toJson(lampStatus));
	}
	
	@Ignore
	@Test
	public void testQueryCurtainStatus() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/curtain/1");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		
		Response response = request.get();
		CurtainStatus curtainStatus = response.readEntity(CurtainStatus.class);
		System.out.println(gson.toJson(curtainStatus));
	}
	
	@Ignore
	@Test
	public void testQuerySheSwitchStatus() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/sheSwitch/1");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		
		Response response = request.get();
		SheSwitchStatus sheSwitchStatus = response.readEntity(SheSwitchStatus.class);
		System.out.println(gson.toJson(sheSwitchStatus));
	}
//	@Ignore
	@Test
	public void testQueryAirConditionStatus() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/airCondition/1");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		
		Response response = request.get();
		AirConditionStatus airConditionStatus = response.readEntity(AirConditionStatus.class);
		System.out.println(gson.toJson(airConditionStatus));
	}
	@Ignore
	@Test
	public void testQueryWaterHeaterStatus() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/appliance/waterHeater/1");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		Response response = request.get();
		WaterHeaterStatus waterHeaterStatus = response.readEntity(WaterHeaterStatus.class);
		System.out.println(gson.toJson(waterHeaterStatus));
	}
	
}