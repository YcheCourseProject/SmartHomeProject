package com.xjtu.sglab.gateway.comm.meter;

import java.util.HashSet;
import java.util.Set;

import com.xjtu.sglab.gateway.util.ArraysOperation;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.ModbusUtil;

public class EPM7000GEDAO extends AbstractGEDAO {
	public static final int ACTIVE_POWER_REF = 1017;
	public static final int REACTIVE_POWER_REF = 1019;
	public static final int ENERGY_REF = 1499;
	
	protected EPM7000GEDAO(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Float getActivePower() {
		// TODO Auto-generated method stub
		try {
			ModbusRequest request = new ReadMultipleRegistersRequest(
					ACTIVE_POWER_REF, 2);
			request.setUnitID(UNIT_ID);
			ModbusTCPTransaction transaction = this.getTransaction();
			transaction.setRequest(request);
			transaction.execute();
			ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) transaction
					.getResponse();
			byte[] bytes1 = response.getRegister(0).toBytes();
			byte[] bytes2 = response.getRegister(1).toBytes();
			return ModbusUtil.registersToFloat(ArraysOperation.mergeBytes(
					bytes1, bytes2));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Float getReactivePower() {
		// TODO Auto-generated method stub
		try {
			ModbusRequest request = new ReadMultipleRegistersRequest(
					REACTIVE_POWER_REF, 2);
			request.setUnitID(UNIT_ID);
			ModbusTCPTransaction transaction = this.getTransaction();
			transaction.setRequest(request);
			transaction.execute();
			ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) transaction
					.getResponse();
			byte[] bytes1 = response.getRegister(0).toBytes();
			byte[] bytes2 = response.getRegister(1).toBytes();
			return ModbusUtil.registersToFloat(ArraysOperation.mergeBytes(
					bytes1, bytes2));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Float getEnergy() {
		// TODO Auto-generated method stub
		try {
			ModbusRequest request = new ReadMultipleRegistersRequest(
					ENERGY_REF, 2);
			request.setUnitID(UNIT_ID);
			ModbusTCPTransaction transaction = this.getTransaction();
			transaction.setRequest(request);
			transaction.execute();
			ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) transaction
					.getResponse();
			byte[] bytes1 = response.getRegister(0).toBytes();
			byte[] bytes2 = response.getRegister(1).toBytes();
			return ModbusUtil.registersToInt(ArraysOperation.mergeBytes(bytes1,
					bytes2)) * getEnergyScale();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
