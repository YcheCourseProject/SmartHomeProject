package com.xjtu.sglab.shems.service.impl;

 
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.IAirConditionControlDetailDAO;
import com.xjtu.sglab.shems.dao.IAirConditionDAO;
import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.IFlameSensorDAO;
import com.xjtu.sglab.shems.dao.IFlameSensorDataDAO;
import com.xjtu.sglab.shems.dao.IGasSensorDAO;
import com.xjtu.sglab.shems.dao.IGasSensorDataDAO;
import com.xjtu.sglab.shems.dao.ILightSensorDAO;
import com.xjtu.sglab.shems.dao.ILightSensorDataDAO;
import com.xjtu.sglab.shems.dao.IPlrSensorDAO;
import com.xjtu.sglab.shems.dao.IPlrSensorDataDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.ITemperatureSensorDAO;
import com.xjtu.sglab.shems.dao.ITemperatureSensorDataDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.FlameSensorDataDAO;
import com.xjtu.sglab.shems.dao.impl.GasSensorDataDAO;
import com.xjtu.sglab.shems.dao.impl.HumidityDataDAO;
import com.xjtu.sglab.shems.dao.impl.LightSensorDataDAO;
import com.xjtu.sglab.shems.dao.impl.PlrSensorDataDAO;
import com.xjtu.sglab.shems.dao.impl.TemperatureSensorDataDAO;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.HumidityData;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;
import com.xjtu.sglab.shems.service.IOperateSensorBoxService;

@Service
public class OperateSensorBoxService implements IOperateSensorBoxService {
	@Resource
	EntityManagerHelper emh;
	@Resource 
	IBoxDAO BoxDAO;
	@Resource
	EntityManagerFactory emf ;

	@Override
    public void saveSensorBoxInfo(Box box) throws Exception {
		if(box.getControlModelIp()!=null&&box.getDevelopmentBoardIp()!=null
				&&box.getRoom()!=null){
			emh.beginTransaction();	
			BoxDAO.save(box);
			emh.commit();
		}
		else{
			throw new Exception();		
		}		
	}

	


	@Override
	public LightSensorData queryLightSensorData(int LightSensorID) throws Exception {
		    EntityManager em = emf.createEntityManager();
	        String sql = "SELECT * from light_sensor_data where light_sensor_id="+LightSensorID+" ORDER BY light_data_id desc limit 1";      
	        javax.persistence.Query query = em.createNativeQuery(sql,LightSensorData.class); 
	        List<LightSensorData> userList = query.getResultList();        
	        LightSensorData lightSensorData = new LightSensorData();
	        lightSensorData = userList.get(userList.size()-1);	        
	        System.out.println(lightSensorData.getLightData());
	        em.close(); 
	        return lightSensorData;
	}


	@Override
	public FlameSensorData queryFlameSensorData(int FlameSensorID) throws Exception {
		 EntityManager em = emf.createEntityManager();
	     String sql = "SELECT * from flame_sensor_data where flame_sensor_id="+FlameSensorID+" ORDER BY flame_data_id desc limit 1";      
	     javax.persistence.Query query = em.createNativeQuery(sql,FlameSensorData.class); 
	     List<FlameSensorData> userList = query.getResultList();        
	     FlameSensorData flameSensorData = new FlameSensorData();
	     flameSensorData = userList.get(userList.size()-1);	        
	     System.out.println(flameSensorData.getFlameData());
	     em.close(); 
	     return flameSensorData;
	}
	
