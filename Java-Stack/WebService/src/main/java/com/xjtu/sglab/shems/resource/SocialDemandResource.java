package com.xjtu.sglab.shems.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.SocialActivityToDemand;
import com.xjtu.sglab.shems.service.IOperateDemandService;

@Component
@Path("socialDemand")
public class SocialDemandResource {
	@Resource
	IOperateDemandService operateDemandService;
	
	@POST
	@Path("realTimeDemand")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response createRealTimeDemand(SocialActivityToDemand socialActivityToDemand) {
		try {
			operateDemandService.createRealTimeDemand(socialActivityToDemand);
			return Response.status(201)
					.entity("A new real-time demand has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}
	
	@GET
	@Path("modelResultShow")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPlrSensorDataEveryTwentyMinutes() {
		try {
			AirConditionRealTimeDecision[] airConditionRealTimeDecisions = operateDemandService
					.queryModelDecision();
			return Response.status(201).entity(airConditionRealTimeDecisions).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	
	

}
