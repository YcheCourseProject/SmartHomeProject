package com.xjtu.sglab.exp;

import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.plug.AbstractPlugCtrl;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl1;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl2;
import com.xjtu.sglab.gateway.entity.SheSwitchStatus;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;

public class ExpSwitchServer {

	public static void main(String[] args) {
		Timer tiemr6 = new Timer();
		tiemr6.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					SheSwitchStatus[] sheSwitchStatus = ApplianceComm
							.getInstance().querySheSwitchStatus(1, 2);
					int num = 1;
					for (SheSwitchStatus sheSwitchStatusNow : sheSwitchStatus) {
						long recordTime = sheSwitchStatusNow
								.getSheSwitchStatusRecordTime().getTime();
						AbstractPlugCtrl abstractPlugCtrl;
						if (Math.abs(recordTime - System.currentTimeMillis()) < 1000000
								&&sheSwitchStatusNow.getIsAlreadyControlled()==false) {
							System.out.println((recordTime - System.currentTimeMillis())+"switch");
							if (sheSwitchStatusNow.getSheSwitch()
									.getSheSwitchId() == 1) {
								abstractPlugCtrl = new PlugCtrl1(
										"192.168.1.255");
								System.out.println("1");
							} else
								abstractPlugCtrl = new PlugCtrl2(
										"192.168.1.255");
								System.out.println("2");
							
							if (sheSwitchStatusNow.getSheSwitchStatus() == true) {
								abstractPlugCtrl.switchOn();
								System.out.println("ON");
							} else {
								abstractPlugCtrl.switchOff();
								System.out.println("OFF");
							}
							ApplianceComm.getInstance().saveSheSwitchInfo(sheSwitchStatusNow.getSheSwitch().getSheSwitchId()
									,sheSwitchStatusNow.getSheSwitchStatus());
						}
						
					}
				} catch (Exception e) {
					
				}
			}
		}, 100, 200);


	}

}