	@Override
	public GasSensorData queryGasSensorData(int GasSensorID) throws Exception {
		 EntityManager em = emf.createEntityManager();
	     String sql = "SELECT * from gas_sensor_data where gas_sensor_id="+GasSensorID+" ORDER BY gas_data_id desc limit 1"; 
	     javax.persistence.Query query = em.createNativeQuery(sql,GasSensorData.class); 
	     List<GasSensorData> userList = query.getResultList();
	     System.out.println(userList.size());
	     GasSensorData gasSensorData = new GasSensorData();
	     gasSensorData = userList.get(userList.size()-1);	        
	     System.out.println(gasSensorData.getGasData());
	     em.close(); 
	     return gasSensorData;
	}
	@Override
	public PlrSensorData queryPlrSensorData(int PlrSensorID) throws Exception {
		 EntityManager em = emf.createEntityManager();
	     String sql = "SELECT * from plr_sensor_data where plr_sensor_id="+PlrSensorID+" ORDER BY plr_data_id desc limit 1";      
	     javax.persistence.Query query = em.createNativeQuery(sql,PlrSensorData.class); 
	     List<PlrSensorData> userList = query.getResultList();        
	     PlrSensorData plrSensorData = new PlrSensorData();
	     plrSensorData = userList.get(userList.size()-1);	        
	     System.out.println(plrSensorData.getPlrData());
	     em.close(); 
	     return plrSensorData;
	}
	@Override
	public TemperatureSensorData queryTemperatureSensorData(int TemperatureSensorID) throws Exception {
		 EntityManager em = emf.createEntityManager();
	     String sql = "SELECT * from temperature_sensor_data where temperature_sensor_id="+TemperatureSensorID+" ORDER BY temperature_data_id desc limit 1";      
	     javax.persistence.Query query = em.createNativeQuery(sql,TemperatureSensorData.class); 
	     List<TemperatureSensorData> userList = query.getResultList();        
	     TemperatureSensorData temperatureSensorData = new TemperatureSensorData();
	     temperatureSensorData = userList.get(userList.size()-1);	        
	     System.out.println(temperatureSensorData.getTemperatureData());
	     em.close(); 
	     return temperatureSensorData;
	}
	@Override
	public HumidityData queryHumidityData(int TemperatureSensorID)
			throws Exception {
		 EntityManager em = emf.createEntityManager();
	     String sql = "SELECT * from humidity_data where temperature_sensor_id="+TemperatureSensorID+" ORDER BY humidity_data_id desc limit 1";
	     javax.persistence.Query query = em.createNativeQuery(sql,HumidityData.class); 
	     List<HumidityData> userList = query.getResultList();        
	     HumidityData humidityData = new HumidityData();
	     humidityData = userList.get(userList.size()-1);	        
	     System.out.println(humidityData.getHumidityData());
	     em.close(); 
	     return humidityData;
	}
	
	@Override
	public LightSensorData[] queryLightEveryTwentyMinutes() throws Exception {
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
	    String sql = "SELECT * FROM (SELECT *,DATE(light_data_collect_time) AS d,"
	    		+ "SUBSTRING(light_data_collect_time,15,2) as h FROM light_sensor_data"
	    		+ " WHERE light_sensor_id=1 AND date(light_data_collect_time)=curdate()"
	    		+ " ORDER BY light_sensor_id,light_data,light_data_collect_time DESC ) light_sensor_data"
	    		+ " WHERE  h='20' OR h='40' GROUP BY light_sensor_id,light_data,d,h ;";
	    javax.persistence.Query query = em.createNativeQuery(sql,LightSensorData.class); 
        List<LightSensorData> userList = query.getResultList();
        LightSensorData[] lightSensorDatas =  new LightSensorData[userList.size()];   
        System.out.println(userList.size());
        for(int index=0;index<userList.size();index++){
        	lightSensorDatas[index] = userList.get(index);
        	lightSensorDatas[index].getLightSensor().setBox(null);
        	lightSensorDatas[index].getLightSensor().setLightSensorDatas(null);
        }        
        emh.commit();
		em.close();
		return lightSensorDatas;
	}


