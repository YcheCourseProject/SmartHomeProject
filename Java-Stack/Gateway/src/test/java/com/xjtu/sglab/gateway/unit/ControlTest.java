package com.xjtu.sglab.gateway.unit;

import org.junit.Ignore;
import org.junit.Test;

import com.xjtu.sglab.gateway.comm.ACctrl;
import com.xjtu.sglab.gateway.comm.deprecated.SWCtrl;
import com.xjtu.sglab.gateway.comm.deprecated.TempHumidAcq;

public class ControlTest {

	@Ignore @Test
	public void testSwtich() throws InterruptedException{
		SWCtrl.switchOn("10.0.0.255");
		Thread.sleep(2000);
		SWCtrl.switchOff("10.0.0.255");
	}
	
	@Ignore @Test
	public void testAirConditioner() throws InterruptedException{
		ACctrl.setACTemperatureMode(26, ACctrl.AC_MODE.COLD);
		Thread.sleep(20000);
		ACctrl.OffAC();
	}
	
	@Ignore @Test
	//测试目前编码有问题
	public void testSensor(){
			TempHumidAcq.disTempratureHummidity(TempHumidAcq.TMP_HUM_SERVER_IP);
		
	}
}
