package com.xjtu.sglab.gateway.unit.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JunitUI {
	
	
	@Test
	public void testSpringConfiguration(){
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = null;  
		String[] fileUrl = new String[]{"classpath*:*Context*.xml"};  
		applicationContext = new ClassPathXmlApplicationContext(fileUrl);  
		ISample s = (ISample)SpringBeanUtil.getBean("sampleService");
		s.test();
	}

}
