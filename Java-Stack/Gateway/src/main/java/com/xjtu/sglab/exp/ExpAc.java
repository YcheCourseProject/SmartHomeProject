package com.xjtu.sglab.exp;

import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.xjtu.sglab.gateway.comm.ACctrl;
import com.xjtu.sglab.gateway.entity.AirConditionStatus;
import com.xjtu.sglab.gateway.util.GsonUtil;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;

public class ExpAc {

	public static void main(String[] args) {
		// Appliance AC
		Timer tiemr5 = new Timer();
		tiemr5.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					AirConditionStatus[] airConditionStatus = ApplianceComm
							.getInstance().queryAirConditionStatus(1);
					Gson gson=GsonUtil.create();
					System.out.println("ac"+gson.toJson(airConditionStatus));
					if (airConditionStatus.length == 1) {
						long recordTime = airConditionStatus[0]
								.getAirConditionStatusRecordTime().getTime();
	//								System.out.println(recordTime+"ac!!!!!!!!!!");
									if (Math.abs(recordTime - System.currentTimeMillis()) < 3000000
											&&airConditionStatus[0].getIsAlreadyControlled()==false) {
										ACctrl.AC_MODE acMode;
										if (airConditionStatus[0].getAirConditionMode() == Constants.AirConditioner.SNOW) {
											acMode = ACctrl.AC_MODE.COLD;
										} else if (airConditionStatus[0]
												.getAirConditionMode() == Constants.AirConditioner.WARM) {
											acMode = ACctrl.AC_MODE.WARM;
										} else if (airConditionStatus[0]
												.getAirConditionMode() == Constants.AirConditioner.HUMID) {
											acMode = ACctrl.AC_MODE.DEHYDRATION;
										} else {
											acMode = ACctrl.AC_MODE.VENTILATION;
										}
										ACctrl.setACTemperatureMode(
												(int) ((float) airConditionStatus[0]
														.getAirConditionTemperature()),
												acMode);
										ApplianceComm.getInstance().saveAirConditionInfo(airConditionStatus[0].getAirCondition().getAirConditionId(),
												airConditionStatus[0].getAirConditionMode(),airConditionStatus[0].getAirConditionTemperature());
									}
									}
									
							} catch (Exception e) {
								
							}
						}
					}, 100, 500);
	
		}
	
	}
