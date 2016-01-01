package com.example.shems.daos.meters.ge;

import com.example.shems.util.CommonFunction;
import com.example.shems.daos.meters.Connector;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;

public class GEConnector extends Connector {

    public String getF7Data(int a, int b, int num, int PTN, int PTD, int CTN, int CTD) {
        double temp = 0;
        readMultipleRegisterRequest = new ReadMultipleRegistersRequest(a, b);
        readMultipleRegisterRequest.setUnitID(this.unitID);
        modbusTCPTransaction = new ModbusTCPTransaction(tcpMasterConnection);
        modbusTCPTransaction.setRequest(readMultipleRegisterRequest);
        try {
            modbusTCPTransaction.execute();
        } catch (ModbusIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ModbusSlaveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ModbusException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        readMultipleRegistersResponse = (ReadMultipleRegistersResponse) modbusTCPTransaction.getResponse();

        int ls = ((readMultipleRegistersResponse.getRegisterValue(0) & 0xffff) << 16 | (readMultipleRegistersResponse.getRegisterValue(1) & 0xffff));

        switch (num) {
            case 1:
                temp = (double) ls / 65536 * (PTN / PTD);
                //conAct.tot1 += temp;
                break;
            case 2:
                temp = (double) ls / 65536 * (PTN / PTD);
                //conAct.tot2 += temp;
                break;
            case 3:
                temp = (double) ls / 65536 * (CTN / CTD);
                //conAct.tot3 += temp;
                break;
            case 4:
                temp = (double) ls / 65536 * (PTN / PTD) * (CTN / CTD);
                //conAct.tot4 += temp;
                break;
        }
        String str = String.valueOf(temp);
        if (str.length() <= 11)
            return str;
        else
            return str.substring(0, 10);
    }

    /**
     * 功能码为写和读都可以用这个，大不了返回的内容的起始位置不同
     *
     * @param lineStr      神马
     * @param transitionID 神马
     * @return 神马
     */
    public String sendReadPackageAndShowResponse(String lineStr, int transitionID) {
        //自己构建的包从0x0036开始 6+(48+6)*3=168
        String result = "";
        try {

            if (lineStr == null) return "";
            lineStr = lineStr.substring(174);
            //把transitionID添加到报文头部
            lineStr = CommonFunction.changeIntegerToHexStr(2, transitionID, "|") + lineStr;
            int lineStrLen = lineStr.length();
            byte[] b = new byte[lineStrLen];
            int bIndex = 0;
            ResponsePackage response;
            //将00|00|格式的报文字符串转化成byte[]
            for (int i = 0; i < lineStrLen; ) {
                b[bIndex++] = (byte) CommonFunction.changeHexStrToInteger(lineStr.substring(i, i + 2));
                i = i + 3;
            }

            response = sendPackageAndGetResponseNoBlock(b, bIndex);

            if (response.data == null) {
                return "";
            } else {

                for (int i = 9; i < response.packageLen; i++) {
                    //if(i >= 9){

                    result = result + CommonFunction.changeIntegerToHexStr(1, response.data[i] & 0x00ff, "");
                    //}
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
            //System.exit(1);
        }
        return result;
    }

    /**
     * only used for send the data read back to meter
     *
     * @param lineStr      神马
     * @param dataStr      神马
     * @param transitionID 神马
     * @param index        神马
     * @return 神马
     */
    public int sendWritePackageAndShowResponse(String lineStr, String dataStr, int transitionID, int index) {
        //自己构建的包从0x0036开始 6+(48+8)*3=168,从transition identification之后开始
        try {
            if (lineStr == null || lineStr.equals("")) return -1;
            lineStr = lineStr.substring(174);
            lineStr = CommonFunction.changeIntegerToHexStr(2, transitionID, "|") + lineStr;
            int lineStrLen = lineStr.length();

            byte[] b = new byte[lineStrLen];
//            ResponsePackage response = null;
            //第一步先把电表数据之前的包内容转化
            int bIndex = 0;
            //45是包括了数据的前4个字节的
            for (int i = 0; i < 51; ) {
                b[bIndex++] = (byte) CommonFunction.changeHexStrToInteger(lineStr.substring(i, i + 2));
                i = i + 3;
            }
            //第二步把电表数据填入
            byte data;
            for (int i = 0; i < 64; i++) {
                data = (byte) CommonFunction.changeHexStrToInteger(dataStr.substring((index + i) * 2, (index + i) * 2 + 2));
                b[bIndex++] = data;
            }
            //第三步把每一行结尾的00 01填入
            b[bIndex++] = 00;
            b[bIndex++] = 01;
//            response = sendPackageAndGetResponseNoBlock(b, bIndex);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return index + 64;
    }
}
