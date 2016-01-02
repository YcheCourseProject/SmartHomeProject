package com.xjtu.sglab.gateway.withserver;

import java.sql.Timestamp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.xjtu.sglab.exp.Constants;
import com.xjtu.sglab.gateway.entity.ElectricityInfo;
import com.xjtu.sglab.gateway.entity.ElectricityMeter;
import com.xjtu.sglab.gateway.util.GsonJsonProvider;
import com.xjtu.sglab.gateway.util.GsonUtil;
 

public class MeterComm {
	private static final MeterComm meterComm=null;
	public static MeterComm getInstance(){
		if(meterComm==null)
			return new MeterComm();
		else
			return meterComm;
	}
	
	public void saveMeterInfo(ElectricityInfo electricityInfo,int electricityMeterId){
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client
				.target("http://"+Constants.Server.IP+":8080/smarthome/smartMeter");
		Builder request = webTarget.request();
		ElectricityMeter tmpElectricityMeter = new ElectricityMeter();
		tmpElectricityMeter.setElectricityMeterId(electricityMeterId);
		electricityInfo.setElectricityMeter(tmpElectricityMeter);
		Gson gson=GsonUtil.create();
		System.out.println(gson.toJson(electricityInfo));
		Response response = request.post(Entity.entity(electricityInfo,
				MediaType.APPLICATION_JSON));
		System.out.println(response.readEntity(String.class));
	}
}
