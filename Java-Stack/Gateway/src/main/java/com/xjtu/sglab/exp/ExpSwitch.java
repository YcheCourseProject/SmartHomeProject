package com.xjtu.sglab.exp;

import com.xjtu.sglab.gateway.comm.deprecated.SWCtrl;
import com.xjtu.sglab.gateway.comm.plug.AbstractPlugCtrl;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl1;
import com.xjtu.sglab.gateway.comm.plug.PlugCtrl2;

public class ExpSwitch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AbstractPlugCtrl plugCtrl=new PlugCtrl1("192.168.1.255");
		AbstractPlugCtrl plugCtrl2=new PlugCtrl2("192.168.1.255");
		plugCtrl.switchOn();
		Thread.sleep(2000);
		plugCtrl2.switchOn();
		Thread.sleep(2000);
		plugCtrl.switchOff();
		Thread.sleep(2000);
		plugCtrl2.switchOff();
	}

}