	@Override
	public PlrSensorData[] queryPlrEveryTwentyMinutes() throws Exception {
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
	    String sql = "SELECT * FROM (SELECT *,DATE(plr_data_collect_time) AS d,"
	    		+ "SUBSTRING(plr_data_collect_time,15,2) as h FROM plr_sensor_data"
	    		+ " WHERE plr_sensor_id=1 AND date(plr_data_collect_time)=curdate()"
	    		+ " ORDER BY plr_sensor_id,plr_data,plr_data_collect_time DESC ) plr_sensor_data"
	    		+ " WHERE h='20' OR h='40';";
	    javax.persistence.Query query = em.createNativeQuery(sql,PlrSensorData.class); 
        List<PlrSensorData> userList = query.getResultList();
        PlrSensorData[] plrSensorDatas =  new PlrSensorData[userList.size()];   
        System.out.println(userList.size());
        for(int index=0;index<userList.size();index++){
        	plrSensorDatas[index] = userList.get(index);
        	plrSensorDatas[index].getPlrSensor().setBox(null);
        	plrSensorDatas[index].getPlrSensor().setPlrSensorDatas(null);
        }        
        emh.commit();
		em.close();
		return plrSensorDatas;
	}




	@Override
	public TemperatureSensorData[] queryTemperatureEveryTwentyMinutes()
			throws Exception {
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
	    String sql = "SELECT * FROM (SELECT *,DATE(temperature_data_collect_time) AS d,"
	    		+ "SUBSTRING(temperature_data_collect_time,15,2) as h FROM temperature_sensor_data"
	    		+ " WHERE temperature_sensor_id=1 AND date(temperature_data_collect_time)=curdate()"
	    		+ " ORDER BY temperature_sensor_id,temperature_data,temperature_data_collect_time DESC ) temperature_sensor_data"
	    		+ " WHERE h='20' OR h='40';";
	    javax.persistence.Query query = em.createNativeQuery(sql,TemperatureSensorData.class); 
        List<TemperatureSensorData> userList = query.getResultList();
        TemperatureSensorData[] temperatureSensorDatas =  new TemperatureSensorData[userList.size()];   
        System.out.println(userList.size());
        for(int index=0;index<userList.size();index++){
        	temperatureSensorDatas[index] = userList.get(index);
        	temperatureSensorDatas[index].getTemperatureSensor().setBox(null);
        	temperatureSensorDatas[index].getTemperatureSensor().setTemperatureSensorDatas(null);
        	temperatureSensorDatas[index].getTemperatureSensor().setHumidityDatas(null);
        }        
        emh.commit();
		em.close();
		return temperatureSensorDatas;
	}

	
	
	@Override
	public void saveFlameSensorDataInfo(FlameSensorData flameSensorData) throws Exception {
		if(flameSensorData.getFlameData()!=null&&flameSensorData.getFlameDataCollectTime()!=null){
			emh.beginTransaction();	
			FlameSensorDataDAO flameSensorDataDAO = new FlameSensorDataDAO();
			flameSensorDataDAO.save(flameSensorData);
			emh.commit();	
		}
		else{
			throw new Exception();		
		}		
	}
	
	@Override
	public void saveLightSensorDataInfo(LightSensorData lightSensorData) throws Exception {
		if(lightSensorData.getLightData()!=null&&lightSensorData.getLightDataCollectTime()!=null){
			emh.beginTransaction();	
			LightSensorDataDAO lightSensorDataDAO = new LightSensorDataDAO();
			lightSensorDataDAO.save(lightSensorData);
			emh.commit();	
		}
		else{
			throw new Exception();		
		}		
	}
	
	@Override
	public void saveGasSensorDataInfo(GasSensorData gasSensorData) throws Exception {
		if(gasSensorData.getGasData()!=null&&gasSensorData.getGasDataCollectTime()!=null){
			emh.beginTransaction();	
			GasSensorDataDAO gasSensorDataDAO = new GasSensorDataDAO();
			gasSensorDataDAO.save(gasSensorData);
			emh.commit();	
		}
		else{
			throw new Exception();		
		}		
	}
	
	@Override
	public void savePlrSensorDataInfo(PlrSensorData plrSensorData) throws Exception {
		if(plrSensorData.getPlrData()!=null&&plrSensorData.getPlrDataCollectTime()!=null){
			emh.beginTransaction();	
			PlrSensorDataDAO plrSensorDataDAO = new PlrSensorDataDAO();
			plrSensorDataDAO.save(plrSensorData);
			
			emh.commit();	
		}
		else{
			throw new Exception();		
		}		
	}

