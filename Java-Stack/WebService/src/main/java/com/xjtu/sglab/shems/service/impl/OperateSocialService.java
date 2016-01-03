package com.xjtu.sglab.shems.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;











import com.xjtu.sglab.shems.dao.IActivityTypeDAO;
import com.xjtu.sglab.shems.dao.IGpsInfoDAO;
import com.xjtu.sglab.shems.dao.ILocationTypeDAO;
import com.xjtu.sglab.shems.dao.ISocialInfoDAO;
import com.xjtu.sglab.shems.dao.ISocialSourceDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.ActivityType;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.service.IOperateGpsService;
import com.xjtu.sglab.shems.service.IOperateSocialService;

@Service
public class OperateSocialService implements IOperateSocialService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	EntityManagerFactory emf ;
	//social media
	@Resource
	ISocialSourceDAO socialSourceDAO;
	@Resource
	ISocialInfoDAO socialInfoDAO;
	@Resource
	IActivityTypeDAO activityTypeDAO;
	
	@Override
	public void saveSocialInfo(SocialInfo socialInfo) throws Exception{
		if(socialInfo.getActivityType()!=null
				&&socialInfo.getActivitySentTime()!=null&&socialInfo.getSocialSource()!=null){
			emh.beginTransaction();
			socialInfo.setSocialSource(socialSourceDAO.findById(socialInfo.getSocialSource().getSourceTypeId()));			
			ActivityType activityType = socialInfo.getActivityType();
			activityType = activityTypeDAO.findById(activityType.getActivityTypeId());
			socialInfo.setActivityType(activityType);
			socialInfo.setActivityRecordTime(new Timestamp(System.currentTimeMillis()));
			socialInfoDAO.save(socialInfo);
			emh.commit();
		}
		else{
			throw new Exception();
		}
	}

	@Override
	public void saveSocialInfo(List<SocialInfo> socialInfoList) throws Exception {
		for(SocialInfo socialInfo: socialInfoList){
			saveSocialInfo(socialInfo);
		}
	}

	@Override
	public SocialInfo querySocialInfo() throws Exception {	
	    EntityManager em = emh.getEntityManager();
        String sql = "SELECT * from social_info ORDER BY social_info_id desc limit 1";      
        javax.persistence.Query query = em.createNativeQuery(sql,SocialInfo.class); 
        List<SocialInfo> userList = query.getResultList();        
        SocialInfo socialInfo = new SocialInfo();
        socialInfo = userList.get(userList.size()-1);	        
      
        em.close(); 
        return socialInfo;	
	}
 
	
	
 
	 
}
