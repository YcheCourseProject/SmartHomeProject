package com.xjtu.sglab.gateway.unit.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName: SampleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2012-12-12 上午10:10:38 
 *  
 */
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
@Service("sampleService")
@Scope("prototype")
public class SampleService implements ISample{
	
	private HibernateTemplate h;
	
	public void test() {
		List list = this.h.getSessionFactory().getCurrentSession().createSQLQuery("select * from cs_user").list();
		System.out.println("----------------------------------------");
		System.out.println(list.size());
	}

	public HibernateTemplate getH() {
		return h;
	}

	@Autowired(required=false)
	public void setH(@Qualifier(value="hibernateTemplate")HibernateTemplate h) {
		this.h = h;
	}
	
	
}
