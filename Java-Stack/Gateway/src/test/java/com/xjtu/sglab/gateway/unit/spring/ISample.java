package com.xjtu.sglab.gateway.unit.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public interface ISample {
	public void test();
	 public HibernateTemplate getH();
	 public void setH(@Qualifier(value="hibernateTemplate")HibernateTemplate h);
}
