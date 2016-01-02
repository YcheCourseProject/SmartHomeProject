package com.xjtu.sglab.exp;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.xjtu.sglab.gateway.comm.ACctrl;
import com.xjtu.sglab.gateway.comm.ArduinoSensorDAO;
import com.xjtu.sglab.gateway.comm.CurtainCtrl;
import com.xjtu.sglab.gateway.comm.LightCtrl;
import com.xjtu.sglab.gateway.comm.meter.EPM6000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.EPM7000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.IMeterDAO;
import com.xjtu.sglab.gateway.comm.plug.AbstractPlugCtrl;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl1;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl2;
import com.xjtu.sglab.gateway.entity.AirConditionStatus;
import com.xjtu.sglab.gateway.entity.CurtainStatus;
import com.xjtu.sglab.gateway.entity.ElectricityInfo;
import com.xjtu.sglab.gateway.entity.LampStatus;
import com.xjtu.sglab.gateway.entity.SheSwitchStatus;
import com.xjtu.sglab.gateway.util.GsonUtil;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;
import com.xjtu.sglab.gateway.withserver.MeterComm;
import com.xjtu.sglab.gateway.withserver.SensorComm;

public class ExpRun1 {

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
				
				
				// Meter
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						IMeterDAO meterDAO = EPM7000GEDAOFactory.getInstance()
								.getGEDAO(Constants.Meter.METER_IP_104);
						ElectricityInfo electricityInfo = new ElectricityInfo();
						electricityInfo.setActivePower(meterDAO.getActivePower());
						electricityInfo.setReactivePower(meterDAO.getReactivePower());
						electricityInfo.setTotalConsumeEnergy(meterDAO.getEnergy());
						electricityInfo.setElectricityInfoCollectTime(new Timestamp(
								System.currentTimeMillis()));
						if (electricityInfo.getActivePower() != null
								&& electricityInfo.getReactivePower() != null
								&& electricityInfo.getTotalConsumeEnergy() != null) {
							MeterComm.getInstance().saveMeterInfo(electricityInfo,
									Constants.Meter.METER_ID_104);
						}
					}
				}, 1000, 4000);

				Timer timer2 = new Timer();
				timer2.schedule(new TimerTask() {
					@Override
					public void run() {
						IMeterDAO meterDAO = EPM6000GEDAOFactory.getInstance()
								.getGEDAO(Constants.Meter.METER_IP_103);
						ElectricityInfo electricityInfo = new ElectricityInfo();
						electricityInfo.setActivePower(meterDAO.getActivePower());
						electricityInfo.setReactivePower(meterDAO.getReactivePower());
						electricityInfo.setTotalConsumeEnergy(meterDAO.getEnergy());
						electricityInfo.setElectricityInfoCollectTime(new Timestamp(
								System.currentTimeMillis()));
						if (electricityInfo.getActivePower() != null
								&& electricityInfo.getReactivePower() != null
								&& electricityInfo.getTotalConsumeEnergy() != null) {
							MeterComm.getInstance().saveMeterInfo(electricityInfo,
									Constants.Meter.METER_ID_103);
						}
					}
				}, 1000, 4000);

				Timer timer3 = new Timer();
				timer3.schedule(new TimerTask() {
					@Override
					public void run() {
						IMeterDAO meterDAO = EPM6000GEDAOFactory.getInstance()
								.getGEDAO(Constants.Meter.METER_IP_102);
						ElectricityInfo electricityInfo = new ElectricityInfo();
						electricityInfo.setActivePower(meterDAO.getActivePower());
						electricityInfo.setReactivePower(meterDAO.getReactivePower());
						electricityInfo.setTotalConsumeEnergy(meterDAO.getEnergy());
						electricityInfo.setElectricityInfoCollectTime(new Timestamp(
								System.currentTimeMillis()));
						if (electricityInfo.getActivePower() != null
								&& electricityInfo.getReactivePower() != null
								&& electricityInfo.getTotalConsumeEnergy() != null) {
							MeterComm.getInstance().saveMeterInfo(electricityInfo,
									Constants.Meter.METER_ID_102);
						}
					}
				}, 1000, 4000);
				
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
				
				
				
				
				// Sensor
				Timer tiemr4 = new Timer();
				tiemr4.schedule(new TimerTask() {
					String ip=Constants.Sensor.IP;
					
					public String getIp() {
						return ip;
					}

					public void setIp(String ip) {
						this.ip = ip;
					}

					@Override
					public void run() {
						try {
							
							String result = ArduinoSensorDAO.gather(InetAddress
									.getByName(getIp()));
							System.out.println(result);
							String s[] = result.split(",");
							String temperature = s[0].split(":")[1];
							System.out.println(temperature);
							String humidity = s[1].split(":")[1];
							String light = s[2].split(":")[1];
							String gas = s[3].split(":")[1];
							String fire = s[4].split(":")[1];
							String human = s[5].split(":")[1];
							SensorComm.getInstance().saveFlameSensorInfo(
									Float.parseFloat(fire), Constants.Sensor.ID);
							SensorComm.getInstance().saveGasSensorInfo(
									Float.parseFloat(gas), Constants.Sensor.ID);
							SensorComm.getInstance().saveLightSensorInfo(
									Float.parseFloat(light), Constants.Sensor.ID);
							SensorComm.getInstance().savePlrSensorInfo(
									Float.parseFloat(human), Constants.Sensor.ID);
							SensorComm.getInstance().saveTemperatureSensorInfo(
									Float.parseFloat(temperature), Constants.Sensor.ID);
							SensorComm.getInstance().saveHumidtySensorInfo(
									Float.parseFloat(humidity), Constants.Sensor.ID);
						} catch (Exception e) {
							setIp(ArduinoSensorDAO.sniffModules("192.168.1.255").get(0).getHostAddress());
						}
					}
				}, 1000, 5000);

				
				
				
			}
	
	
	

	}

