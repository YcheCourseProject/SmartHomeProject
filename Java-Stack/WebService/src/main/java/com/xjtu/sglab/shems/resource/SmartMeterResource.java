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

import com.xjtu.sglab.shems.entity.CircuitLine;
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.service.IOperateSmartMeterService;

@Component
@Path("smartMeter")
public class SmartMeterResource {
	private static final String IDS = "ids";
	@Resource
	IOperateSmartMeterService operateSmartMeterService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveSmartMeterInfo(ElectricityInfo electricityInfo) {
		try {
			operateSmartMeterService.saveSmartMeterInfo(electricityInfo);
			return Response.status(201)
					.entity("A new smartMeterInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getCause());
			return Response.status(202).entity("creation error").build();
		}
	}

	@GET
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response querySmartMeterData(@PathParam("id") Integer id) {
		try {
			ElectricityInfo electricityInfo = operateSmartMeterService
					.queryElectricityInfo(id);
			electricityInfo.getElectricityMeter().setElectricityInfos(null);
			
			CircuitLine circuitLine=electricityInfo.getElectricityMeter().getCircuitLine();
			if(circuitLine!=null){
				circuitLine.setCircuitLine(null);
				circuitLine.setCircuitLines(null);
				circuitLine.setElectricityMeter(null);
				circuitLine.setElectricityMeters(null);
				circuitLine.setRoom(null);
				
			}
			electricityInfo.getElectricityMeter().setCircuitLines(null);
			electricityInfo.getElectricityMeter().setElectricityType(null);

			return Response.status(201).entity(electricityInfo).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response querySmartMeterDataArray(
			@QueryParam(IDS) Set<Integer> electricityMeterIDSet) {
		try {
			ElectricityInfo[] electricityInfos = operateSmartMeterService
					.queryElectricityInfoArray(electricityMeterIDSet);
			for (ElectricityInfo electricityInfo : electricityInfos) {
				electricityInfo.getElectricityMeter().setElectricityInfos(null);
				electricityInfo.getElectricityMeter().setCircuitLines(null);
				electricityInfo.getElectricityMeter().setElectricityType(null);
				CircuitLine circuitLine=electricityInfo.getElectricityMeter().getCircuitLine();
				if(circuitLine!=null){
					circuitLine.setCircuitLine(null);
					circuitLine.setCircuitLines(null);
					circuitLine.setElectricityMeter(null);
					circuitLine.setElectricityMeters(null);
					circuitLine.setRoom(null);
				}
			}
			return Response.status(201).entity(electricityInfos).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	@GET
	@Path("meterEveryHour")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPlrSensorDataEveryTwentyMinutes() {
		try {
			ElectricityInfo[] electricityInfos = operateSmartMeterService
					.queryElectricityInfoEveryHour();
			return Response.status(201).entity(electricityInfos).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
