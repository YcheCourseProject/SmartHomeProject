package com.xjtu.sglab.shems.dao.unit.che;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.impl.BoxDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.Room;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class JPAOne2ManyInsert {
	@Resource
	EntityManagerHelper emh;
	@Resource
	IRoomDAO roomDAO;
	@Resource
	IBoxDAO boxDAO;
	
	@Ignore
	@Test
	public void testInsertOne2Many(){
		emh.beginTransaction();
		Room room=new Room();
		Set<Box> boxSet=new HashSet<Box>();
		Box box=new Box();
		boxSet.add(box);
		Box box2=new Box();
		boxSet.add(box2);
		room.setBoxes(boxSet);
		box.setControlModelIp("1234567");
		box.setDevelopmentBoardIp("4324");
		box2.setControlModelIp("2345678");
		box2.setDevelopmentBoardIp("4323214");
		roomDAO.save(room);
		room.setBoxes(null);
		box.setRoom(room);
		box2.setRoom(room);
		boxDAO.update(box);
		boxDAO.update(box2);
	 
		emh.commit();
//		emh.getEntityManager().clear();
//		Room room=emh.getEntityManager().find(Room.class, arg1)
		
	}

	@Ignore
	@Test
	public void testMany2One(){
		emh.beginTransaction();
		Box box=new Box();
		box.setControlModelIp("shi45354t");
		box.setDevelopmentBoardIp("4324");
		Room room=new Room();
		room.setRoomId(1);
		box.setRoom(room);
		boxDAO.save(box);
		emh.commit();
	}
	
	@Test
	public void testOne2ManyEager(){
		emh.beginTransaction();
		Room room=new Room();
		
		Set<Box> boxSet=new HashSet<Box>();
		Box box=new Box();
		boxSet.add(box);
		Box box2=new Box();
		boxSet.add(box2);
		room.setBoxes(boxSet);
		box.setControlModelIp("shit");
		box.setDevelopmentBoardIp("4324");
		box2.setControlModelIp("shit321");
		box2.setDevelopmentBoardIp("4323214");
		roomDAO.save(room);
		emh.commit();
	}
}
