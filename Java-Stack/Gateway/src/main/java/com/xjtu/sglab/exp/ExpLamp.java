package com.xjtu.sglab.exp;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.LightCtrl;
import com.xjtu.sglab.gateway.entity.LampStatus;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;

public class ExpLamp {

	public static void main(String[] args) {
		// Appliance Lamp
		Timer tiemr7 = new Timer();
		tiemr7.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					LampStatus[] lampStatus = ApplianceComm.getInstance()
							.queryLampStatusArray(1, 2, 3);					
					for (LampStatus lampStatusNow : lampStatus) {
						
						long recordTime = lampStatusNow
								.getLampStatusRecordTime().getTime();
						if (Math.abs(recordTime - System.currentTimeMillis()) < 1000000
								&&lampStatusNow.getIsAlreadyControlled()==false) {
							InetAddress inetAddress = LightCtrl
									.sniffModules("192.168.1.255");
							LightCtrl.confirm(inetAddress);
							System.out.println(lampStatusNow.getLamp()
									.getLampId()+"lamp+++++++");
							if (lampStatusNow.getLampStatus() == 0) {
								for (int j = 0; j < 4; j++){
									LightCtrl.control(lampStatusNow.getLamp()
											.getLampId(), false, inetAddress);}							
							} else
								for (int j = 0; j < 4; j++){
									LightCtrl.control(lampStatusNow.getLamp()
											.getLampId(), true, inetAddress);}
							ApplianceComm.getInstance().saveLampInfo(lampStatusNow.getLamp().getLampId(),lampStatusNow.getLampStatus());
						}
						
					}
				} catch (Exception e) {
					
				}
			}
		}, 100, 200);

	}

}
