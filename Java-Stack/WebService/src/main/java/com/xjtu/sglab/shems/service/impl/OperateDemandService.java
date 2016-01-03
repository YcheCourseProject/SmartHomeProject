package com.xjtu.sglab.shems.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.codehaus.jackson.format.InputAccessor.Std;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjtu.sglab.shems.dao.IRealTimeDecisionDAO;
import com.xjtu.sglab.shems.dao.impl.AirConditionDemandDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.dao.impl.RealTimeDecisionDAO;
import com.xjtu.sglab.shems.dao.impl.RealTimeDemandDAO;
import com.xjtu.sglab.shems.dao.impl.SocialActivityToDemandDAO;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionDemand;
import com.xjtu.sglab.shems.entity.AirConditionRealTimeDecision;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.RealTimeDecision;
import com.xjtu.sglab.shems.entity.RealTimeDemand;
import com.xjtu.sglab.shems.entity.SocialActivityToDemand;
import com.xjtu.sglab.shems.entity.WaterHeaterDemand;
import com.xjtu.sglab.shems.service.IOperateDemandService;

@Service
public class OperateDemandService implements IOperateDemandService{

	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf ;

	@Resource
	com.xjtu.sglab.shems.dao.impl.ActivityTypeDAO activityTypeDAO;
	
	
	@Override
	
	public RealTimeDemand createRealTimeDemand(
			SocialActivityToDemand socialActivityToDemand) throws Exception {
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
		
		String activityName = socialActivityToDemand.getActivityType().getActivityType();
		String stdTime = socialActivityToDemand.getSocialActivityBeginTime();
		
		String sql1 = "SELECT * FROM social_activity_to_demand WHERE social_activity_begin_time"
				+ " = ? AND activity_type_id = (SELECT activity_type_id FROM activity_type WHERE activity_type = ?)";
		Query query = em.createNativeQuery(sql1, SocialActivityToDemand.class).setParameter(1, stdTime).setParameter(2, activityName);
		List<SocialActivityToDemand> satdList = query.getResultList();
		SocialActivityToDemand satdTmp = satdList.get(satdList.size()-1);
		
		
		SocialActivityToDemandDAO socialActivityToDemandDAO = new SocialActivityToDemandDAO();

//		Set<AirConditionDemand> airConditionDemands = socialActivityToDemandDAO.findById(3).getAirConditionDemands();
		Set<AirConditionDemand> airConditionDemands = satdTmp.getAirConditionDemands();
		emh.getEntityManager().clear();
		
		RealTimeDemand realTimeDemand = new RealTimeDemand();	
		RealTimeDemandDAO realTimeDemandDAO = new RealTimeDemandDAO();
		realTimeDemand.setAirConditionDemands(airConditionDemands);
		realTimeDemandDAO.save(realTimeDemand);
		
		String sql2 = "SELECT * from real_time_demand ORDER BY real_time_demand_id desc limit 1";
		query = em.createNativeQuery(sql2, RealTimeDemand.class);
		List<RealTimeDemand> rtdList = query.getResultList();
		RealTimeDemand rtdTmp = rtdList.get(rtdList.size()-1);
		
		
		emh.commit();
		em.close();
		return rtdTmp;
			
	}
	
	public RealTimeDecision createRealTimeDecision(RealTimeDecision realTimeDecision) throws Exception{
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
		RealTimeDecision rtd = realTimeDecision;
		RealTimeDecisionDAO realTimeDecisionDAO = new RealTimeDecisionDAO();
		
		
		
		realTimeDecisionDAO.save(rtd);
		
		String sql = "SELECT * from real_time_decision ORDER BY real_time_decision_id desc limit 1";
		Query query = em.createNativeQuery(sql, RealTimeDecision.class);
		List<RealTimeDecision> decisionList = query.getResultList();
		rtd = decisionList.get(decisionList.size()-1);
		
		emh.commit();
		em.close();
		return rtd;
	}
	
	public void updateRealTimeDecision(RealTimeDecision realTimeDecision) throws Exception{
		emh.beginTransaction();	
		EntityManager em = emh.getEntityManager();
		RealTimeDecision rtd = realTimeDecision;
		realTimeDecision.getAirConditionRealTimeDecisions();
		realTimeDecision.getRealTimeDecisionId();
		
		RealTimeDecisionDAO realTimeDecisionDAO = new RealTimeDecisionDAO();
//		realTimeDecisionDAO.update(realTimeDecision);
			
		emh.commit();
		em.close();
	
	}
	
	
	
	
	public DailyElectricityPrice queryPrice () throws Exception{
		emh.beginTransaction();
		EntityManager em = emh.getEntityManager();
		String sql = "SELECT * FROM daily_electricity_price ";
		Query query = em.createNativeQuery(sql,DailyElectricityPrice.class);
		List<DailyElectricityPrice> priceList = query.getResultList();
		DailyElectricityPrice price = priceList.get(priceList.size()-1);
		emh.commit();
		em.close();
		return price;
		
	}

	@Override
	public AirConditionRealTimeDecision[] queryModelDecision() throws Exception {
		emh.beginTransaction();
		EntityManager em = emh.getEntityManager();
//		String sql = "SELECT * FROM air_condition_real_time_decision WHERE real_time_decision_id =  "
//				+ "(SELECT real_time_decision_id FROM real_time_decision ORDER BY real_time_decision_id DESC LIMIT 1) ";
//		String sql = "SELECT * FROM real_time_decision ORDER BY real_time_decision_id DESC LIMIT 1";
		String sql = "select * FROM air_condition_real_time_decision "
				+ "WHERE TIMESTAMPDIFF(MINUTE,air_condition_decision_record_time,CURRENT_TIMESTAMP()) <=5";
		javax.persistence.Query query = em.createNativeQuery(sql,AirConditionRealTimeDecision.class); 
        List<AirConditionRealTimeDecision> userList = query.getResultList();
        AirConditionRealTimeDecision[] airConditionRealTimeDecisions =  new AirConditionRealTimeDecision[userList.size()];   
        System.out.println(userList.size());
        for(int index=0;index<userList.size();index++){
        	airConditionRealTimeDecisions[index] = userList.get(index);
        	airConditionRealTimeDecisions[index].getAirCondition().setAirConditionControlDetails(null);
        	airConditionRealTimeDecisions[index].getAirCondition().setAirConditionRealTimeDecisions(null);
        	airConditionRealTimeDecisions[index].getAirCondition().setBox(null);
        	airConditionRealTimeDecisions[index].getAirCondition().setAirConditionStatuses(null);
        	airConditionRealTimeDecisions[index].setAirConditionControlDetails(null);        	
        	airConditionRealTimeDecisions[index].getAirConditionControlDetail().setAirCondition(null);
        	airConditionRealTimeDecisions[index].getAirConditionControlDetail().setAirConditionRealTimeDecision(null);
        	airConditionRealTimeDecisions[index].getAirConditionControlDetail().setAirConditionRealTimeDecisions(null);
//        	airConditionRealTimeDecisions[index].getRealTimeDecision().setAirConditionRealTimeDecisions(null);
//        	airConditionRealTimeDecisions[index].getRealTimeDecision().setRealTimeDemand(null);
//        	airConditionRealTimeDecisions[index].getRealTimeDecision().setWaterHeaterRealTimeDecisions(null); 	
        }        
        emh.commit();
		em.close();
		return airConditionRealTimeDecisions;
	}
	
	
	
	
	
	
	
	
	
}
