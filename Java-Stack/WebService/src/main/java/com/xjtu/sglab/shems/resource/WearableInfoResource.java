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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.service.IOperateSocialService;
import com.xjtu.sglab.shems.service.IOperateWearableService;

@Component
@Path("wearableInfo")
public class WearableInfoResource {
	@Resource
	IOperateWearableService operateWearableService;
	
	@POST
	@Path("/save")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN})
	public Response saveSocialInfo(WearableDeviceInfo wearableDeviceInfo) {
		try{
			operateWearableService.saveWearableInfo(wearableDeviceInfo); 
			return Response.status(201)
					.entity("A new werableInfo record has been created").build();
		}
		catch(Exception e){
			return Response.status(202)
					.entity("creation error").build();
		}
		
	}
	
	

	@GET
	@Path("/query")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLightSensorData() {
		try {
			WearableDeviceInfo wearableDeviceInfo = operateWearableService.queryWearableInfo();		
			wearableDeviceInfo.getMovingStatus().setWearableDeviceInfos(null);
			wearableDeviceInfo.getMovingStatus().setWearableInfoToDemands(null);
			return Response.status(201).entity(wearableDeviceInfo).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
}
