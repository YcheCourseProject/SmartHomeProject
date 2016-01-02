package com.xjtu.sglab.exp;

import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.CurtainCtrl;

public class ExpCurtain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CurtainCtrl.downCurtain();
			}
		}, 5000);

		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CurtainCtrl.stopCurtain();
			}
		}, 10000);

		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CurtainCtrl.upCurtain();
			}
		}, 15000);
	}
}
