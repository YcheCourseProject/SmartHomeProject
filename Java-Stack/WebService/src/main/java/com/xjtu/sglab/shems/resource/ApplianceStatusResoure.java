package com.xjtu.sglab.shems.resource;

import java.sql.Timestamp;
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

import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionStatus;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.CurtainStatus;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.SheSwitch;
import com.xjtu.sglab.shems.entity.SheSwitchStatus;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterStatus;
import com.xjtu.sglab.shems.service.IOperateApplianceService;
import com.xjtu.sglab.shems.service.IOperateSensorBoxService;

@Component
@Path("appliance")
public class ApplianceStatusResoure {
	private static final String IDS = "ids";
	@Resource
	IOperateApplianceService operateApplianceService;

	@POST
	@Path("lamp")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveLampInfo(LampStatus lampStatus) {
		try {
			lampStatus.setLampStatusId(null);
			lampStatus.setLampStatusRecordTime(new Timestamp(System.currentTimeMillis()));
			operateApplianceService.saveLampInfo(lampStatus);
			return Response.status(201)
					.entity("A new LampInfo record has been created").build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@POST
	@Path("curtain")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveCurtainInfo(CurtainStatus curtainStatus) {
		try {
			curtainStatus.setCurtainStatusId(null);
			curtainStatus.setCurtainStatusRecordTime(new Timestamp(System.currentTimeMillis()));
			operateApplianceService.saveCurtainInfo(curtainStatus);
			return Response.status(201)
					.entity("A new CurtainInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@POST
	@Path("sheSwitch")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveSheSwitchInfo(SheSwitchStatus sheSwitchStatus) {
		try {
			sheSwitchStatus.setSheSwitchStatusId(null);
			sheSwitchStatus.setSheSwitchStatusRecordTime(new Timestamp(System.currentTimeMillis()));
			operateApplianceService.saveSheSwitchInfo(sheSwitchStatus);
			return Response.status(201)
					.entity("A new SheSwitchInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@POST
	@Path("airCondition")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveAirConditionInfo(AirConditionStatus airConditionStatus) {
		try {
			airConditionStatus.setAirConditionStatusId(null);
			airConditionStatus.setAirConditionStatusRecordTime(new Timestamp(System.currentTimeMillis()));
			operateApplianceService.saveAirConditionInfo(airConditionStatus);
			return Response.status(201)
					.entity("A new AirConditionInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@POST
	@Path("waterHeater")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveWaterHeaterInfo(WaterHeaterStatus waterHeaterStatus) {
		try {
			waterHeaterStatus.setWaterHeaterStautsId(null);
			operateApplianceService.saveWaterHeaterInfo(waterHeaterStatus);
			return Response.status(201)
					.entity("A new WaterHeaterInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@GET
	@Path("/lamp/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLampStatus(@PathParam("id") Integer id) {
		try {
			LampStatus lampStatus = operateApplianceService.queryLampStatus(id);
			lampStatus.getLamp().setLampStatuses(null);
			lampStatus.getLamp().setBox(null);
			return Response.status(201).entity(lampStatus).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/curtain/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryCurtainStatus(@PathParam("id") Integer id) {
		try {
			CurtainStatus curtainStatus = operateApplianceService
					.queryCurtainStatus(id);
			curtainStatus.getCurtain().setCurtainStatuses(null);
			curtainStatus.getCurtain().setBox(null);
			return Response.status(201).entity(curtainStatus).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/sheSwitch/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response querySheSwitchStatus(@PathParam("id") Integer id) {
		try {
			SheSwitchStatus sheSwitchStatus = operateApplianceService
					.querySheSwitchStatus(id);
			sheSwitchStatus.getSheSwitch().setSheSwitchStatuses(null);
			return Response.status(201).entity(sheSwitchStatus).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/airCondition/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAirConditionStatus(@PathParam("id") Integer id) {
		try {
			AirConditionStatus airConditionStatus = operateApplianceService
					.queryAirConditionStatus(id);
			airConditionStatus.getAirCondition().setAirConditionStatuses(null);
			airConditionStatus.getAirCondition().setBox(null);
			airConditionStatus.getAirCondition().setAirConditionControlDetails(
					null);
			airConditionStatus.getAirCondition()
					.setAirConditionRealTimeDecisions(null);
			return Response.status(201).entity(airConditionStatus).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/waterHeater/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryWaterHeaterStatus(@PathParam("id") Integer id) {
		try {
			WaterHeaterStatus waterHeaterStatus = operateApplianceService
					.queryWaterHeaterStatus(id);
			waterHeaterStatus.getWaterHeater().setWaterHeaterStatuses(null);
			waterHeaterStatus.getWaterHeater().setWaterHeaterControlDetails(
					null);
			waterHeaterStatus.getWaterHeater().setWaterHeaterRealTimeDecisions(
					null);
			waterHeaterStatus.getWaterHeater().setBox(null);
			return Response.status(201).entity(waterHeaterStatus).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/lamp/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLampStatusArray(@QueryParam(IDS) Set<Integer> LampIDSet) {
		try {
			LampStatus[] lampStatusArray = operateApplianceService
					.queryLampStatusArray(LampIDSet);
			for (LampStatus lampStatus : lampStatusArray) {
				lampStatus.getLamp().setLampStatuses(null);
				lampStatus.getLamp().setBox(null);
			}
			return Response.status(201).entity(lampStatusArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/curtain/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryCurtainStatus(
			@QueryParam(IDS) Set<Integer> CurtainIDSet) {
		try {
			CurtainStatus[] curtainStatusArray = operateApplianceService
					.queryCurtainStatusArray(CurtainIDSet);
			for (CurtainStatus curtainStatus : curtainStatusArray) {
				curtainStatus.getCurtain().setCurtainStatuses(null);
				curtainStatus.getCurtain().setBox(null);
			}
			return Response.status(201).entity(curtainStatusArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/sheSwitch/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response querySheSwitchStatus(
			@QueryParam(IDS) Set<Integer> SheSwitchIDSet) {
		try {
			SheSwitchStatus[] sheSwitchStatusArray = operateApplianceService
					.querySheSwitchStatusArray(SheSwitchIDSet);
			for (SheSwitchStatus sheSwitchStatus : sheSwitchStatusArray) {
				sheSwitchStatus.getSheSwitch().setSheSwitchStatuses(null);
			}
			return Response.status(201).entity(sheSwitchStatusArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/airCondition/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAirConditionStatus(
			@QueryParam(IDS) Set<Integer> AirConditionIDSet) {
		try {
			AirConditionStatus[] airConditionStatusArray = operateApplianceService
					.queryAirConditionStatusArray(AirConditionIDSet);
			for (AirConditionStatus airConditionStatus : airConditionStatusArray) {
				airConditionStatus.getAirCondition().setAirConditionStatuses(
						null);
				airConditionStatus.getAirCondition().setAirConditionStatuses(null);
				airConditionStatus.getAirCondition().setBox(null);
				airConditionStatus.getAirCondition().setAirConditionControlDetails(
						null);
				airConditionStatus.getAirCondition()
						.setAirConditionRealTimeDecisions(null);
			}
			return Response.status(201).entity(airConditionStatusArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/waterHeater/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryWaterHeaterStatus(
			@QueryParam(IDS) Set<Integer> WaterHeaterIDSet) {
		try {
			WaterHeaterStatus[] waterHeaterStatusArray = operateApplianceService
					.queryWaterHeaterStatusArray(WaterHeaterIDSet);
			for (WaterHeaterStatus waterHeaterStatus : waterHeaterStatusArray) {
				waterHeaterStatus.getWaterHeater().setWaterHeaterStatuses(null);
				waterHeaterStatus.getWaterHeater()
						.setWaterHeaterControlDetails(null);
				waterHeaterStatus.getWaterHeater()
						.setWaterHeaterRealTimeDecisions(null);
				waterHeaterStatus.getWaterHeater().setBox(null);
			}
			return Response.status(201).entity(waterHeaterStatusArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

}
