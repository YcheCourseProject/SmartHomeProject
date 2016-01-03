package com.xjtu.sglab.shems.resource;

import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.UserAddress;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;
import com.xjtu.sglab.shems.service.IOperateGpsService;
@Component
@Path("gpsInfo")
public class GpsInfoResource {
	@Resource
	IOperateGpsService operateGpsService;
	
	@POST
	@Path("saveGpsInfo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN})
	public Response saveGpsInfo(GpsInfo gpsInfo) {
		try{
			operateGpsService.saveGpsInfo(gpsInfo);
		return Response.status(201)
					.entity("A new gpsinfo record has been created").build();
		}
		catch(Exception e){
			return Response.status(202)
					.entity("creation error").build();
		}	
	}
	
	@POST
	@Path("saveUserAddress")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN})
	public Response saveUserAddress(String address) {
		try{
			UserAddress userAddress = operateGpsService.saveUserAddress(address);	
			return Response.status(201)
					.entity(userAddress).build();
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
	public Response queryGpsData() {
		try {
			GpsInfo gpsInfo = operateGpsService.queryGpsInfo();		
			gpsInfo.getLocationType().setGpsInfos(null);
			gpsInfo.getLocationType().setGpsInfoToDemands(null);
			return Response.status(201).entity(gpsInfo).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	@GET
	@Path("{location}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response findShortest(@PathParam("location") String location) {
		try{
			ResultWithKey resultWithKey=operateGpsService.searchLocation(location);
			return Response.status(201)
					.entity(resultWithKey).build();
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
