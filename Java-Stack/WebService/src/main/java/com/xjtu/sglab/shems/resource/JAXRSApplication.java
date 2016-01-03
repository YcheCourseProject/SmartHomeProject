package com.xjtu.sglab.shems.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.xjtu.sglab.shems.resource.deprecated.CatResource;
import com.xjtu.sglab.shems.resource.deprecated.DogResource;
import com.xjtu.sglab.shems.resource.deprecated.HelloWorldResource;
import com.xjtu.sglab.shems.util.CORSResponseFilter;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.LoggingResponseFilter;


/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author ama
 * 
 */
public class JAXRSApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public JAXRSApplication() {
		//add JacksonJsonProvider
		register(GsonJsonProvider.class);	
		register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);		
		//register(JacksonJsonProvider.class);
		register(DogResource.class);
		register(CatResource.class);
		register(HelloWorldResource.class);
		
		register(SocialActivityResource.class);
		register(GpsInfoResource.class);
		register(WearableInfoResource.class);
		register(SensorBoxResource.class);
		register(SmartMeterResource.class);
		register(ConfigrationResource.class);
		register(QueryResource.class);
		register(ApplianceStatusResoure.class);
		register(WeatherResource.class);
		register(PriceResource.class);
		register(SocialDemandResource.class);
	}
}
