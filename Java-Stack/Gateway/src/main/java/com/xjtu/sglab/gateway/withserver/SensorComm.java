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
import com.xjtu.sglab.gateway.entity.FlameSensor;
import com.xjtu.sglab.gateway.entity.FlameSensorData;
import com.xjtu.sglab.gateway.entity.GasSensor;
import com.xjtu.sglab.gateway.entity.GasSensorData;
import com.xjtu.sglab.gateway.entity.LightSensor;
import com.xjtu.sglab.gateway.entity.LightSensorData;
import com.xjtu.sglab.gateway.entity.PlrSensor;
import com.xjtu.sglab.gateway.entity.PlrSensorData;
import com.xjtu.sglab.gateway.entity.TemperatureSensor;
import com.xjtu.sglab.gateway.entity.TemperatureSensorData;
import com.xjtu.sglab.gateway.util.GsonJsonProvider;
import com.xjtu.sglab.gateway.entity.HumidityData;
import com.xjtu.sglab.gateway.util.GsonUtil;

public class SensorComm {
	private static final SensorComm sensorComm = null;

	public static SensorComm getInstance() {
		if (sensorComm == null)
			return new SensorComm();
		else
			return sensorComm;
	}

	public void saveFlameSensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
				+ ":8080/smarthome/sensorBox/flameSensor");
		Builder request = webTarget.request();
		FlameSensorData flameSensorData = new FlameSensorData();
		flameSensorData.setFlameData(data);
		FlameSensor flameSensor = new FlameSensor();
		flameSensor.setFlameSensorId(sensorID);
		flameSensorData.setFlameSensor(flameSensor);
		flameSensorData.setFlameDataCollectTime(new Timestamp(System
				.currentTimeMillis()));

		Response response = request.post(Entity.entity(flameSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	public void saveLightSensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
				+ ":8080/smarthome/sensorBox/lightSensor");
		Builder request = webTarget.request();
		LightSensorData lightSensorData = new LightSensorData();
		lightSensorData.setLightData(data);
		LightSensor lightSensor = new LightSensor();
		lightSensor.setLightSensorId(sensorID);
		lightSensorData.setLightSensor(lightSensor);
		lightSensorData.setLightDataCollectTime(new Timestamp(System
				.currentTimeMillis()));
		Response response = request.post(Entity.entity(lightSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	
	public void saveGasSensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
				+ ":8080/smarthome/sensorBox/gasSensor");
		Builder request = webTarget.request();
		GasSensorData gasSensorData = new GasSensorData();
		gasSensorData.setGasData(data);
		GasSensor gasSensor = new GasSensor();
		gasSensor.setGasSensorId(sensorID);
		gasSensorData.setGasSensor(gasSensor);
		gasSensorData.setGasDataCollectTime(new Timestamp(System
				.currentTimeMillis()));
		Response response = request.post(Entity.entity(gasSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	
	public void savePlrSensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
				+ ":8080/smarthome/sensorBox/plrSensor");
		Builder request = webTarget.request();
		PlrSensorData plrSensorData = new PlrSensorData();
		plrSensorData.setPlrData(true);
		PlrSensor plrSensor = new PlrSensor();
		plrSensor.setPlrSensorId(sensorID);
		plrSensorData.setPlrSensor(plrSensor);
		plrSensorData.setPlrDataCollectTime(new Timestamp(System
				.currentTimeMillis()));
		Response response = request.post(Entity.entity(plrSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}


	public void saveTemperatureSensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
				+ ":8080/smarthome/sensorBox/temperatureSensor");
		Builder request = webTarget.request();
		
		TemperatureSensorData temperatureSensorData = new TemperatureSensorData();
		temperatureSensorData.setTemperatureData(data);
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		temperatureSensor.setTemperatureSensorId(sensorID);
		temperatureSensorData.setTemperatureSensor(temperatureSensor);
		temperatureSensorData.setTemperatureDataCollectTime(new Timestamp(
				System.currentTimeMillis()));
		
		Response response = request.post(Entity.entity(temperatureSensorData,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}
	
	
	public void saveHumidtySensorInfo(float data, int sensorID) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + Constants.Server.IP
			+ ":8080/smarthome/sensorBox/humidty");
		Builder request = webTarget.request();

		HumidityData humidityData = new HumidityData();
		humidityData.setHumidityData(data);
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		temperatureSensor.setTemperatureSensorId(sensorID);
		humidityData.setTemperatureSensor(temperatureSensor);
		humidityData.setHumidityDataRecordTime(new Timestamp(System
		.currentTimeMillis()));
		
		
		Response response = request.post(Entity.entity(humidityData,
			MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println( str);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
