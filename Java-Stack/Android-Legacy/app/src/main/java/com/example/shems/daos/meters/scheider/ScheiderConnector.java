package com.example.shems.daos.meters.scheider;

import com.example.shems.models.load_info.ElectricalLogInfo;
import com.example.shems.util.CommonFunction;
import com.example.shems.daos.meters.Connector;

import android.util.Log;

public class ScheiderConnector extends Connector {

    /**
     * 作者：刘加贺
     * 仅仅用于施耐德的寄存器的读取，为了节省代码用的函数，
     * 不可在其他地方使用 用于读取1个寄存器，判断它是否是32768，如果是，就置为0
     * @param referenceNum 神马
     * @param registerNum 神马
     * @return 神马
     */
    public String getRegistersDataForNonjamodForScheider(int referenceNum,
                                                         int registerNum) {
        byte[] b1 = {(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x06, (byte) 0xf7, (byte) 0x03,
                (byte) 0xc3, (byte) 0x5d, (byte) 0x00, (byte) 0x02};
        b1[6] = (byte) unitID;
        b1[8] = (byte) (referenceNum / 0x0100);
        b1[9] = (byte) (referenceNum % 0x0100);
        b1[10] = (byte) (registerNum / 0x0100);
        b1[11] = (byte) (registerNum % 0x0100);

        b1 = sendPackageAndGetResponseNoBlock(b1, b1.length).data;
        // if the received package's function code != 0x03,fail
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
        if (result[0] == 32768) {
            return "NaN";
        }

        return result[0] + "";
    }

    /**
     * @param referencenum (int) 寄存器号码一般是两个字节的无符号数
     * @param wordcount    (int) 寄存器的个数
     * @return (byte[]) 要发的包的内容 function :modbus的function code 0x03的向meter发包的构建
     * attention://功能跟Connector 中的方法有重复
     */
    public byte[] getSendReadHoldingregisternumsPackage(int referencenum,
                                                        int wordcount)// 构建手机发的包 function code 3
    {
        int functioncode = 3;
        int length = 1 + 1 + 2 + 2;
        int index = 0; // sendbytes index
        byte[] sendbytes = new byte[2 + 2 + 2 + 1 + 1 + 2 + 2];
        byte[] tranidbytes = CommonFunction.int2bytes(0, 2); // transaction id
        // 为了简单处理固定为0x00
        // 0x00
        byte[] protoclbytes = CommonFunction.int2bytes(0, 2);// modbus协议 固定0x00
        // 0x00
        byte[] unitIdbytes = CommonFunction.int2bytes(this.unitID, 1);
        byte[] lengthbytes = CommonFunction.int2bytes(length, 2);
        byte[] referencenumbytes = CommonFunction.int2bytes(referencenum, 2);
        byte[] wordcountbytes = CommonFunction.int2bytes(wordcount, 2);

        sendbytes[index] = tranidbytes[0];
        index++; // transaction id 2Bytes
        sendbytes[index] = tranidbytes[1];
        index++;
        sendbytes[index] = protoclbytes[0];
        index++; // protocol id 2Bytes
        sendbytes[index] = protoclbytes[1];
        index++;
        sendbytes[index] = lengthbytes[0];
        index++; // length 2Bytes
        sendbytes[index] = lengthbytes[1];
        index++;
        sendbytes[index] = unitIdbytes[0];
        index++; // unit id 1Bytes

        sendbytes[index] = (byte) (functioncode);
        index++; // function 1Bytes
        sendbytes[index] = referencenumbytes[0];
        index++; // reference number 2Bytes
        sendbytes[index] = referencenumbytes[1];
        index++;
        sendbytes[index] = wordcountbytes[0];
        index++; // word count 2Bytes
        sendbytes[index] = wordcountbytes[1];

        return sendbytes;
    }

    /**
     * 作者：车煜林
     * 功能码 0x10 Write Multiple Registers
     * @param ref (int)
     * @param  data
     * @return  (byte[])
     */
    public byte[] getSendWriteMultipleRegPackage(int ref, int[] data)
    {
        int index = 13;
        int length = 1 + 1 + 2 + 2 + 1;
        int wordCount = data.length;
        byte byteCount = (byte) (wordCount * 2);

        byte[] sendBytes = new byte[2 + 2 + 2 + 1 + 1 + 2 + 2 + 1
                + data.length * 2];
        byte[] tranIDBytes = CommonFunction.int2bytes(0, 2);
        byte[] protocolBytes = CommonFunction.int2bytes(0, 2);
        byte[] unitIDBytes = CommonFunction.int2bytes(this.unitID, 1);

        byte[] referenceNumBytes = CommonFunction.int2bytes(ref, 2);
        byte[] wordCountBytes = CommonFunction.int2bytes(wordCount, 2);
        // byte count只有一个 Byte
        sendBytes[0] = tranIDBytes[0]; // transaction id 2Bytes
        sendBytes[1] = tranIDBytes[1];
        sendBytes[2] = protocolBytes[0]; // protocol id 2Bytes
        sendBytes[3] = protocolBytes[1];

        length += byteCount & 0xff; // byte按位的内容转int 一定要注意！！！！！！！！
        byte[] lengthBytes = CommonFunction.int2bytes(length, 2);
        System.out.println(lengthBytes[0]);
        System.out.println(lengthBytes[1]);
        sendBytes[4] = lengthBytes[0];
        sendBytes[5] = lengthBytes[1];
        // 4、5是长度
        sendBytes[6] = unitIDBytes[0]; // unit id 1Bytes
        sendBytes[7] = (byte) 0x10; // function code 1Bytes
        sendBytes[8] = referenceNumBytes[0]; // reference num 2Bytes
        sendBytes[9] = referenceNumBytes[1];
        sendBytes[10] = wordCountBytes[0]; // wordCount 2Bytes 也就是记录的条数
        sendBytes[11] = wordCountBytes[1];
        sendBytes[12] = byteCount; // byteCount 1Byte
        // registers的内容
        for (int aData : data) {
            // 有问题
            byte tempBytes[] = CommonFunction.int2bytes(aData, 2);
            sendBytes[index] = tempBytes[0];
            sendBytes[index + 1] = tempBytes[1];
            index += 2;
        }
        return sendBytes;
    }

    /**
     * 构建Modbus包头
     * 作者：车煜林
     * @param bytes  载荷
     * @param length
     * @return  包头
     */
    public byte[] getPackageHead(byte[] bytes, int length) {
        byte[] tranidbytes = CommonFunction.int2bytes(0, 2);
        byte[] protocolbytes = CommonFunction.int2bytes(0, 2);
        byte[] lengthbytes = CommonFunction.int2bytes(length, 2);
        int index = 0;
        for (int i = 0; i < 2; i++) {
            bytes[index] = tranidbytes[i];
            index++;
        }
        for (int i = 0; i < 2; i++) {
            bytes[index] = protocolbytes[i];
            index++;
        }
        for (int i = 0; i < 2; i++) {
            bytes[index] = lengthbytes[i];
            index++;
        }
        bytes[6] = (byte) this.unitID;
        return bytes;
    }

    /**
     * 功能码：0x14
     * 作者：车煜林
     * @param groups 相应的对象
     * @return
     */
    public byte[] getSendReadGeneralReferencePackage(Group[] groups)
    {
        byte[] getbytes = new byte[7 + 2 + groups.length * 7];
        int length = 1 + 1 + 1 + 7 * groups.length;
        getbytes = getPackageHead(getbytes, length);
        byte bytecount = (byte) (7 * groups.length);
        getbytes[7] = 20;
        getbytes[8] = bytecount;

        int index = 9; // 后面都是group的内容
        for (Group group : groups) {
            byte[] bytesInfo = group.getBytesFromGroup();
            for (int j = 0; j < 7; j++) // 都是7Bytes
            {
                getbytes[index] = bytesInfo[j];
                index++;
            }
        }
        return getbytes;
    }

    public ElectricalLogInfo ParseReadGeneralReference(byte[] toMeBytes)
    {
        int allbytesnum = toMeBytes[8] & 0xff;        //表示下面还有的总共的字节数
        byte[] retbytes = new byte[300];            //之后用类中的个数来确定有多少个实际上的数据
        int actualbytenum = 0;            //记录实际会有多少个
        int index = 9;                    //下标9开始是后面各个group的内容
        int subbytesnum = toMeBytes[index] & 0xff;        //表示后面还要读多少个
        index++;                //指向了reference type
        while (allbytesnum > 0) {

            index++;            //跳过一个Byte 的reference type		指向了data
            for (int i = 0; i < subbytesnum - 1; i++) {
                retbytes[actualbytenum] = toMeBytes[index];
                index++;                    //toMeBytes下标
                actualbytenum++;            //总数据个数
            }
            allbytesnum -= subbytesnum + 1;        //看还剩下几个
            subbytesnum = toMeBytes[index] & 0xff;        //这时候要把剩下的子串的值得到
        }
        return new ElectricalLogInfo(retbytes, actualbytenum);
    }

    /**
     * author:cyl
     * 读取function code 0x14 并且进行解析
     * @return 神马
     */
    public ElectricalLogInfo receiveReadGeneralRefereceNonBlockAndParse()    //未解析前后面都是0注意！！！！！！！！！！
    {
        byte[] bytes = new byte[300];
        try {
            this.fis.read(bytes);
        } catch (Exception e) {
            Log.i("error", "function code 0x14 read");
        }
        return this.ParseReadGeneralReference(bytes);
    }

    /**
     * 作者：车煜林
     * function code 0x03
     * @param toMeBytes
     * @return
     */
    public int[] parseReadHoldRegistersPackageToDataBytes(byte[] toMeBytes)
    {
            int bytecount = CommonFunction.bytes2int(new byte[]{toMeBytes[8]});
            //貌似寄存器就是偶数的编号
            if(bytecount %2!=0)
                return null;
            int index = 9;
            int[] ints = new int[bytecount / 2];
            int intsindex = 0;

            for (int i = 0; i < bytecount; i += 2) {
                int tempRegisterData = CommonFunction.bytes2int(new byte[]{toMeBytes[index + i], toMeBytes[index + i + 1]});
                ints[intsindex] = tempRegisterData;
                intsindex++;
            }
            return ints;
        }
}
