package com.xjtu.sglab.gateway.comm.deprecated;

import java.net.InetAddress;

import com.xjtu.sglab.gateway.util.ArraysOperation;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.facade.ModbusTCPMaster;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.util.ModbusUtil;

public class MeterAcq {

	public static void main(String[] args) throws Exception {
		int port = Modbus.DEFAULT_PORT;
		int unitId = 1;
		InetAddress addy = InetAddress.getByName("192.168.1.104");
		TCPMasterConnection connection = new TCPMasterConnection(addy);
		connection.setTimeout(3000);
		connection.setPort(port);
		connection.connect();
		ModbusTCPTransaction transaction = new ModbusTCPTransaction(connection);
		
		ModbusRequest request = new ReadMultipleRegistersRequest(30005, 1);
		request.setUnitID(unitId);
		transaction.setRequest(request);
		transaction.execute();

		ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) transaction
				.getResponse();
		Register register = response.getRegister(0);
		byte[] bytes = register.toBytes();
		System.out.println(bytes[0]);
		System.out.println(bytes[1] >> 4);
		System.out.println((bytes[1] & 0x0f));
		double degeree=Math.pow(10, bytes[1] >> 4)*Math.pow(10, -1*(bytes[1] & 0x0f));
		System.out.println("degree"+degeree);
		
		request = new ReadMultipleRegistersRequest(1017, 4);
		request.setUnitID(unitId);
		transaction.setRequest(request);
		transaction.execute();
		response = (ReadMultipleRegistersResponse) transaction.getResponse();
		byte[] bytes1 = response.getRegister(0).toBytes();
		byte[] bytes2 = response.getRegister(1).toBytes();
		byte[] bytes3= response.getRegister(2).toBytes();
		byte[] bytes4=response.getRegister(3).toBytes();
		System.out.println(ModbusUtil.registersToFloat(ArraysOperation.mergeBytes(bytes1,bytes2)));
		System.out.println(ModbusUtil.registersToFloat(ArraysOperation.mergeBytes(bytes3,bytes4)));
		
		
		request = new ReadMultipleRegistersRequest(1500, 2);
		request.setUnitID(unitId);
		transaction.setRequest(request);
		transaction.execute();
		response = (ReadMultipleRegistersResponse) transaction.getResponse();
		bytes1 = response.getRegister(0).toBytes();
		bytes2 = response.getRegister(1).toBytes();
		
		System.out.println(ModbusUtil.registersToInt(ArraysOperation.mergeBytes(bytes1,bytes2))*degeree);
		 
	}


}
