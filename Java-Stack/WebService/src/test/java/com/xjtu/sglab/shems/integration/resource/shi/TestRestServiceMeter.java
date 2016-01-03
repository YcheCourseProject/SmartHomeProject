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
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestRestServiceMeter {
	@Resource
    IBoxDAO BoxDAO;
	@Resource
	IRoomDAO RoomDAO;
	@Resource
	LampStatus LampStatusDAO;
	private static final String LOCAL_HOST_IP="192.168.1.6";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	
	@Ignore
	@Test
	public void testSaveMeterInfo() throws JsonGenerationException,JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/smartMeter");
		Builder request = webTarget.request();
		
		int electricityMeterId = 1;
		
		ElectricityInfo electricityInfo = new ElectricityInfo();
		electricityInfo.setActivePower(10f);
		electricityInfo.setElectricityInfoCollectTime(new Timestamp(System.currentTimeMillis()));
		electricityInfo.setReactivePower(20f);
		electricityInfo.setTotalConsumeEnergy(12f);
		ElectricityMeter tmpElectricityMeter = new ElectricityMeter();
		tmpElectricityMeter.setElectricityMeterId(electricityMeterId);
		electricityInfo.setElectricityMeter(tmpElectricityMeter);
		
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(electricityInfo, ElectricityInfo.class);
		System.out.println(tmpstr);
		Response response = request.post(Entity.entity(electricityInfo,
				MediaType.APPLICATION_JSON));

		String str = response.readEntity(String.class);
		System.out.println(str);
}
	
	@Ignore
	@Test
	public void testQuerySmartMeterInfo() throws JsonGenerationException,JsonMappingException,IOException{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/smartMeter/1");
		Builder request = webTarget.request();
		Gson gson = GsonUtil.create();
		Response response = request.get();
		ElectricityInfo electricityInfo = response.readEntity(ElectricityInfo.class);
		System.out.println(gson.toJson(electricityInfo));
	}
	
	 @Test
	 public void testQueryMeterEveryHour() throws JsonGenerationException,
		JsonMappingException, IOException {
		 
		 ClientConfig clientConfig = new ClientConfig();
		 clientConfig.register(GsonJsonProvider.class);
		 Client client = ClientBuilder.newClient(clientConfig);
		 Gson gson=GsonUtil.create();
		 WebTarget webTarget = client
					.target("http://"+HOST_IP+":8080/smarthome/smartMeter/meterEveryHour");
		 Builder request = webTarget.request();
		 Response response = request.get();
		 ElectricityInfo[] electricityInfos = response.readEntity(ElectricityInfo[].class);
		 System.out.println(gson.toJson(electricityInfos));
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}