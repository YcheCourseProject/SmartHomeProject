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
import com.xjtu.sglab.shems.dao.IFlameSensorDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.impl.FlameSensorDAO;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensor;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.HumidityData;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.PlrSensor;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.TemperatureSensor;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.service.impl.OperateSensorBoxService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestRestServiceSensor {
	@Resource
	IBoxDAO BoxDAO;
	@Resource
	IRoomDAO RoomDAO;

	private static final String LOCAL_HOST_IP = "192.168.1.6";
	private static final String LOOP_IP = "localhost";
	private static final String REMOTE_HOST_IP = "202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	

	@Ignore
	@Test
	public void testQuerylightSensorData() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/lightsensor/1");
		Builder request = webTarget.request();

		Gson gson = GsonUtil.create();

		Response response = request.get();
		LightSensorData lightSensorData = response
				.readEntity(LightSensorData.class);
		System.out.println(gson.toJson(lightSensorData));
	}

	@Ignore
	@Test
	public void testQueryflameSensorData() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/flamesensor/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		FlameSensorData flameSensorData = response
				.readEntity(FlameSensorData.class);
		System.out.println(gson.toJson(flameSensorData));
	}
	
	@Ignore
	@Test
	public void testQuerygasSensorData() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/gassensor/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		GasSensorData gasSensorData = response.readEntity(GasSensorData.class);
		System.out.println(gson.toJson(gasSensorData));
	}

	
	@Ignore
	@Test
	public void testQueryplrSensorData() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/plrsensor/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		PlrSensorData plrSensorData = response.readEntity(PlrSensorData.class);
		System.out.println(gson.toJson(plrSensorData));
	}
	
	@Ignore
	@Test
	public void testQuerytemperatureSensorData()
			throws JsonGenerationException, JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/temperaturesensor/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		TemperatureSensorData temperatureSensorData = response
				.readEntity(TemperatureSensorData.class);
		System.out.println(gson.toJson(temperatureSensorData));
	}


	@Ignore
	@Test
	public void testQueryHumidityData() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/humidity/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		HumidityData humidityData = response.readEntity(HumidityData.class);
		System.out.println(gson.toJson(humidityData));
	}

	
	 @Ignore
	@Test
	public void testSaveFlameSensorInfo() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/flameSensor");
		Builder request = webTarget.request();

		int fid = 2;
		FlameSensorData flameSensorData = new FlameSensorData();
		flameSensorData.setFlameData(10f);
		FlameSensor flameSensor = new FlameSensor();
		flameSensor.setFlameSensorId(fid);
		flameSensorData.setFlameSensor(flameSensor);
		flameSensorData.setFlameDataCollectTime(new Timestamp(System
				.currentTimeMillis()));

		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(flameSensorData, FlameSensorData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(flameSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}
	 @Ignore
	 @Test
		public void testSaveHumidtySensorInfo() throws JsonGenerationException,
				JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/humidty");
		Builder request = webTarget.request();

		int tid = 1;
		HumidityData humidityData = new HumidityData();
		humidityData.setHumidityData(10f);
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		temperatureSensor.setTemperatureSensorId(tid);
		humidityData.setTemperatureSensor(temperatureSensor);
		humidityData.setHumidityDataRecordTime(new Timestamp(System
			.currentTimeMillis()));
		
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(humidityData, HumidityData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(humidityData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
		}

	 @Ignore
	@Test
	public void testSaveLightSensorInfo() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/lightSensor");
		Builder request = webTarget.request();

		int lid = 2;
		LightSensorData lightSensorData = new LightSensorData();
		lightSensorData.setLightData(10f);
		LightSensor lightSensor = new LightSensor();
		lightSensor.setLightSensorId(lid);
		lightSensorData.setLightSensor(lightSensor);
		lightSensorData.setLightDataCollectTime(new Timestamp(System
				.currentTimeMillis()));

		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(lightSensorData, LightSensorData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(lightSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	 @Ignore
	@Test
	public void testSaveGasSensorInfo() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/gasSensor");
		Builder request = webTarget.request();

		int gid = 2;
		GasSensorData gasSensorData = new GasSensorData();
		gasSensorData.setGasData(10f);
		GasSensor gasSensor = new GasSensor();
		gasSensor.setGasSensorId(gid);
		gasSensorData.setGasSensor(gasSensor);
		gasSensorData.setGasDataCollectTime(new Timestamp(System
				.currentTimeMillis()));

		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(gasSensorData, GasSensorData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(gasSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	 @Ignore
	@Test
	public void testSavePlrSensorInfo() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/plrSensor");
		Builder request = webTarget.request();

		int pid = 2;
		PlrSensorData plrSensorData = new PlrSensorData();
		plrSensorData.setPlrData(true);
		PlrSensor plrSensor = new PlrSensor();
		plrSensor.setPlrSensorId(pid);
		plrSensorData.setPlrSensor(plrSensor);
		plrSensorData.setPlrDataCollectTime(new Timestamp(System
				.currentTimeMillis()));

		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(plrSensorData, PlrSensorData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(plrSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	 @Ignore
	@Test
	public void testSaveTemperatureSensorInfo() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/sensorBox/temperatureSensor");
		Builder request = webTarget.request();

		int tid = 2;
		TemperatureSensorData temperatureSensorData = new TemperatureSensorData();
		temperatureSensorData.setTemperatureData(32f);
		
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		temperatureSensor.setTemperatureSensorId(tid);
		temperatureSensorData.setTemperatureSensor(temperatureSensor);
		temperatureSensorData.setTemperatureDataCollectTime(new Timestamp(
				System.currentTimeMillis()));

		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(temperatureSensorData,
				TemperatureSensorData.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(temperatureSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}
	 
	 
	 @Test
	 public void testQueryLightTwenty() throws JsonGenerationException,
		JsonMappingException, IOException {
		 
		 ClientConfig clientConfig = new ClientConfig();
		 clientConfig.register(GsonJsonProvider.class);
		 Client client = ClientBuilder.newClient(clientConfig);
		 Gson gson=GsonUtil.create();
		 WebTarget webTarget = client
					.target("http://"+HOST_IP+":8080/smarthome/sensorBox/lightEveryTwenty");
		 Builder request = webTarget.request();
		 Response response = request.get();
		 LightSensorData[] lightSensorDatas = response.readEntity(LightSensorData[].class);
		 System.out.println(gson.toJson(lightSensorDatas));
	 }
	 
	 
	 @Test
	 public void testQueryTemperatureTwenty() throws JsonGenerationException,
		JsonMappingException, IOException {
		 
		 ClientConfig clientConfig = new ClientConfig();
		 clientConfig.register(GsonJsonProvider.class);
		 Client client = ClientBuilder.newClient(clientConfig);
		 Gson gson=GsonUtil.create();
		 WebTarget webTarget = client
					.target("http://"+HOST_IP+":8080/smarthome/sensorBox/temperatureEveryTwenty");
		 Builder request = webTarget.request();
		 Response response = request.get();
		 TemperatureSensorData[] temperatureSensorDatas = response.readEntity(TemperatureSensorData[].class);
		 System.out.println(temperatureSensorDatas.length);
		 System.out.println(gson.toJson(temperatureSensorDatas));
	 }
	 
	 
	 @Test
	 public void testQueryPlrTwenty() throws JsonGenerationException,
		JsonMappingException, IOException {
		 
		 ClientConfig clientConfig = new ClientConfig();
		 clientConfig.register(GsonJsonProvider.class);
		 Client client = ClientBuilder.newClient(clientConfig);
		 Gson gson=GsonUtil.create();
		 WebTarget webTarget = client
					.target("http://"+HOST_IP+":8080/smarthome/sensorBox/plrEveryTwenty");
		 Builder request = webTarget.request();
		 Response response = request.get();
		 PlrSensorData[] plrSensorDatas = response.readEntity(PlrSensorData[].class);
		 System.out.println(plrSensorDatas.length);
		 System.out.println(gson.toJson(plrSensorDatas));
	 }
	 
	 


}
