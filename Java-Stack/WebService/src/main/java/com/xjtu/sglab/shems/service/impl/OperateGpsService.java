package com.xjtu.sglab.shems.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.GpsInfoDAO;
import com.xjtu.sglab.shems.dao.impl.LocationTypeDAO;
import com.xjtu.sglab.shems.dao.impl.UserAddressDAO;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.LocationType;
import com.xjtu.sglab.shems.entity.UserAddress;
import com.xjtu.sglab.shems.model.baidumap.POIResult;
import com.xjtu.sglab.shems.model.baidumap.MapResult;
import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;
import com.xjtu.sglab.shems.model.locTolat.LatLocation;
import com.xjtu.sglab.shems.model.locTolat.LngLatResult;
import com.xjtu.sglab.shems.model.locTolat.LatResult;
import com.xjtu.sglab.shems.service.IOperateGpsService;
import com.xjtu.sglab.shems.util.GsonJsonProvider;
import com.xjtu.sglab.shems.util.GsonUtil;
import com.xjtu.sglab.shems.util.comparator.POIResultComparator;

@Service
public class OperateGpsService implements IOperateGpsService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf ;
	@Resource
	LocationTypeDAO locationTypeDAO;
	@Resource
	GpsInfoDAO gpsInfoDAO;
	@Resource
	UserAddressDAO userAddressDAO;
	
	private static final String LOCATION_URL = "http://api.map.baidu.com/place/v2/search";
	private static final String GEOCODER_URL = "http://api.map.baidu.com/geocoder/v2/";   //记得在最后加上/
	
	private static final String PARAM_AK="ak";
	private static final String PARAM_OUTPUT = "output";
	private static final String PARAM_QUERY = "query";
	private static final String PARAM_PAGE_SIZE = "page_size";
	private static final String PARAM_PAGE_NUM = "page_num";
	private static final String PARAM_SCOPE = "scope";
	private static final String PARAM_LOCATION = "location";
	private static final String PARAM_RADIUS = "radius";
	private static final String PARAM_ADDRESS = "address";
	private static final String PARAM_CITY = "city";
	
	private static final String GYM = "%E4%BD%93%E8%82%B2%E5%9C%BA%E6%89%80";
	private static final String SCHOOL = "%E5%AD%A6%E6%A0%A1";
	private static final String RESTAURANT = "%E9%A4%90%E5%8E%85";
	private static final String HOSPITAL = "%E5%8C%BB%E9%99%A2";
	private static final String MARKET = "%E5%95%86%E5%9C%BA";
	
	private static final String GYM_KEY = "体育场所";
	private static final String SCHOOL_KEY= "学校";
	private static final String RESTAURANT_KEY = "饭店";
	private static final String HOSPITAL_KEY = "医院";
	private static final String MARKET_KEY= "商店";
	
	@Override
	public void saveGpsInfo(GpsInfo gpsInfo) throws Exception {	
		if(gpsInfo.getGpsLatitude()!=null&&gpsInfo.getGpsLongitude()!=null
				&&gpsInfo.getGpsRecordTime()!=null&&gpsInfo.getDistanceFromHome()!=null){
			emh.beginTransaction();		
//			LocationType locationType=locationTypeDAO.findById(getLocationType(gpsInfo));
			LocationType locationType=locationTypeDAO.findById(1);
			gpsInfo.setLocationType(locationType);		
			gpsInfoDAO.save(gpsInfo);
			emh.commit();			
		}
		else{
			throw new Exception();
		}	
	}
	
	
	
	
	@Override
	public UserAddress saveUserAddress(String address) throws Exception {
		
		LngLatResult myLngLatResult = searchLngLat(address);
		LatResult latResult = myLngLatResult.getResult();
		LatLocation latLocation = latResult.getLocation();
		float lat = latLocation.getLat();
		float lng = latLocation.getLng();
		UserAddress userAddress = new UserAddress();
		userAddress.setUserAddressLatitude(lat);
		userAddress.setUserAddressLongitude(lng);
		userAddress.setUserAddressDetail("home");
		
		emh.beginTransaction();	
		userAddressDAO.save(userAddress);
		emh.commit();
		return userAddress;
	
	}
	
	public LngLatResult searchLngLat(String address) throws Exception{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client
				.target(GEOCODER_URL).queryParam(PARAM_AK,"lUgdacuVNGH8GqOIdMeXOnHR")
				.queryParam(PARAM_OUTPUT, "json")
				.queryParam(PARAM_ADDRESS, address).queryParam(PARAM_CITY, "西安");
		Builder request = webTarget.request();
		Response response = request.get();
		LngLatResult lngLatResult = response.readEntity(LngLatResult.class);
		return lngLatResult;
	}
	
	
	
	private int getLocationType(GpsInfo gpsInfo) throws Exception{
		String latitude = Double.toString(gpsInfo.getGpsLatitude());
		String longitude = Double.toString(gpsInfo.getGpsLongitude());
		String location = latitude+","+longitude;
		System.out.println(location);
		ResultWithKey resultWithKey = searchLocation(location);
		String key = resultWithKey.getKey();
		System.out.println(key);
		List<LocationType> locationTypeList = locationTypeDAO.findByProperty("locType", key);
		LocationType locationType = locationTypeList.get(locationTypeList.size()-1);
		int locationTypeId = locationType.getLocTypeId();
		return locationTypeId;
	}

	@Override
	public ResultWithKey searchLocation(String location)
			throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(GsonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		
	    String locationType = GYM;
	    String key;
	    int[] shortest = new int[5];
	    List<ResultWithKey> resultWithKeys=new ArrayList<ResultWithKey>();
		for(int j=0;j<5;j++){
			switch(j){
			case 0:locationType = GYM;key=GYM_KEY;break;
			case 1:locationType = SCHOOL;key=SCHOOL_KEY;break;
			case 2:locationType = RESTAURANT;key=RESTAURANT_KEY;break;
			case 3:locationType = HOSPITAL;key=HOSPITAL_KEY;break;
			case 4:locationType = MARKET;key=MARKET_KEY;break;
			default:locationType = GYM;key=GYM_KEY;break;}
			WebTarget webTarget = client
					.target(LOCATION_URL).queryParam(PARAM_AK, "Upd50Bk9D3aooKWM9SxHm7Gh").queryParam(PARAM_OUTPUT, "json")
					.queryParam(PARAM_QUERY, locationType).queryParam(PARAM_PAGE_SIZE, 10).queryParam(PARAM_PAGE_NUM, 0)
					.queryParam(PARAM_SCOPE, 2).queryParam(PARAM_LOCATION, location)
					.queryParam(PARAM_RADIUS, 500);
			Builder request = webTarget.request();
			Response response = request.get();
			POIResult myPOIResult = response.readEntity(POIResult.class);
			List<MapResult> results=myPOIResult.getResults();
			for(MapResult result:results)
			{
				resultWithKeys.add(new ResultWithKey(result, key));
			}
		}

		ResultWithKey resultKey=orderLocation(resultWithKeys);
		return resultKey;
	}
	
	private ResultWithKey orderLocation(List<ResultWithKey> resultList){
		
		Collections.sort(resultList,new POIResultComparator());
		return resultList.get(0);
	}

	@Override
	public GpsInfo queryGpsInfo() throws Exception {
	    EntityManager em = emh.getEntityManager();
        String sql = "SELECT * from gps_info ORDER BY gps_id desc limit 1";      
        javax.persistence.Query query = em.createNativeQuery(sql,GpsInfo.class); 
        List<GpsInfo> userList = query.getResultList();        
        GpsInfo gpsInfo = new GpsInfo();
        gpsInfo = userList.get(userList.size()-1);	        
      
        em.close(); 
        return gpsInfo;	
	}


	
}
