package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;




import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;


public class TestRestServicePrice {
	
	private static final String LOCAL_HOST_IP = "192.168.1.6";
	private static final String LOOP_IP = "localhost";
	private static final String REMOTE_HOST_IP = "202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	

	JsonSerializer<Date> ser = new JsonSerializer<Date>() {
		  @Override
		  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext 
		             context) {
		    return src == null ? null : new JsonPrimitive(src.getTime());
		  }
		};

		JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT,
					JsonDeserializationContext context) throws JsonParseException {
				 return json == null ? null : new Date(json.getAsLong());
			}
		};
	
	
	@Test
	public void testQueryPrice() throws JsonGenerationException,
			JsonMappingException, IOException {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://" + HOST_IP
				+ ":8080/smarthome/price/month");
		Builder request = webTarget.request();
		Gson gson = new GsonBuilder().create();
		Response response = request.get();
		DailyElectricityPrice dailyElectricityPrice = response.readEntity(DailyElectricityPrice.class);
		System.out.println(gson.toJson(dailyElectricityPrice));
	}
}











