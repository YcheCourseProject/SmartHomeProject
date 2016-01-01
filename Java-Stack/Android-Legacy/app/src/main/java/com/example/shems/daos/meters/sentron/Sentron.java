package com.example.shems.daos.meters.sentron;

import android.util.Log;

import com.example.shems.daos.meters.Meter;

import net.wimpi.modbus.util.ModbusUtil;


public class Sentron extends Meter {
    /**
     * get the sentron's connection type
     *
     * @return
     * 	0:3p4w; 1:3p3w ; 2: 3p4wb: 3:3p3wb; 4:1p2w
     * 	if -1 the operation is fail,so please check the return value
     */
    public int getSentronConType(){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b == null || b.length < 40){
                Log.i("sentron", "encapsulate length wrong");
                return -1;
            }
            //read the 50001 registers
            int[] result = connector.getRegistersDataForNonjamod(50001, 2);

            return (result == null)?-1:(result[0]*0x10000)+result[1];
        }catch(Exception ex){
            ex.printStackTrace();
            Log.i("sentron", "Exception");
            return -1;
        }
    }



    /**
     * change the sentron's connection type
     *
     * @param conType:integer
     *    0:3p4w; 1:3p3w ; 2: 3p4wb: 3:3p3wb; 4:1p2w
     * @return
     */
    public boolean changeSentronConType(int conType){
        if(conType<0 || conType>4){
            return false;
        }
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50001 registers

            int[] data = new int[2];
            data[0] = conType / 0x10000;
            data[1] = conType % 0x10000;
            return connector.writeRegistersDataForNonJamod(50001, 2, data);

        }catch(Exception ex){
            return false;
        }
    }

    /**
     * get the sentron's primary current
     * @return
     * 	if -1 the operation is fail,so please check the return value
     */
    public int getSentronPriCurrent(){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return -1;
            }
            //read the 50011 registers to get the primary current

            int[] result = connector.getRegistersDataForNonjamod(50011, 2);

            return (result == null)?-1:(result[0]*0x10000)+result[1];

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }



    /**
     * change the sentron's primary current
     * @param curValue:integer
     * @return
     */
    public boolean changeSentronPriCurrent(int curValue){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50011 registers to change the primary current

            int[] data = new int[2];
            data[0] = curValue / 0x10000;
            data[1] = curValue % 0x10000;

            return connector.writeRegistersDataForNonJamod(50011, 2, data);

        }catch(Exception ex){
            return false;
        }
    }



    /**
     * get the sentron's second current
     * @return
     * 	if -1 the operation is fail,so please check the return value
     */
    public int getSentronSecCurrent(){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return -1;
            }
            //read the 50013 registers to get the primary current
            int[] result = connector.getRegistersDataForNonjamod(50013, 2);

            return (result == null)?-1:(result[0]*0x10000)+result[1];
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }



    /**
     * change the sentron's second current
     * @param curValue:integer
     * @return
     */
    public boolean changeSentronSecCurrent(int curValue){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50013 registers to change the second current

            int[] data = new int[2];
            data[0] = curValue / 0x10000;
            data[1] = curValue % 0x10000;

            return connector.writeRegistersDataForNonJamod(50013, 2, data);

        }catch(Exception ex){
            return false;
        }
    }


    /**
     * set if the voltage transformer is open
     * @param isOpen: true for open and false for not open
     * @return
     *
     */
    public boolean setSentronVoltageTran(boolean isOpen){
        //encapsulate
        try{
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50003 registers to change the voltage transformer

            int[] data = new int[2];
            data[0] = 0;
            data[1] = (isOpen == true)?1:0;

            return connector.writeRegistersDataForNonJamod(50003, 2, data);

        }catch(Exception ex){
            return false;
        }
    }

    /**
     * get the sentron's primary Voltage
     * @return
     * 	if -1 the operation is fail,so please check the return value
     */
    public int getSentronPriVoltage(){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return -1;
            }
            //read the c355 registers to get the primary Voltage
            int[] result = connector.getRegistersDataForNonjamod(50005, 2);
            return (result == null)?-1:(result[0]*0x10000)+result[1];

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }



    /**
     * change the sentron's primary voltage
     * @param curValue:integer
     * @return
     */
    public boolean changeSentronPriVoltage(int curValue){
        try {
            //to open the voltage transformer first
            if(!this.setSentronVoltageTran(true)){
                return false;
            }
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50005 registers to change the primary current

            int[] data = new int[2];
            data[0] = curValue / 0x10000;
            data[1] = curValue % 0x10000;

            return connector.writeRegistersDataForNonJamod(50005, 2, data);

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * get the sentron's second Voltage
     * @return
     * 	if -1 the operation is fail,so please check the return value
     */
    public int getSentronSecVoltage(){
        try {
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return -1;
            }
            //read the 50007 registers to get the primary current
            int[] result = connector.getRegistersDataForNonjamod(50007, 2);

            return (result == null)?-1:(result[0]*0x10000)+result[1];
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * change the sentron's second current
     * @param curValue:integer
     * @return
     */
    public boolean changeSentronSecVoltage(int curValue){
        try {
            //to open the voltage transformer first
            if(!this.setSentronVoltageTran(true)){
                return false;
            }
            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                return false;
            }
            //write to the 50007 registers to change the second current
            int[] data = new int[2];
            data[0] = curValue / 0x10000;
            data[1] = curValue % 0x10000;

            return connector.writeRegistersDataForNonJamod(50007, 2, data);

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return int[2]:int[0] is the passwd,int[1] is the state(1 for protection,otherwise 0)
     * 			null if fail
     */
    public int[] getSentronPasswdAndState(){
        int result[] = new int[2];
        try {
            //get the passwdState
            int[] tempArray = connector.getRegistersDataForNonjamod(63009, 2);
            if(tempArray == null){
                return null;
            }
            result[1] = tempArray[0] * 0x10000 + tempArray[1];
            //violate the passwd
            byte obuf[] = new byte[261];
            obuf[2] = 0x00;
            obuf[3] = 0x00;
            obuf[4] = 0x00;
            obuf[5] = 0x0d;
            obuf[6] = 0x0d;
            byte[] data = new byte[]{0x67, (byte) 0xff,0x0e,0x00,0x02,0x04,0x00,0x00,0x16,0x2e,0x16,0x2e};
            byte[] tempByteArray;
            int len = data.length;
            for(int i=0;i<9999;i++){
                data[len-4] = (byte)(i>>8);
                data[len-3] = (byte)(i);
                data[len-2] = data[len-4];
                data[len-1] = data[len-3];
                //获得密码的byte数组
//                tempByteArray = CRCGenerator.CalculateCrc(data, 0, data.length);
                tempByteArray=new byte[2];
                int[] tempIntArray=ModbusUtil.calculateCRC(data,0,data.length);
                tempByteArray[0]= (byte) tempIntArray[0];
                tempByteArray[1]= (byte) tempIntArray[1];
                data[data.length - 2] = tempByteArray[0];
                data[data.length - 1] = tempByteArray[1];
                //构建最终发的包
                obuf[0] = (byte)(i>>8);
                obuf[1] = (byte)(i);
                for(int j=0;j<data.length;j++){
                    obuf[j+7] = data[j];
                }
                tempByteArray = connector.sendPackageAndGetResponseNoBlock(obuf,19).data;
                if(tempByteArray[7] != 0x67){
                }else{
                    result[0] = i;
                    break;
                }
            }
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * change the passwd State
     * @param currentPasswd: short
     * @param passwdState:short(0 for nonprotection,1 for protection)
     * @return
     */
    public boolean changeSentronPasswdState(short currentPasswd, short passwdState){
        if(passwdState == 0){
            return (changeSentronPasswd(currentPasswd, currentPasswd, passwdState) == -1)?false:true;
        }else{
            try {
                //encapsulate
                byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
                b[6] = (byte)connector.unitID;
                b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
                //not exactly
                if(b.length < 40){
                    return false;
                }
                //input passwd
                //byte[] b1 = {0x00,0x07,0x00,0x00,0x00,0x0d,(byte) 0xf7,0x67,(byte) 0xff,0x0e,0x00,0x02,0x04,0x00,0x00,0x00,(byte) 0x02,(byte) 0xa7,(byte) 0xb3};
                short[] value = new short[2];
                value[0] = 0;
                value[1] = currentPasswd;
                b = ((SentronConnector)(connector)).PAC4200WriteMultiRegsPasswordRequest((short)0xff0e, value, currentPasswd);
                b = ((SentronConnector)(connector)).addPackageHead(b,(short)2,(byte)connector.unitID);
                b = connector.sendPackageAndGetResponseNoBlock(b,b.length).data;
                if(b[7] != 0x67){
                    return false;
                }
                //change passwd state
                //byte[] b2 = {(byte)0x00,(byte)0x0a,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x15,(byte)0xf7,(byte)0x67,(byte)0xff,(byte)0x0e,(byte)0x00,(byte)0x06,(byte)0x0c,
                //		(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x1a,(byte)0x31};
                value = new short[2];
                value[0] = 0;
                value[1] = passwdState;
                b = ((SentronConnector)(connector)).PAC4200WriteMultiRegsPasswordRequest((short)0xff12, value, currentPasswd);
                b = ((SentronConnector)(connector)).addPackageHead(b,(short)3,(byte)connector.unitID);
                b = connector.sendPackageAndGetResponseNoBlock(b,b.length).data;
                if(b[7] != 0x67){
                    Log.i("FailState", b[7]+"");
                    return false;
                }

                return true;
            }catch(Exception ex){
                ex.printStackTrace();
                return false;
            }
        }
    }
    /**
     *	change the Password
     * @param currentPasswd : short
     * @param passwdState : short(1:protection;0:nonProtection)
     * @return
     *  if success, return the current password, otherwise return -1
     */
    public int changeSentronPasswd(short currentPasswd, short desPasswd, short passwdState){
        try {

            //encapsulate
            byte[] b = {(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0xf7,(byte)0x2b,(byte)0x0e,(byte)0x01,(byte)0x00};
            b[6] = (byte)connector.unitID;
            b = connector.sendPackageAndGetResponseNoBlock(b, b.length).data;
            //not exactly
            if(b.length < 40){
                Log.i("log", "leng<40");
                return -1;
            }
            //input passwd
            //byte[] b1 = {0x00,0x07,0x00,0x00,0x00,0x0d,(byte) 0xf7,0x67,(byte) 0xff,0x0e,0x00,0x02,0x04,0x00,0x00,0x00,(byte) 0x02,(byte) 0xa7,(byte) 0xb3};
            short[] value = new short[2];
            value[0] = 0;
            value[1] = currentPasswd;
            b = ((SentronConnector)(connector)).PAC4200WriteMultiRegsPasswordRequest((short)0xff0e, value, currentPasswd);
            b = ((SentronConnector)(connector)).addPackageHead(b,(short)2,(byte)((SentronConnector)(connector)).unitID);



            b = connector.sendPackageAndGetResponseNoBlock(b,b.length).data;
            if(b[7] != 0x67){
                Log.i("log", "input passwd fail"+b[6]+ " "+ b[7]);
                return -1;
            }
            //change passwd
            //byte[] b2 = {(byte)0x00,(byte)0x0a,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x15,(byte)0xf7,(byte)0x67,(byte)0xff,(byte)0x0e,(byte)0x00,(byte)0x06,(byte)0x0c,
            //		(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x1a,(byte)0x31};
            value = new short[6];
            value[0] = 0;
            value[1] = currentPasswd;
            value[2] = 0;
            value[3] = desPasswd;
            value[4] = 0;
            value[5] = passwdState;
            b = ((SentronConnector)(connector)).PAC4200WriteMultiRegsPasswordRequest((short)0xff0e, value, currentPasswd);
            b = ((SentronConnector)(connector)).addPackageHead(b,(short)3,(byte)((SentronConnector)(connector)).unitID);
            b = connector.sendPackageAndGetResponseNoBlock(b,b.length).data;
            if(b == null){
                return -1;
            }
            int n = 0x00ff & b[7];
            if(b[7] != 0x67){
                //表示失败
                return -1;
            }
            return desPasswd;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * used by sentron, to format the integer password to the string for showing
     * @param pwd
     * @return
     */
    public static String changeIntToPasswdStr(int pwd){
        String result = String.valueOf(pwd);
        int len = result.length();
        while(len<4){
            result = 0 + result;
            len++;
        }
        return result;
    }
}
