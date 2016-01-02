package com.xjtu.sglab.exp;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.meter.EPM6000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.EPM7000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.IMeterDAO;
import com.xjtu.sglab.gateway.entity.ElectricityInfo;
import com.xjtu.sglab.gateway.withserver.MeterComm;

public class ExpMeterServer {

	public static void main(String[] args) {
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


	}

}
