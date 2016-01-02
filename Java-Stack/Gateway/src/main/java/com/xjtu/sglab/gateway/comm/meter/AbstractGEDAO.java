package com.xjtu.sglab.gateway.comm.meter;

import java.net.InetAddress;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;

public abstract class AbstractGEDAO implements IMeterDAO {
	public static final int SCALE_REF = 30005;
	public static final int UNIT_ID = 1;
	private TCPMasterConnection connection = null;
	private ModbusTCPTransaction transaction = null;
	protected String ip = null;

	public AbstractGEDAO(String ip) {
		super();
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 功能：每次读取数据之前都要获取Scale 每次获取都是一个新的Transaction
	 * 
	 * @return
	 */
	public Float getEnergyScale() {
		try {
			ModbusRequest request = new ReadMultipleRegistersRequest(SCALE_REF,
					1);
			request.setUnitID(UNIT_ID);
			transaction = new ModbusTCPTransaction(getModbusConnection());
			transaction.setRequest(request);
			transaction.execute();
			ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) transaction
					.getResponse();
			Register register = response.getRegister(0);
			byte[] bytes = register.toBytes();
			float degeree = (float) (Math.pow(10, bytes[1] >> 4) * Math.pow(10,
					-1 * (bytes[1] & 0x0f)));
			return degeree;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public TCPMasterConnection getModbusConnection() throws Exception {
		// TODO Auto-generated method stub
		if (ip == null)
			return null;
		if (connection == null) {
			InetAddress slaveAddr = InetAddress.getByName(ip);
			connection = new TCPMasterConnection(slaveAddr);
		}
		if (connection.isConnected() == false) {
			connection.close();
			
			connection.setTimeout(10000);
			connection.setPort(Modbus.DEFAULT_PORT);
			
			connection.connect();
		}
		return connection;
	}

	@Override
	public ModbusTCPTransaction getTransaction() throws Exception {
		// TODO Auto-generated method stub
		if (transaction == null) {
			transaction = new ModbusTCPTransaction(getModbusConnection());
		}
		return transaction;
	}

}
