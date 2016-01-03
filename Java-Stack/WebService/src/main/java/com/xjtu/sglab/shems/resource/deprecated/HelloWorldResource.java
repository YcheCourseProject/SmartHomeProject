package com.xjtu.sglab.shems.resource.deprecated;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xjtu.sglab.shems.dao.IGpsInfoDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.GpsInfoDAO;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LocationType;


@Path("helloworld")
public class HelloWorldResource {
	public static final String CLICHED_MESSAGE = "Hello World!";
	
	@Autowired
	private GpsInfoDAO gpsInfoDAO;
	@Resource
	EntityManagerHelper emh;
	@Transactional
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getHello() {
		emh.beginTransaction();
		GpsInfo gpsInfo=new GpsInfo();
		gpsInfo.setGpsLatitude(100d);
		gpsInfo.setGpsLongitude(200d);
		gpsInfo.setDistanceFromHome(1f);
 
		gpsInfo.setGpsRecordTime(new Timestamp(System.currentTimeMillis()));
		LocationType locationType=new  LocationType();
		locationType.setLocType("中英文hunda");
		locationType.setLocTypeId(1);
		gpsInfo.setLocationType(locationType);
		gpsInfoDAO.save(gpsInfo);
		emh.commit();
		return  Response.status(201)
				.entity(gpsInfo).build();
	}
	
 
}
