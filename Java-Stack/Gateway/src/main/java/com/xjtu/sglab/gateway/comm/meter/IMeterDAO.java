package com.xjtu.sglab.gateway.comm.meter;

import java.net.UnknownHostException;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.net.TCPMasterConnection;

public interface IMeterDAO {
	public Float getActivePower();
	public Float getReactivePower();
	public Float getEnergy();
	public TCPMasterConnection getModbusConnection() throws Exception;
	public ModbusTCPTransaction getTransaction() throws Exception;
}
