package com.xjtu.sglab.exp;

import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.meter.EPM6000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.EPM7000GEDAOFactory;
import com.xjtu.sglab.gateway.comm.meter.IMeterDAO;

public class ExpMeter {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				IMeterDAO meterDAO = EPM7000GEDAOFactory.getInstance().getGEDAO(
						"192.168.1.104");
				System.out.println("104:\t"+meterDAO.getActivePower());
				System.out.println("104:\t"+meterDAO.getReactivePower());
				System.out.println("104:\t"+meterDAO.getEnergy());
				System.out.println();
			}
		}, 1000, 2000);
		
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				IMeterDAO meterDAO = EPM6000GEDAOFactory.getInstance().getGEDAO(
						"192.168.1.103");
				System.out.println("103:\t"+meterDAO.getActivePower());
				System.out.println("103:\t"+meterDAO.getReactivePower());
				System.out.println("103:\t"+meterDAO.getEnergy());
				System.out.println();
			}
		}, 1000, 2000);
		
		Timer timer3 = new Timer();
		timer3.schedule(new TimerTask() {
			@Override
			public void run() {
				IMeterDAO meterDAO = EPM6000GEDAOFactory.getInstance().getGEDAO(
						"192.168.1.102");
				System.out.println("102:\t"+meterDAO.getActivePower());
				System.out.println("102:\t"+meterDAO.getReactivePower());
				System.out.println("102:\t"+meterDAO.getEnergy());
				System.out.println();
			}
		}, 1000, 2000);
	}

}
