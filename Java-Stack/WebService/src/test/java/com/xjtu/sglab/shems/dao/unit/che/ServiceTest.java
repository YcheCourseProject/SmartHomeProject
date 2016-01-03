package com.xjtu.sglab.shems.dao.unit.che;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.dao.ICircuitLineDAO;
import com.xjtu.sglab.shems.dao.IElectricityMeterDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.CircuitLine;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.Room;
import com.xjtu.sglab.shems.model.weather.HeFengWeatherService;
import com.xjtu.sglab.shems.service.IOperateConfigrationService;
import com.xjtu.sglab.shems.service.IOperateWeatherInfoService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class ServiceTest {
	public static final String WEATHER_URL="https://api.heweather.com/x3/weather";
	public static final String PARAM_CITY_ID="cityid";
	public static final String PARM_KEY="key";
	
	private static final String LOCAL_HOST_IP="192.168.1.6";
	private static final String LOOP_IP="localhost";
	private static final String REMOTE_HOST_IP="202.117.14.247";
	private static final String HOST_IP = REMOTE_HOST_IP;    //远程用
//	private static final String HOST_IP = LOOP_IP;           //本机测试用
	@Resource
	EntityManagerHelper emh;
	
	@Resource
	IOperateWeatherInfoService operateWatherInfoService;
	@Resource
	ICircuitLineDAO circuitLineDAO;
	@Resource
	IElectricityMeterDAO electricityMeterDAO;
	@Resource
	IOperateConfigrationService operateConfigrationService;
	
	@Ignore
	@Test
	public void testConfiguration() throws Exception{
		Room room=new Room();
		Set<Box> boxSet=new HashSet<Box>();
		Box box=new Box();
		box.setControlModelIp("192.158.1.432");
		box.setDevelopmentBoardIp("192.153.432.21");
		boxSet.add(box);
		room.setBoxes(boxSet);
		
		Set<Lamp> lampSet=new HashSet<Lamp>();
		box.setLamps(lampSet);
		lampSet.add(new Lamp());
		lampSet.add(new Lamp());
		Set<LightSensor> lightSensors=new HashSet<LightSensor>();
		box.setLightSensors(lightSensors);
		lightSensors.add(new LightSensor());
		lightSensors.add(new LightSensor());
		
		
		
		//..............
		
		Set<CircuitLine>  circuitLines=new HashSet<CircuitLine>();
		room.setCircuitLines(circuitLines);
		CircuitLine circuitLine=new CircuitLine();
		circuitLines.add(circuitLine);
		ElectricityMeter electricityMeter=new ElectricityMeter();
		electricityMeter.setElectricityMeterIp("192.168.1.1");
		circuitLine.setElectricityMeter(electricityMeter);

		
		ElectricityMeter electricityMeter2=new ElectricityMeter();
		electricityMeter2.setElectricityMeterIp("192.168.1.2");
		CircuitLine circuitLine2=new CircuitLine();
		circuitLine2.setCircuitLine(circuitLine);
		circuitLine2.setElectricityMeter(electricityMeter2);
		circuitLines.add(circuitLine2);
		//..........
		operateConfigrationService.saveConfigration(room);
	}
	
	
//	@Ignore
	@Test
	public void testWeatherServcie() throws Exception{
//		HeFengWeatherService service=operateWatherInfoService.getWeatherByCityCode("CN101110101", "ab9039c5c1524ae59aa99a8a2c99af8b");
//		Gson gson =GsonUtil.create();
//		System.out.println(gson.toJson(service));
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client
				.target("http://"+HOST_IP+":8080/smarthome/weather/hefeng");
		Builder request = webTarget.request();
		
		Gson gson = GsonUtil.create();
		
		Response response = request.get();
		HeFengWeatherService weatherService = response.readEntity(HeFengWeatherService.class);
		System.out.println(gson.toJson(weatherService));
		
		
		
	}
	
	@Ignore
	@Test
	public void testCircuitLineAndMeter() throws Exception{
		emh.beginTransaction();
		CircuitLine circuitLine=new CircuitLine();
		ElectricityMeter electricityMeter=new ElectricityMeter();
		electricityMeter.setElectricityMeterIp("192.168.1.1");
		circuitLine.setElectricityMeter(electricityMeter);
		electricityMeter.setCircuitLine(circuitLine);
		electricityMeterDAO.save(electricityMeter);
		circuitLineDAO.save(circuitLine);

		
		ElectricityMeter electricityMeter2=new ElectricityMeter();
		electricityMeter2.setElectricityMeterIp("192.168.1.2");
		CircuitLine circuitLine2=new CircuitLine();
		circuitLine2.setCircuitLine(circuitLine);
		circuitLine2.setElectricityMeter(electricityMeter2);
		electricityMeter2.setCircuitLine(circuitLine2);
		electricityMeterDAO.save(electricityMeter2);
		circuitLineDAO.save(circuitLine2);
		emh.commit();
	}
}
