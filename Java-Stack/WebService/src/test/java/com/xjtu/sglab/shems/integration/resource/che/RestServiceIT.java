package com.xjtu.sglab.shems.integration.resource.che;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.SocialSource;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.service.impl.OperateWearableService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class RestServiceIT {
	private static final String LOCAL_HOST_IP = "192.168.1.6";
	private static final String LOOP_IP = "localhost";
	private static final String REMOTE_HOST_IP = "202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	
 
	
	@Test
	@Ignore
	public void testSaveSocialInfo() throws JsonGenerationException,
			JsonMappingException, IOException {

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/socialInfo");
		Builder request = webTarget.request();

		SocialInfo socialInfo = new SocialInfo();
		SocialSource socialSource = new SocialSource();
		socialSource.setSourceTypeId(1);
		ActivityType activityType = new ActivityType();
		activityType.setActivityTypeId(2);
		socialInfo.setActivityType(activityType);
		socialInfo.setSocialSource(socialSource);

		socialInfo
				.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(socialInfo, SocialInfo.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(socialInfo,
				MediaType.APPLICATION_JSON));

		String str = response.readEntity(String.class);
		System.out.println(str);

	}

	@Test
//	@Ignore
	public void testSaveSocialInfoList() throws JsonGenerationException,
			JsonMappingException, IOException {

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/socialInfo/list");
		Builder request = webTarget.request();

		ArrayList<SocialInfo> socialInfoList=new ArrayList<SocialInfo>();
		SocialInfo socialInfo = new SocialInfo();
		SocialSource socialSource = new SocialSource();
		socialSource.setSourceTypeId(1);
		ActivityType activityType = new ActivityType();
		activityType.setActivityTypeId(1);
		socialInfo.setActivityType(activityType);
		socialInfo.setSocialSource(socialSource);
		socialInfo
				.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
		socialInfo.setStartTime("2015-08-09 14:00:00");
		socialInfoList.add(socialInfo);
		socialInfo
		.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
		socialInfo.setStartTime("2015-08-09 14:05:00");
		socialInfoList.add(socialInfo);
		
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(socialInfoList, socialInfoList.getClass());
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(socialInfoList,
				MediaType.APPLICATION_JSON));

		String str = response.readEntity(String.class);
		System.out.println(str);

	}
	
	
	
	@Test
	@Ignore
	public void testSaveGpsInfo() throws JsonGenerationException,
			JsonMappingException, IOException {

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/gpsInfo/saveGpsInfo");
		Builder request = webTarget.request();

		GpsInfo gpsInfo = new GpsInfo();
		gpsInfo.setGpsLatitude(101d + Math.random());
		gpsInfo.setGpsLongitude(200d + Math.random());
		gpsInfo.setGpsRecordTime(new Timestamp(System.currentTimeMillis()));
		gpsInfo.setDistanceFromHome(100f + (float) Math.random());
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(gpsInfo, GpsInfo.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(gpsInfo,
				MediaType.APPLICATION_JSON));

		String str = response.readEntity(String.class);
		System.out.println(str);

	}

	@Ignore
	@Test
	public void testSaveWearableInfo() throws JsonGenerationException,
			JsonMappingException, IOException {

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/wearableInfo/save");
		Builder request = webTarget.request();

		WearableDeviceInfo wearableDeviceInfo=new WearableDeviceInfo();
		MovingStatus movingStatus=new MovingStatus();
		movingStatus.setMovingType("unknown");
		wearableDeviceInfo.setMovingStatus(movingStatus);
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
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(wearableDeviceInfo, WearableDeviceInfo.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(wearableDeviceInfo,MediaType.APPLICATION_JSON));

		String str = response.readEntity(String.class);
		System.out.println(str);

	}
	@Ignore
	@Test
	public void testQueryWearableInfo() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/wearableInfo/query");
		Builder request = webTarget.request();

		Gson gson = GsonUtil.create();

		Response response = request.get();
		WearableDeviceInfo wearableDeviceInfo = response
				.readEntity(WearableDeviceInfo.class);
		System.out.println(gson.toJson(wearableDeviceInfo));
	}
	
	@Ignore
	@Test
	public void testQueryGpsInfo() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/gpsInfo/query");
		Builder request = webTarget.request();

		Gson gson = GsonUtil.create();

		Response response = request.get();
		GpsInfo gpsInfo = response
				.readEntity(GpsInfo.class);
		System.out.println(gson.toJson(gpsInfo));
		
	
	}
	
	
//	@Ignore
	@Test
	public void testQuerySocialInfo() throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/socialInfo/query");
		Builder request = webTarget.request();

		Gson gson = GsonUtil.create();

		Response response = request.get();
		SocialInfo socialInfo = response
				.readEntity(SocialInfo.class);
		System.out.println(gson.toJson(socialInfo));
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
