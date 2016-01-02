package com.xjtu.sglab.gateway.unit.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


/** 
* @ClassName: SpringBeanUtil 
* @Description: TODO(spring功能类，用于获取bean) 
* @author zhoushun
* @date 2012-11-27 下午04:22:36 
*  
*/ 


@Component("springBeanUtil")//使用注解
public class SpringBeanUtil implements ApplicationContextAware {
	protected final static Log logger = LogFactory.getLog(SpringBeanUtil.class);
	
	private static ApplicationContext ctx = null;
	
	private static Map<String, Properties> propMap = new HashMap<String, Properties>(0);
	
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		SpringBeanUtil.ctx = ctx;
	}
	
	public static Object getBean(String prop) {
		Object obj = ctx.getBean(prop);
		if (logger.isDebugEnabled()) {
			logger.debug("property=[" + prop + "],object=[" + obj + "]");
		}
		return obj;
	}

	public static Properties getProperties(String filepath) {
		if (propMap.containsKey(filepath)) return propMap.get(filepath);
		
		Resource resource = ctx.getResource(filepath);
		Properties prop = new Properties();
		try {
			prop.load(resource.getInputStream());
			propMap.put(filepath, prop);
			return prop;
		} catch (IOException e) {
			logger.error("can not find the resource file:[" + filepath + "]", e);
			return null;
		}
	}

	public static DataSource getDataSource(String source) {
		return (DataSource) getBean(source);
	}
	
	public static SessionFactory getSessionFactory() {
		return (SessionFactory) getBean("sessionFactory");
	}
}

