package com.xjtu.sglab.shems.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.model.weather.HeFengWeatherService;
import com.xjtu.sglab.shems.service.IOperateWeatherInfoService;

@Component
@Path("weather")
public class WeatherResource {
	@Resource
	IOperateWeatherInfoService operateWatherInfoService;
	@GET
	@Path("hefeng")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLampStatus() {
		try {
			HeFengWeatherService weatherService = operateWatherInfoService
					.getWeatherByCityCode("CN101110101", "ab9039c5c1524ae59aa99a8a2c99af8b");
			
			return Response.status(201).entity(weatherService).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

}
