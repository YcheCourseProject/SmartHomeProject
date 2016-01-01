package com.example.shems.daos.meters;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.util.Log;

import com.example.shems.util.CommonFunction;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.ModbusUtil;

/**
 * only used for build socket connection at the first time
 * 其中变量 type的含义(0:GE),(1:sentron)
 */
public class Connector {
    public static final int conWaitTime = 2000;
    public static int TRANSACTION_ID = 0;

    public TCPMasterConnection tcpMasterConnection;
    public ModbusTCPTransaction modbusTCPTransaction; // the transaction
    public ReadMultipleRegistersRequest readMultipleRegisterRequest; // the request
    public ReadMultipleRegistersResponse readMultipleRegistersResponse; // the response

    public InetAddress inetAddress;
    public String ipAddressStr;
    public int type = 0;
    public int port;
    public int unitID;

    public Socket socket = null;
    public SocketAddress socketAddress = null;
    public OutputStream os = null;
    public FilterInputStream fis = null;

    public boolean connectJamod(String addr, int port, int unitID, int type) {
        this.disconnect();
        try {
            this.ipAddressStr=addr;
            this.port = port;
            this.unitID = unitID;
            this.type = type;
            this.inetAddress = InetAddress.getByName(addr);
            this.tcpMasterConnection = new TCPMasterConnection(this.inetAddress);
            this.tcpMasterConnection.setPort(this.port);
            this.tcpMasterConnection.setTimeout(Connector.conWaitTime);
            this.tcpMasterConnection.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public String getIntegerData(int ref, int count) {
        readMultipleRegisterRequest = new ReadMultipleRegistersRequest(ref, count);
        readMultipleRegisterRequest.setUnitID(this.unitID);
        // 4. Prepare the transaction
        modbusTCPTransaction = new ModbusTCPTransaction(tcpMasterConnection);
        modbusTCPTransaction.setRequest(readMultipleRegisterRequest);
        try {
            modbusTCPTransaction.execute();
        } catch (ModbusIOException e) {
            // TODO Auto-generated catch block
        } catch (ModbusSlaveException e) {
            // TODO Auto-generated catch block
        } catch (ModbusException e) {
            // TODO Auto-generated catch block
        }
        int result = ((readMultipleRegistersResponse.getRegisterValue(0) & 0xffff) << 16 | (readMultipleRegistersResponse.getRegisterValue(1) & 0xffff));
        return String.valueOf(result);

    }

    public String getFloatData(int ref, int count) {
        float floatVal;
        readMultipleRegisterRequest = new ReadMultipleRegistersRequest(ref, count);
        readMultipleRegisterRequest.setUnitID(this.unitID);
        modbusTCPTransaction = new ModbusTCPTransaction(tcpMasterConnection);
        modbusTCPTransaction.setRequest(readMultipleRegisterRequest);
        try {
            modbusTCPTransaction.execute();
        } catch (ModbusException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        readMultipleRegistersResponse = (ReadMultipleRegistersResponse) modbusTCPTransaction.getResponse();
        floatVal = ModbusUtil.registersToFloat(CommonFunction.bytesMerger(
                readMultipleRegistersResponse.getRegister(0).toBytes(),
                readMultipleRegistersResponse.getRegister(1).toBytes()));
        return CommonFunction.floatWith2Bit(floatVal);
    }

    public String getDoubleData(int ref, int count) {
        double doubleVal;
        readMultipleRegisterRequest = new ReadMultipleRegistersRequest(ref, count);
        readMultipleRegisterRequest.setUnitID(this.unitID);
        modbusTCPTransaction = new ModbusTCPTransaction(tcpMasterConnection);
        modbusTCPTransaction.setRequest(readMultipleRegisterRequest);
        try {
            modbusTCPTransaction.execute();
        } catch (ModbusException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        readMultipleRegistersResponse = (ReadMultipleRegistersResponse) modbusTCPTransaction.getResponse();
        doubleVal = ModbusUtil.registersToDouble(
                CommonFunction.bytesMerger(readMultipleRegistersResponse.getRegister(0).toBytes(),
                        readMultipleRegistersResponse.getRegister(1).toBytes(),
                        readMultipleRegistersResponse.getRegister(2).toBytes(),
                        readMultipleRegistersResponse.getRegister(3).toBytes()));
        return CommonFunction.doubleWith2Bit(doubleVal);
    }

    public void disconnect() {
        if (this.tcpMasterConnection != null && this.tcpMasterConnection.isConnected()) {
            this.tcpMasterConnection.close();
        }
        if (this.socket != null && !this.socket.isClosed()) {
            try {
                if (this.os != null) {
                    this.os.close();
                }
                if (this.fis != null) {
                    this.fis.close();
                }
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }

        }
    }

    public boolean connectNonjamod(String addr, int port, int unitID, int type) {
        this.disconnect();
        try {
            this.ipAddressStr=addr;
            this.port = port;
            this.unitID = unitID;
            this.type = type;
            socketAddress = new InetSocketAddress(addr, port);
            socket = new Socket();
            socket.connect(socketAddress, Connector.conWaitTime);
            socket.setSoTimeout(conWaitTime);
            os = socket.getOutputStream();
            fis = new BufferedInputStream(socket.getInputStream());
            Log.i("connection", "socket_con success");
            // GE
            if (this.type == 0) {
                byte[] b = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x03, (byte) 0xf6, (byte) 0x21, (byte) 0x00, (byte) 0x02};
                b[6] = (byte) this.unitID;
                b = sendPackageAndGetResponseNoBlock(b, b.length).data;
                if (b != null) {
                    Log.i("connect", "true");
                    return true;
                } else {
                    return false;
                }
                // 西门子
            } else if (this.type == 1) {
                byte[] b = {(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0xf7, (byte) 0x2b, (byte) 0x0e, (byte) 0x01, (byte) 0x00};
                b[6] = (byte) this.unitID;
                Log.i("connection", "send package");
                b = sendPackageAndGetResponseNoBlock(b, b.length).data;
                Log.i("connection", "receive package");
                return b != null;
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public int[] getRegistersDataForNonjamod(int referenceNum, int registerNum) {
        byte[] b1 = {(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0xf7, (byte) 0x03, (byte) 0xc3, (byte) 0x5d, (byte) 0x00, (byte) 0x02};
        b1[6] = (byte) unitID;
        b1[8] = (byte) (referenceNum / 0x0100);
        b1[9] = (byte) (referenceNum % 0x0100);
        b1[10] = (byte) (registerNum / 0x0100);
        b1[11] = (byte) (registerNum % 0x0100);

        b1 = sendPackageAndGetResponseNoBlock(b1, b1.length).data;
        if (b1 == null || b1[7] != 0x03) {
            Log.i("owen", "getRegistersDataForNonjamod" + (b1 != null ? b1[7] : 0));
            return null;
        }

        int[] result = new int[registerNum];
        int index = 9;
        for (int i = 0; i < registerNum; i++) {
            result[i] = (b1[index++] & 0x00ff);
            result[i] = (result[i] << 8) | (b1[index++] & 0x00ff);
        }

        return result;
    }

    public float[] getFloatForNonJamod(int referenceNum, int num) {
        int[] tempData;
        float[] result = new float[num];
        for (int i = 0; i < num; i++) {
            tempData = this.getRegistersDataForNonjamod(referenceNum + i * 2, 2);
            result[i] = Float.intBitsToFloat(tempData[0] * 0x10000 + tempData[1]);
        }
        return result;
    }

    public boolean writeRegistersDataForNonJamod(int refNum, int regNum, int[] data) {
        byte[] b1 = new byte[13 + data.length * 2];
        b1[0] = (byte) (TRANSACTION_ID / 0x100);
        b1[1] = (byte) (TRANSACTION_ID % 0x100);
        TRANSACTION_ID++;
        b1[5] = (byte) (b1.length - 6);
        b1[6] = (byte) unitID;
        b1[7] = (byte) 0x10;
        b1[8] = (byte) (refNum / 0x100);
        b1[9] = (byte) (refNum % 0x100);
        b1[10] = (byte) (data.length / 0x100);
        b1[11] = (byte) (data.length % 0x100);
        b1[12] = (byte) (data.length * 2);
        int index = 13;
        for (int aData : data) {
            b1[index++] = (byte) (aData / 0x100);
            b1[index++] = (byte) (aData % 0x100);
        }
        String str = "";
        for (int i = 0; i < index; i++) {
            str = str + CommonFunction.changeIntegerToHexStr(1, b1[i] & 0x00ff, "") + " ";
        }
        ResponsePackage rp = sendPackageAndGetResponseNoBlock(b1, b1.length);
        b1 = rp.data;

        str = "";
        for (int i = 0; i < rp.packageLen; i++) {
            str = str + CommonFunction.changeIntegerToHexStr(1, b1[i] & 0x00ff, "") + " ";
        }
        //if the received package's function code != 0x10,fail
        return !(b1 == null || b1[7] != 0x10);
    }

    public ResponsePackage sendPackageAndGetResponseNoBlock(byte[] data, int len) {
        int responseLength;
        byte[] buffer = new byte[261];
        try {
            os.write(data, 0, len);
            responseLength = fis.read(buffer, 0, 261);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponsePackage();
        }
        return new ResponsePackage(buffer, responseLength);
    }

    public class ResponsePackage {
        public int packageLen = 0;
        public byte[] data = null;

        ResponsePackage() {
            packageLen = 0;
            data = null;
        }

        public ResponsePackage(byte[] data, int packageLen) {
            this.packageLen = packageLen;
            this.data = data;
        }
    }


}
