package com.xjtu.sglab.gateway.comm;

import java.sql.Timestamp;

import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Invocation.Builder;

public class CurtainCtrl {

	public static final String UP_URL = "http://192.168.1.25/gpio/ts/1?ac=123456&delay=5";
	public static final String STOP_URL = "http://192.168.1.25/gpio/ts/2?ac=123456&delay=5";
	public static final String DOWN_URL = "http://192.168.1.25/gpio/ts/3?ac=123456&delay=5";
	public static final int WHOLE_TIME = 35;

	public static void upCurtain() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(UP_URL);
		Builder request = webTarget.request();
		Response response = request.get();
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	public static void downCurtain() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(DOWN_URL);
		Builder request = webTarget.request();
		Response response = request.get();
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	public static void stopCurtain() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(STOP_URL);
		Builder request = webTarget.request();
		Response response = request.get();
		String str = response.readEntity(String.class);
		System.out.println(str);
	}

	

}
