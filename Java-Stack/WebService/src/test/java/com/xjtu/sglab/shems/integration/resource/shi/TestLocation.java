package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensor;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.UserAddress;
import com.xjtu.sglab.shems.model.baidumap.DetailInfo;
import com.xjtu.sglab.shems.model.baidumap.POIResult;
import com.xjtu.sglab.shems.model.baidumap.MapResult;
import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;
import com.xjtu.sglab.shems.util.comparator.POIResultComparator;

public class TestLocation {

	
	private static final String LOCAL_HOST_IP="192.168.1.13";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP=LOOP_IP;
	
	
	@Ignore
	@Test
	public void testSearchLocation() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Gson gson=GsonUtil.create();
		
		String location = "34.285,108.969";
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/gpsInfo/"+"34.285,"+"108.969");
		
		Builder request = webTarget.request();
		Response response = request.get();
		ResultWithKey resultWithKey = response.readEntity(ResultWithKey.class);
		System.out.println(gson.toJson(resultWithKey));
	}
	
	@Test
	public void testSaveGpsInfo() throws JsonGenerationException,
		JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/gpsInfo/saveGpsInfo");
		Builder request = webTarget.request();
		
        GpsInfo gpsInfo = new GpsInfo();
        gpsInfo.setGpsLatitude(34.285);
        gpsInfo.setGpsLongitude(108.969);
        gpsInfo.setGpsRecordTime(new Timestamp(System.currentTimeMillis()));
        gpsInfo.setDistanceFromHome(104f);
		
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(gpsInfo, GpsInfo.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(gpsInfo,
				MediaType.APPLICATION_JSON));
		String str = response.readEntity(String.class);
		System.out.println(str);
	}
	
	@Test
	public void testSaveUserAddress() throws JsonGenerationException,
		JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/gpsInfo/saveUserAddress");
		Builder request = webTarget.request();
		
		String address = "西安交通大学";
		String city = "西安";
		
		
		Response response = request.post(Entity.entity(address,
				MediaType.APPLICATION_JSON));
		UserAddress userAddress = response.readEntity(UserAddress.class);
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(userAddress);
		System.out.println(tmpstr);
	}
	
	
	
	
	
	

}
