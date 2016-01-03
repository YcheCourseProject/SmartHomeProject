package com.xjtu.sglab.shems.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.DailyElectricityPrice;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.service.IOperatePriceService;

@Service
public class OperatePriceService implements IOperatePriceService {
	@Resource
	EntityManagerHelper emh;
	@Resource 
	IBoxDAO BoxDAO;
	@Resource
	EntityManagerFactory emf ;
	
	@Override
	public DailyElectricityPrice queryPrice(int month) throws Exception {
		EntityManager em = emf.createEntityManager();
		month = 8;
		String sql ="SELECT * FROM daily_electricity_price LIMIT 1";
		javax.persistence.Query query = em.createNativeQuery(sql,DailyElectricityPrice.class);
					
		List<DailyElectricityPrice> userList = query.getResultList();
		DailyElectricityPrice dailyElectricityPrice = new DailyElectricityPrice();
		dailyElectricityPrice = userList.get(userList.size()-1);
		
		em.close();
		return dailyElectricityPrice;
	}

}
