package com.xjtu.sglab.shems.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.CurtainStatus;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.Room;
import com.xjtu.sglab.shems.service.IOperateConfigrationService;
import com.xjtu.sglab.shems.service.IOperateGpsService;

@Component
@Path("configrationRoom")
public class ConfigrationResource {
	@Resource
	IOperateConfigrationService operateConfigrationService;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN})
	public Response saveConfigration(Room room) {
		try{
			operateConfigrationService.saveConfigration(room);
			return Response.status(201)
					.entity("A new configration record has been created").build();
		}
		catch(Exception e){
			return Response.status(202)
					.entity("creation error").build();
		}		
	}
	
	@GET
	@Path("{roomid}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryCurtainStatus(@PathParam("id") Integer roomID) {
		try {
			Room room = operateConfigrationService
					.queryConfiguration(roomID);
			return Response.status(201).entity(room).build();
		} catch (Exception e) {
			return Response.status(202).entity("query error").build();
		}
	}
}