	@Override
	public void saveTemperatureSensorDataInfo(TemperatureSensorData temperatureSensorData) throws Exception {
		if(temperatureSensorData.getTemperatureData()!=null&&temperatureSensorData.getTemperatureDataCollectTime()!=null){
			emh.beginTransaction();	
			TemperatureSensorDataDAO temperatureSensorDataDAO = new TemperatureSensorDataDAO();
			temperatureSensorDataDAO.save(temperatureSensorData);
			emh.commit();	
		}
		else{
			throw new Exception();		
		}		
	}

	@Override
	public void saveHumidityDataInfo(HumidityData humidityData)
			throws Exception {
		if(humidityData.getHumidityData()!=null&&humidityData.getHumidityDataRecordTime()!=null){
			emh.beginTransaction();	
			HumidityDataDAO humidityDataDAO = new HumidityDataDAO();
			humidityDataDAO.save(humidityData);
			emh.commit();	
		}
		else{
			throw new Exception();
		}
		
	}




	@Override
	public LightSensorData[] queryLightSensorDataSet(
			Set<Integer> lightSensorIDSet) throws Exception {
		// TODO Auto-generated method stub
		LightSensorData[] lightSensorDataArray=new LightSensorData[lightSensorIDSet.size()];
		int index=0;
		for(Integer lightSensorID:lightSensorIDSet){
			lightSensorDataArray[index]=queryLightSensorData(lightSensorID);
			index++;
		}
		return lightSensorDataArray;
	}




	@Override
	public FlameSensorData[] queryFlameSensorDataSet(
			Set<Integer> flameSensorIDSet) throws Exception {
		// TODO Auto-generated method stub
		FlameSensorData[] flameSensorDataArray=new FlameSensorData[flameSensorIDSet.size()];
		int index=0;
		for(Integer flameSensorID:flameSensorIDSet){
			flameSensorDataArray[index]=queryFlameSensorData(flameSensorID);
			index++;
		}
		return flameSensorDataArray;
	}




	@Override
	public GasSensorData[] queryGasSensorDataSet(Set<Integer> gasSensorIDSet)
			throws Exception {
		// TODO Auto-generated method stub
		GasSensorData[] gasSensorDataArray=new GasSensorData[gasSensorIDSet.size()];
		int index=0;
		for(Integer gasSensorID:gasSensorIDSet){
			gasSensorDataArray[index]=queryGasSensorData(gasSensorID);
			index++;
		}
		return gasSensorDataArray;
	}




	@Override
	public PlrSensorData[] queryPlrSensorDataSet(Set<Integer> plrSensorIDSet)
			throws Exception {
		// TODO Auto-generated method stub
		PlrSensorData[] plrSensorDataArray=new PlrSensorData[plrSensorIDSet.size()];
		int index=0;
		for(Integer plrSensorID:plrSensorIDSet){
			plrSensorDataArray[index]=queryPlrSensorData(plrSensorID);
			index++;
		}
		return plrSensorDataArray;
	}




	@Override
	public TemperatureSensorData[] queryTemperatureSensorDataSet(
			Set<Integer> temperatureSensorIDSet) throws Exception {
		TemperatureSensorData[] temperatureSensorData=new TemperatureSensorData[temperatureSensorIDSet.size()];
		int index=0;
		for(Integer temperatureSensorID:temperatureSensorIDSet){
			temperatureSensorData[index]=queryTemperatureSensorData(temperatureSensorID);
			index++;
		}
		return temperatureSensorData;
	}



	@Override
	public HumidityData[] queryHumidityDataSet(
			Set<Integer> temperatureSensorIDSet) throws Exception {
		HumidityData[] humidityData = new HumidityData[temperatureSensorIDSet.size()];
		int index=0;
		for(Integer temperatureSensorID:temperatureSensorIDSet){
			humidityData[index] = queryHumidityData(temperatureSensorID);
			index++;
		}
		return humidityData;
	}













 
 
	
	
	
	
	
	
	
	
}

