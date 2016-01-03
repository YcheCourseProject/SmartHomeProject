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

import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.HumidityData;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.service.IOperateSensorBoxService;

@Component
@Path("sensorBox")
public class SensorBoxResource {
	private static final String IDS = "ids";

	@Resource
	IOperateSensorBoxService operateSensorBoxService;

	@POST
	@Path("box")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveSensorBoxInfo(Box box) {
		try {
			operateSensorBoxService.saveSensorBoxInfo(box);
			return Response.status(201)
					.entity("A new sensorBoxInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("creation error").build();
		}
	}

	@POST
	@Path("flameSensor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveFlameSensorInfo(FlameSensorData flameSensorData) {
		try {
			operateSensorBoxService.saveFlameSensorDataInfo(flameSensorData);
			return Response
					.status(201)
					.entity("A new flameSensorDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}

	@POST
	@Path("lightSensor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveLightSensorInfo(LightSensorData lightSensorData) {
		try {
			operateSensorBoxService.saveLightSensorDataInfo(lightSensorData);
			return Response
					.status(201)
					.entity("A new lightSensorDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}

	@POST
	@Path("gasSensor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveGasSensorInfo(GasSensorData gasSensorData) {
		try {
			operateSensorBoxService.saveGasSensorDataInfo(gasSensorData);
			return Response.status(201)
					.entity("A new gasSensorDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}

	@POST
	@Path("plrSensor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response savePlrSensorInfo(PlrSensorData plrSensorData) {
		try {
			operateSensorBoxService.savePlrSensorDataInfo(plrSensorData);
			return Response.status(201)
					.entity("A new plrSensorDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}

	@POST
	@Path("temperatureSensor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveTemperatureSensorInfo(
			TemperatureSensorData temperatureSensorData) {
		try {
			operateSensorBoxService
					.saveTemperatureSensorDataInfo(temperatureSensorData);
			return Response
					.status(201)
					.entity("A new temperatureSensorDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}
	
	@POST
	@Path("humidty")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveHumidityInfo(HumidityData humidityData) {
		try {
			operateSensorBoxService.saveHumidityDataInfo(humidityData);
			return Response.status(201)
					.entity("A new humidtyDataInfo record has been created")
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("create error").build();
		}
	}
	

	@GET
	@Path("/lightsensor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLightSensorData(@PathParam("id") Integer id) {
		try {
			LightSensorData lightSensorData = operateSensorBoxService
					.queryLightSensorData(id);
			lightSensorData.getLightSensor().setLightSensorDatas(null);
			lightSensorData.getLightSensor().setBox(null);
			return Response.status(201).entity(lightSensorData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	@GET
	@Path("lightEveryTwenty")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLightSensorData() {
		try {
			LightSensorData[] lightSensorDatas = operateSensorBoxService
					.queryLightEveryTwentyMinutes();
			return Response.status(201).entity(lightSensorDatas).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	@GET
	@Path("temperatureEveryTwenty")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryTemperatureSensorDataEveryTwentyMinutes() {
		try {
			TemperatureSensorData[] temperatureSensorDatas = operateSensorBoxService
					.queryTemperatureEveryTwentyMinutes();
			return Response.status(201).entity(temperatureSensorDatas).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	@GET
	@Path("plrEveryTwenty")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPlrSensorDataEveryTwentyMinutes() {
		try {
			PlrSensorData[] plrSensorDatas = operateSensorBoxService
					.queryPlrEveryTwentyMinutes();
			return Response.status(201).entity(plrSensorDatas).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	
	
	
	

	@GET
	@Path("/flamesensor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryFlameSensorData(@PathParam("id") Integer id) {
		try {
			FlameSensorData flameSensorData = operateSensorBoxService
					.queryFlameSensorData(id);
			flameSensorData.getFlameSensor().setBox(null);
			flameSensorData.getFlameSensor().setFlameSensorDatas(null);
			return Response.status(201).entity(flameSensorData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/gassensor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryGasSensorData(@PathParam("id") Integer id) {
		try {
			GasSensorData gasSensorData = operateSensorBoxService
					.queryGasSensorData(id);
			gasSensorData.getGasSensor().setGasSensorDatas(null);
			gasSensorData.getGasSensor().setBox(null);
			return Response.status(201).entity(gasSensorData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/plrsensor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPlrSensorData(@PathParam("id") Integer id) {
		try {
			PlrSensorData plrSensorData = operateSensorBoxService
					.queryPlrSensorData(id);
			plrSensorData.getPlrSensor().setPlrSensorDatas(null);
			plrSensorData.getPlrSensor().setBox(null);
			return Response.status(201).entity(plrSensorData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/temperaturesensor/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryTemperatureSensorData(@PathParam("id") Integer id) {
		try {
			TemperatureSensorData temperatureSensorData = operateSensorBoxService
					.queryTemperatureSensorData(id);
			temperatureSensorData.getTemperatureSensor()
					.setTemperatureSensorDatas(null);
			temperatureSensorData.getTemperatureSensor().setBox(null);
			temperatureSensorData.getTemperatureSensor().setHumidityDatas(null);
			return Response.status(201).entity(temperatureSensorData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	

	@GET
	@Path("/humidity/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryHumidityData(@PathParam("id") Integer id) {
		try {
			HumidityData humidityData = operateSensorBoxService
					.queryHumidityData(id);
			humidityData.getTemperatureSensor().setBox(null);
			humidityData.getTemperatureSensor().setHumidityDatas(null);
			humidityData.getTemperatureSensor().setTemperatureSensorDatas(null);		
			return Response.status(201).entity(humidityData).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/lightsensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryLightSensorDataArray(
			@QueryParam(IDS) Set<Integer> lightSensorIDSet) {
		try {
			LightSensorData[] lightSensorDataArray = operateSensorBoxService
					.queryLightSensorDataSet(lightSensorIDSet);
			for (LightSensorData lightSensorData : lightSensorDataArray) {
				lightSensorData.getLightSensor().setLightSensorDatas(null);
				lightSensorData.getLightSensor().setBox(null);
			}
			return Response.status(201).entity(lightSensorDataArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/flamesensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryFlameSensorDataArray(
			@QueryParam(IDS) Set<Integer> flameSensorIDSet) {
		try {
			FlameSensorData[] flameSensorDataArray = operateSensorBoxService
					.queryFlameSensorDataSet(flameSensorIDSet);
			for (FlameSensorData flameSensorData : flameSensorDataArray) {
				flameSensorData.getFlameSensor().setBox(null);
				flameSensorData.getFlameSensor().setFlameSensorDatas(null);
			}
			return Response.status(201).entity(flameSensorDataArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/gassensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryGasSensorDataArray(
			@QueryParam(IDS) Set<Integer> gasSensorIDSet) {
		try {
			GasSensorData[] gasSensorDataArray = operateSensorBoxService
					.queryGasSensorDataSet(gasSensorIDSet);
			for (GasSensorData gasSensorData : gasSensorDataArray) {
				gasSensorData.getGasSensor().setGasSensorDatas(null);
				gasSensorData.getGasSensor().setBox(null);
			}
			return Response.status(201).entity(gasSensorDataArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/plrsensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPlrSensorDataArray(
			@QueryParam(IDS) Set<Integer> plrSensorIDSet) {
		try {
			PlrSensorData[] plrSensorDataArray = operateSensorBoxService
					.queryPlrSensorDataSet(plrSensorIDSet);
			for (PlrSensorData plrSensorData : plrSensorDataArray) {
				plrSensorData.getPlrSensor().setPlrSensorDatas(null);
				plrSensorData.getPlrSensor().setBox(null);
			}
			return Response.status(201).entity(plrSensorDataArray).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}

	@GET
	@Path("/temperaturesensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryTemperatureSensorDataArray(
			@QueryParam(IDS) Set<Integer> temperatureSensorIDSet) {
		try {
			TemperatureSensorData[] temperatureSensorDataArray = operateSensorBoxService
					.queryTemperatureSensorDataSet(temperatureSensorIDSet);
			for (TemperatureSensorData temperatureSensorData : temperatureSensorDataArray) {
				temperatureSensorData.getTemperatureSensor()
						.setTemperatureSensorDatas(null);
				temperatureSensorData.getTemperatureSensor().setBox(null);
				temperatureSensorData.getTemperatureSensor().setHumidityDatas(null);
			}
			return Response.status(201).entity(temperatureSensorDataArray)
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	@GET
	@Path("/humidtysensor/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryHumidtySensorDataArray(
			@QueryParam(IDS) Set<Integer> temperatureSensorIDSet) {
		try {
			HumidityData[] humiditySensorDataArray = operateSensorBoxService
					.queryHumidityDataSet(temperatureSensorIDSet);
			for (HumidityData humidityData : humiditySensorDataArray) {
				humidityData.getTemperatureSensor().setBox(null);
				humidityData.getTemperatureSensor().setHumidityDatas(null);
				humidityData.getTemperatureSensor().setTemperatureSensorDatas(null);
			}
			return Response.status(201).entity(humiditySensorDataArray)
					.build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	
	
	
	
	
	
	

}
