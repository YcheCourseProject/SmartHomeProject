package com.example.shems.daos.meters.sentron;

import com.example.shems.daos.meters.Connector;
import com.example.shems.util.CommonFunction;

public class SentronConnector extends Connector {

    public byte[] PAC4200WriteMultiRegsPasswordRequest(short startAddress, short[] values, short password) {
        byte[] data = new byte[8 + (2 * values.length)];
        data[0] = 0x67;
        data[1] = (byte) ((startAddress >> 8) & 0x00ff);
        data[2] = (byte) ((startAddress % 0x0100));
        data[3] = (byte) (values.length / 0x100);
        data[4] = (byte) (values.length % 0x100);
        data[5] = (byte) (2 * values.length);
        int index = 6;
        short num2;
        for (short value : values) {
            num2 = value;
            data[index] = (byte) (num2 / 0x100);
            index++;
            data[index] = (byte) (num2 % 0x100);
            index++;
        }
        data[data.length - 2] = (byte) (password / 0x100);
        data[data.length - 1] = (byte) (password % 0x100);

        byte[] buffer2 = CommonFunction.calculateCRC(data, 0, data.length);
        data[data.length - 2] = buffer2[0];
        data[data.length - 1] = buffer2[1];
        return data;
    }

    /**
     * 添加上包头，包括transitionID，length，unitID等等,仅仅在密码保护开启时发包时候使用
     *
     * @return 神马
     */
    public byte[] addPackageHead(byte[] pack, short transitionID, byte unitID) {
        byte[] data = new byte[7 + pack.length];
        data[0] = (byte) (transitionID >> 8);
        data[1] = (byte) (transitionID);
        data[2] = 0x00;
        data[3] = 0x00;
        data[4] = (byte) ((pack.length + 1) >> 8);
        data[5] = (byte) (pack.length + 1);
        data[6] = unitID;
        System.arraycopy(pack, 0, data, 7, pack.length);
        return data;
    }


}
