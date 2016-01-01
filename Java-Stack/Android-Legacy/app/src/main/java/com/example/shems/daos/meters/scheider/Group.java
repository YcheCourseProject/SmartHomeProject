package com.example.shems.daos.meters.scheider;

import com.example.shems.util.CommonFunction;

/**
 * @author cyl Group 只是把数据一捆绑
 * 按照modbus抓包的wareshark解析来看得
 */
public class Group // 对于功能码20的一个数据封装的类
{
    private byte referenceType;
    private byte[] fileNumBytes; // 2个BYTE
    private byte[] referenceNumBytes; // 2个BYTE
    private byte[] wordCountBytes; // 2个BYTE
    private byte[] getBytes; // 7个BYTE
    byte[] getBytesFromGroup()
    {
        return this.getBytes;
    }

    Group(int referenceType, int fileNum, int referenceNum, int wordCount) {
        this.getBytes = new byte[7];
        this.referenceType = (byte) referenceType;
        this.fileNumBytes = CommonFunction.int2bytes(fileNum, 2); // fileNumBytes
        // 2Bytes
        this.referenceNumBytes = CommonFunction.int2bytes(referenceNum, 2); // referenceNumBytes
        // 2Bytes
        this.wordCountBytes = CommonFunction.int2bytes(wordCount, 2); // wordCountBytes
        // 2Bytes
        int index = 0;
        getBytes[index] = this.referenceType;
        index++;
        for (int i = 0; i < 2; i++) {
            getBytes[index] = this.fileNumBytes[i];
            index++;
        }
        for (int i = 0; i < 2; i++) {
            getBytes[index] = this.referenceNumBytes[i];
            index++;
        }
        for (int i = 0; i < 2; i++) {
            getBytes[index] = this.wordCountBytes[i];
            index++;
        }
    }



}
