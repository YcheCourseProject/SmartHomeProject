package com.xjtu.sglab.shems.dao.unit;



import java.util.List; 
  












import javax.annotation.Resource;
import javax.persistence.EntityManager; 
import javax.persistence.EntityManagerFactory; 
import javax.persistence.Persistence; 
import javax.persistence.Query; 
  












import org.junit.After; 
import org.junit.Before; 
import org.junit.Ignore;
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.LightSensorData;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")

  
  
/** 
 * 测试JPA原生SQL查询 
 * @author Luxh 
 */
public class TestNativeQuery { 
	EntityManagerFactory emf = null; 
    @Resource
    EntityManagerHelper emh;
    @Before
    public void before() { 
        //根据在persistence.xml中配置的persistence-unit name 创建EntityManagerFactory 
        emf = Persistence.createEntityManagerFactory("demoRestPersistence"); 
    } 
      
    @After
    public void after() { 
        //关闭EntityManagerFactory 
        if(null != emf) { 
            emf.close(); 
        } 
    } 

//    @Ignore
    @Test
    public void testNativeQuery2() {  
//      EntityManager em = emf.createEntityManager(); 
//      LightSensor lightSensor = new LightSensor();
//      
//
//      em.close(); 

//      EntityManager em = emf.createEntityManager(); 
//      em.createNativeQuery("INSERT INTO light_sensor (box_id) VALUES (3)",LightSensor.class);
//      em.createNativeQuery("INSERT INTO light_sensor (box_id) VALUES (?)").setParameter(0, 3);
//      Query query = em.createNativeQuery("INSERT INTO light_sensor (box_id) VALUES (box_id=:box_id)");
//      query.setParameter("box_id", 3);
//      em.flush();
//      em.close(); 
//      emh.commit();
    	
        EntityManager em = emf.createEntityManager(); 
        //定义SQL 
        String sql = "SELECT * from lamp ORDER BY lamp_id desc limit 1";       
        //创建原生SQL查询QUERY实例,指定了返回的实体类型 
        Query query =  em.createNativeQuery(sql,Lamp.class); 
      //执行查询，返回的是实体列表, 
        List<Lamp> userList = query.getResultList(); 
        
        Lamp lamp = new Lamp();
        lamp = userList.get(userList.size()-1);
        System.out.println(lamp.getLampId());
        System.out.println(lamp.getLampRatedPower());   
        em.close(); 
    	
//      EntityManager em = emf.createEntityManager(); 
//      //定义SQL 
//      String sql1 = "SELECT * FROM lamp"; 
//      String sql2 = "INSERT INTO lamp VALUES (2,2,3,4.3,5)";
//      
//      //创建原生SQL查询QUERY实例,指定了返回的实体类型 
//      Query query =  em.createNativeQuery(sql2,Lamp.class); 
//    //执行查询，返回的是实体列表, 
//      List<Lamp> userList = query.getResultList(); 
//      
//      Lamp lamp = new Lamp();
//      lamp = userList.get(userList.size()-1);
//      System.out.println(lamp.getLampId());
//      System.out.println(lamp.getLampRatedPower());   
//      em.close(); 
    	
    } 
      
    @Ignore  
    @Test
    public void testLightSensorQuery() { 
        EntityManager em = emf.createEntityManager(); 
        //定义SQL 
        String sql = "SELECT * from light_sensor_data ORDER BY light_data_id desc limit 1";       
        //创建原生SQL查询QUERY实例,指定了返回的实体类型 
        Query query =  em.createNativeQuery(sql,LightSensorData.class); 
      //执行查询，返回的是实体列表, 
        List<LightSensorData> userList = query.getResultList(); 
        
        LightSensorData lightSensorData = new LightSensorData();
        lightSensorData = userList.get(userList.size()-1);
        
        System.out.println(lightSensorData.getLightData());
        em.close(); }  
}