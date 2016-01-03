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
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.SocialSource;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestMeterRestServiceIT {
	private static final String LOCAL_HOST_IP="192.168.1.13";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP=REMOTE_HOST_IP;
	
	
	@Test
	public void testQueryTemperatureSensorDataArray() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/smartMeter/list").queryParam("ids", 1);
		Builder request = webTarget.request();
		Response response = request.get();
		ElectricityInfo[] electricityInfos = response.readEntity(ElectricityInfo[].class);
		System.out.println(gson.toJson(electricityInfos));
	}
	

}
