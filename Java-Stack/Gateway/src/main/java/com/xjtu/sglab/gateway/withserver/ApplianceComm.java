package com.xjtu.sglab.gateway.withserver;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.google.gson.Gson;
import com.xjtu.sglab.exp.Constants;
import com.xjtu.sglab.gateway.util.GsonJsonProvider;
import com.xjtu.sglab.gateway.entity.AirCondition;
import com.xjtu.sglab.gateway.entity.AirConditionStatus;
import com.xjtu.sglab.gateway.entity.Curtain;
import com.xjtu.sglab.gateway.entity.CurtainStatus;
import com.xjtu.sglab.gateway.entity.Lamp;
import com.xjtu.sglab.gateway.entity.LampStatus;
import com.xjtu.sglab.gateway.entity.SheSwitch;
import com.xjtu.sglab.gateway.entity.SheSwitchStatus;
import com.xjtu.sglab.gateway.entity.WaterHeater;
import com.xjtu.sglab.gateway.entity.WaterHeaterStatus;
import com.xjtu.sglab.gateway.util.GsonUtil;
 

public class ApplianceComm {
	private static final ApplianceComm applianceComm=null;
	public static ApplianceComm getInstance(){
		if(applianceComm==null)
			return new ApplianceComm();
		else
			return applianceComm;
	}
	
	public LampStatus[] queryLampStatusArray(Integer...ints)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/lamp/list").queryParam("ids",ints);
		Builder request = webTarget.request();
		Response response = request.get();
		LampStatus[] lampStatus = response.readEntity(LampStatus[].class);
		return lampStatus;
	}
	
	public CurtainStatus[] queryCurtainStatus(Integer...ints)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/curtain/list").queryParam("ids", ints);
		Builder request = webTarget.request();
		Response response = request.get();
		CurtainStatus[] curtainStatus = response.readEntity(CurtainStatus[].class);
		return curtainStatus;
	}
	
	public SheSwitchStatus[] querySheSwitchStatus(Integer...ints) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/sheSwitch/list").queryParam("ids", ints);
		Builder request = webTarget.request();
		Response response = request.get();
		SheSwitchStatus[] sheSwitchStatus = response.readEntity(SheSwitchStatus[].class);
		return sheSwitchStatus;
	}
	
	public AirConditionStatus[] queryAirConditionStatus(Integer...ints) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/airCondition/list").queryParam("ids",ints);
		Builder request = webTarget.request();
		Response response = request.get();
		AirConditionStatus[] airConditionStatus = response.readEntity(AirConditionStatus[].class);
		return airConditionStatus;
	}
	
	public WaterHeaterStatus[] queryWaterHeaterStatus(Integer...ints) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/waterHeater/list").queryParam("ids",ints);
		Builder request = webTarget.request();
		Response response = request.get();
		WaterHeaterStatus[] waterHeaterStatus = response.readEntity(WaterHeaterStatus[].class);
		return waterHeaterStatus;
	}
	
	public void saveLampInfo(int lampId,int lampStatusCode)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/lamp");
		Builder request = webTarget.request();
		
	
		LampStatus lampStatus = new LampStatus();
		lampStatus.setIsControlledByUser(true);
		lampStatus.setLampStatus(lampStatusCode);
		Lamp lamp = new Lamp();
		lamp.setLampId(lampId);
		lampStatus.setLamp(lamp);
		lampStatus.setIsAlreadyControlled(true);
		lampStatus.setLampStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		
		Response response = request.post(Entity.entity(lampStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	
	public void saveCurtainInfo(int curtainId,float curtainStatusCode) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/curtain");
		Builder request = webTarget.request();
		
	
		CurtainStatus curtainStatus = new CurtainStatus();
		Curtain curtain = new Curtain();
		curtain.setCurtainId(curtainId);
		curtainStatus.setCurtain(curtain);
		curtainStatus.setCurtainStatus(curtainStatusCode);
		curtainStatus.setCurtainStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		curtainStatus.setIsControlledByUser(false);
		curtainStatus.setIsAlreadyControlled(true);

		Response response = request.post(Entity.entity(curtainStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	public void saveSheSwitchInfo(int sheSwitchId,boolean sheStatusCode)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/sheSwitch");
		Builder request = webTarget.request();
		 
//	    sheSwitchId = 1;
	    SheSwitchStatus sheSwitchStatus = new SheSwitchStatus();
	    SheSwitch sheSwitch = new SheSwitch();
	    sheSwitch.setSheSwitchId(sheSwitchId);
	    sheSwitchStatus.setSheSwitch(sheSwitch);
	    sheSwitchStatus.setIsControlledByUser(true);
	    sheSwitchStatus.setSheSwitchStatus(sheStatusCode);
	    sheSwitchStatus.setSheSwitchStatusRecordTime(new Timestamp(System.currentTimeMillis()));
	    sheSwitchStatus.setIsAlreadyControlled(true);

		Response response = request.post(Entity.entity(sheSwitchStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	public void saveAirConditionInfo(int airConditionId, int modeCode, float temperatureCode)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/airCondition");
		Builder request = webTarget.request();

		AirConditionStatus airConditionStatus = new AirConditionStatus();
		AirCondition airCondition = new AirCondition();
		airCondition.setAirConditionId(airConditionId);
		airConditionStatus.setAirCondition(airCondition);
		airConditionStatus.setAirConditionMode(modeCode);
		airConditionStatus.setAirConditionStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		airConditionStatus.setAirConditionTemperature(temperatureCode);
		airConditionStatus.setIsControlledByUser(false);
		airConditionStatus.setIsAlreadyControlled(true);
		
		Response response = request.post(Entity.entity(airConditionStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}
	public void saveWaterHeaterInfo(int waterHeaterId)  {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/appliance/waterHeater");
		Builder request = webTarget.request();

		WaterHeaterStatus waterHeaterStatus = new WaterHeaterStatus();
		WaterHeater waterHeater = new WaterHeater();
		waterHeater.setWaterHeaterId(waterHeaterId);
		waterHeaterStatus.setWaterHeater(waterHeater);
		waterHeaterStatus.setIsControlledByUser(true);
		waterHeaterStatus.setWaterHeaterStatusRecordTime(new Timestamp(System.currentTimeMillis()));
		waterHeaterStatus.setWaterHeaterTemperature(37f);
		waterHeaterStatus.setIsAlreadyControlled(true);
		
		Response response = request.post(Entity.entity(waterHeaterStatus,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
}

	
}
