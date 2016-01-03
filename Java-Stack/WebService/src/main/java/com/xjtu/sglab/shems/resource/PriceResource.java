package com.xjtu.sglab.shems.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.service.IOperatePriceService;

@Component
@Path("price")

public class PriceResource {
	@Resource
	IOperatePriceService operatePriceService;
	
	@GET
	@Path("/month")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPrice() {
		try {
			DailyElectricityPrice dailyElectricityPrice = operatePriceService
					.queryPrice(8);
			return Response.status(201).entity(dailyElectricityPrice).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	

}
