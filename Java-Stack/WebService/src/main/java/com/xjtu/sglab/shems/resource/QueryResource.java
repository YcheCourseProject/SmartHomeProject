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

import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.Room;
import com.xjtu.sglab.shems.service.IOperateQueryService;
import com.xjtu.sglab.shems.service.IOperateSensorBoxService;

@Component
@Path("queryRoom")
public class QueryResource {
	@Resource
	IOperateQueryService operateQueryService;
	
	@GET
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response queryRoom(@PathParam("id") Integer id) {
		try{
			LightSensorData lightSensorData=operateQueryService.queryRoom(id);
			return Response.status(201)
					.entity(lightSensorData).build();
		}
		catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202)
					.entity("query error").build();			
		}	
	}

}
