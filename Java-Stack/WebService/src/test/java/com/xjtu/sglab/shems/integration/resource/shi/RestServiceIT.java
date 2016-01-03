package com.xjtu.sglab.shems.integration.resource.shi;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.impl.BoxDAO;
import com.xjtu.sglab.shems.dao.impl.LampDAO;
import com.xjtu.sglab.shems.dao.impl.LampStatusDAO;
import com.xjtu.sglab.shems.dao.impl.RoomDAO;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.CircuitLine;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.ElectricityType;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensor;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.MovingStatus;
import com.xjtu.sglab.shems.entity.PlrSensor;
import com.xjtu.sglab.shems.entity.RealTimeDecision;
import com.xjtu.sglab.shems.entity.Room;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.SocialSource;
import com.xjtu.sglab.shems.entity.TemperatureSensor;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterControlDetail;
import com.xjtu.sglab.shems.entity.WaterHeaterRealTimeDecision;
import com.xjtu.sglab.shems.entity.WaterHeaterStatus;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

public class RestServiceIT {
	@Resource
    IBoxDAO BoxDAO;
	@Resource
	IRoomDAO RoomDAO;
	@Resource
	LampStatus LampStatusDAO;
	private static final String LOCAL_HOST_IP="192.168.1.6";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP=LOOP_IP;
	

@Test
public void testConfigrationInfo() throws JsonGenerationException,JsonMappingException,IOException{
	ClientConfig clientConfig = new ClientConfig();
	clientConfig.register(GsonJsonProvider.class);

	Client client = ClientBuilder.newClient(clientConfig);

	WebTarget webTarget = client
			.target("http://"+HOST_IP+":8080/smarthome/configrationRoom");
	Builder request = webTarget.request();
	
	//配置信息
	int rommID = 1;
	int boxID = 1;
	int circuitLineId = 1;
	int electricityMeterId = 1;
	
	//配置room
	Room room = new Room();
	Set<Box> boxes = new HashSet<Box>();
	Box box = new Box();
	boxes.add(box);
	Set<CircuitLine> circuitLines = new HashSet<CircuitLine>();
	CircuitLine circuitLine = new CircuitLine();
	circuitLines.add(circuitLine);
	room.setBoxes(boxes);
    room.setCircuitLines(circuitLines);
    room.setRoomSize(35f);
    
    //配置外键
    Room tmproom = new Room();
    tmproom.setRoomId(rommID);
    Box tmpbox = new Box();
    tmpbox.setBoxId(boxID);
    CircuitLine tmpCircuitLine = new CircuitLine();
    tmpCircuitLine.setCircuitLineId(circuitLineId);
    ElectricityMeter tmpElectricityMeter = new ElectricityMeter();
    tmpElectricityMeter.setElectricityMeterId(electricityMeterId);
    
    //配置box
    box.setControlModelIp(HOST_IP);
    box.setDevelopmentBoardIp(LOCAL_HOST_IP); 
    box.setRoom(tmproom);
    Set<AirCondition> airConditions = new HashSet<AirCondition>();
    AirCondition airCondition = new AirCondition();
    airConditions.add(airCondition);
    box.setAirConditions(airConditions);
    
    Set<Curtain> curtains = new HashSet<Curtain>();
    Curtain curtain = new Curtain();
    curtains.add(curtain);
    box.setCurtains(curtains);
    
    Set<FlameSensor> flameSensors = new HashSet<FlameSensor>();
    FlameSensor flameSensor = new FlameSensor();
    flameSensors.add(flameSensor);
    box.setFlameSensors(flameSensors);
    
    Set<GasSensor> gasSensors = new HashSet<GasSensor>();
    GasSensor gasSensor = new GasSensor();
    gasSensors.add(gasSensor);
    box.setGasSensors(gasSensors);
    
    Set<Lamp> lamps = new HashSet<Lamp>();
    Lamp lamp = new Lamp();
    lamps.add(lamp);
    box.setLamps(lamps);
    
    Set<LightSensor> lightSensors = new HashSet<LightSensor>();
    LightSensor lightSensor = new LightSensor();
    lightSensors.add(lightSensor);
    box.setLightSensors(lightSensors);
    
    Set<PlrSensor> plrSensors = new HashSet<PlrSensor>();
    PlrSensor plrSensor = new PlrSensor();
    plrSensors.add(plrSensor);
    box.setPlrSensors(plrSensors);
    
    Set<TemperatureSensor> temperatureSensors = new HashSet<TemperatureSensor>();
    TemperatureSensor temperatureSensor = new TemperatureSensor();
    temperatureSensors.add(temperatureSensor);
    box.setTemperatureSensors(temperatureSensors);
    
    Set<WaterHeater> waterHeaters = new HashSet<WaterHeater>();
    WaterHeater waterHeater = new WaterHeater();
    waterHeaters.add(waterHeater);
    box.setWaterHeaters(waterHeaters);
    
	//配置box所管理的设备
    airCondition.setAirConditionIp(HOST_IP);
    airCondition.setAirConditionRatedPower(100f);
    airCondition.setBox(tmpbox);
    curtain.setCurtainIp(HOST_IP);
    curtain.setCurtainRatedPower(12f);
    curtain.setCurtainSize(23f);
    curtain.setBox(tmpbox);
    flameSensor.setBox(tmpbox);
    gasSensor.setBox(tmpbox);
    lamp.setBox(tmpbox);
    lamp.setLampLocation(10);
    lamp.setLampRatedPower(123f);
    lamp.setLampType(2);
    lightSensor.setBox(tmpbox);
    plrSensor.setBox(tmpbox);
    temperatureSensor.setBox(tmpbox);
    waterHeater.setBox(tmpbox);
    waterHeater.setWaterHeaterRatedPower(13f);
    
    //配置circuitLine
    circuitLine.setCircuitLineDescription("bedroom");
    circuitLine.setRoom(tmproom);
    circuitLine.setCircuitLine(tmpCircuitLine);
    circuitLine.setElectricityMeter(tmpElectricityMeter);
	
	Gson gson = GsonUtil.create();
	String tmpstr = gson.toJson(room, Room.class);
	System.out.println(tmpstr);	
	Response response = request.post(Entity.entity(room,MediaType.APPLICATION_JSON));
	String str = response.readEntity(String.class);
	System.out.println(str);
	
}

@Ignore
@Test
public void testQueryRoomData() throws JsonGenerationException,JsonMappingException,IOException{
	ClientConfig clientConfig = new ClientConfig();
	clientConfig.register(GsonJsonProvider.class);

	Client client = ClientBuilder.newClient(clientConfig);

	WebTarget webTarget = client
			.target("http://"+HOST_IP+":8080/smarthome/configrationRoom/room/1");
	Builder request = webTarget.request();
	
	Gson gson = GsonUtil.create();
	
	Response response = request.get();
	Room room = response.readEntity(Room.class);
	System.out.println(gson.toJson(room));
}
	


}