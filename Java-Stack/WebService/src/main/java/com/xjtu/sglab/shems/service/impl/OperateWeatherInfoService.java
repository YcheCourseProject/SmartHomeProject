package com.xjtu.sglab.shems.service.impl;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.sql.Timestamp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.model.weather.HeFengWeatherService;
import com.xjtu.sglab.shems.service.IOperateWeatherInfoService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

@Service
public class OperateWeatherInfoService implements IOperateWeatherInfoService {
	public static final String WEATHER_URL = "https://api.heweather.com/x3/weather";
	public static final String PARAM_CITY_ID = "cityid";
	public static final String PARM_KEY = "key";

	private HostnameVerifier getHostnameVerifier() {
		HostnameVerifier hv = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				// TODO Auto-generated method stub
				return true;
			}
		};
		return hv;
	}

	private SSLContext getSslContext() throws Exception {
		final SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

		} }, new SecureRandom());
		return sslContext;
	}

	@Override
	public HeFengWeatherService getWeatherByCityCode(String cityCode,
			String AppKey) throws Exception {
		// TODO Auto-generated method stub
		ClientConfig config = new DefaultClientConfig();  
		HostnameVerifier hv = getHostnameVerifier();
		SSLContext ctx = getSslContext();
		config.getProperties().put(
				HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
				new HTTPSProperties(hv, ctx));
		config.getClasses().add(GsonJsonProvider.class);
		Client client = Client.create(config);  
		client.setFollowRedirects(true);  
		WebResource resource = client.resource(WEATHER_URL)
				.queryParam(PARAM_CITY_ID, cityCode)
				.queryParam(PARM_KEY, AppKey);
		
		HeFengWeatherService weatherService=resource.get(HeFengWeatherService.class);
		return weatherService;
	}

}
