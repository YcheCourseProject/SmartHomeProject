package com.xjtu.sglab.shems.integration.resource.che;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.HumidityData;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.SocialSource;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestSensorBoxRestServiceIT {
	private static final String LOCAL_HOST_IP="192.168.1.13";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	
	
	@Test
	public void testQueryLightSensorDataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/lightsensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		LightSensorData[] lightSensorDatas = response.readEntity(LightSensorData[].class);
		System.out.println(gson.toJson(lightSensorDatas));
	}
	
	@Test
	public void testQueryFlameSensorataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/flamesensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		FlameSensorData[] flameSensorDatas = response.readEntity(FlameSensorData[].class);
		System.out.println(gson.toJson(flameSensorDatas));
	}
	
	@Test
	public void testQueryGasSensorrataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/gassensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		GasSensorData[] gasSensorDatas = response.readEntity(GasSensorData[].class);
		System.out.println(gson.toJson(gasSensorDatas));
	}
	
	@Test
	public void testQueryPlrSensorrataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/plrsensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		PlrSensorData[] plrSensorDatas = response.readEntity(PlrSensorData[].class);
		System.out.println(gson.toJson(plrSensorDatas));
	}
	
	@Test
	public void testQueryTemperatureSensorDataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/temperaturesensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		TemperatureSensorData[] temperatureSensorDatas = response.readEntity(TemperatureSensorData[].class);
		System.out.println(gson.toJson(temperatureSensorDatas));
	}

	@Test
	public void testQueryHumidtySensorrataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/sensorBox/humidtysensor/list").queryParam("ids", 1,2);
		Builder request = webTarget.request();
		Response response = request.get();
		HumidityData[] humidityDatas = response.readEntity(HumidityData[].class);
		System.out.println(gson.toJson(humidityDatas));
	}
	
	
	
	
	
	

	

}
