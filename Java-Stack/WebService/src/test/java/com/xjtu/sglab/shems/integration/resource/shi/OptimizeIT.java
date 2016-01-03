
package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.SocialSource;
import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class OptimizeIT {

	
	
	private static final String LOCAL_HOST_IP="192.168.1.13";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
//	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
	private static final String HOST_IP = LOOP_IP;           //本机测试用
	@Test
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
		activityType.setActivityTypeId(1);
		socialInfo.setActivityType(activityType);
		socialInfo.setSocialSource(socialSource);
		socialInfo.setStartTime(new Timestamp(System.currentTimeMillis()).toString());
//		socialInfo.setEndTime(new Timestamp(System.currentTimeMillis()).toString());
//		socialInfo.setStartTime("2015-08-08 22:00:00.341");
		socialInfo.setStartTime("2015-08-09 16:00:00");
		System.out.println(new Timestamp(System.currentTimeMillis()).toString()+"!");
		
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
	
}



