package com.xjtu.sglab.exp;

import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.CurtainCtrl;
import com.xjtu.sglab.gateway.entity.CurtainStatus;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;

public class ExpCurtainServer {

	public static void main(String[] args) {
		// Appliance Curtian
		Timer tiemr8 = new Timer();
		tiemr8.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					CurtainStatus[] curtainStatus = ApplianceComm.getInstance()
							.queryCurtainStatus(1);
					 if(curtainStatus.length==1){
						 long recordTime = curtainStatus[0]
									.getCurtainStatusRecordTime().getTime();
							if (Math.abs(recordTime - System.currentTimeMillis()) < 1000000&&curtainStatus[0].getIsAlreadyControlled()==false) {
								if(curtainStatus[0].getCurtainStatus()==1){
									CurtainCtrl.upCurtain();
								}
								else if(curtainStatus[0].getCurtainStatus()==2){
									CurtainCtrl.stopCurtain();
								}
								else if(curtainStatus[0].getCurtainStatus()==3){
									CurtainCtrl.downCurtain();
								}
								ApplianceComm.getInstance().saveCurtainInfo(curtainStatus[0].getCurtain().getCurtainId(),curtainStatus[0].getCurtainStatus());
							}
					 }
				} catch (Exception e) {
					
				}
			}
		}, 100, 200);
	}
	

}
