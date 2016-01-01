package com.example.shems.daos.meters.scheider;
import java.util.Date;


import android.content.Context;
import android.util.Log;

import com.example.shems.daos.meters.Meter;
import com.example.shems.daos.sqllite.HisLogDB;
import com.example.shems.models.load_info.ElectricalLogInfo;

public class Scheider  extends Meter {


    public int getPriCurrent(){
        try {
            //read the 3104 registers
            int[] result = connector.getRegistersDataForNonjamod(3200, 1);

            if(result != null){
                return result[0];
            }else{
                return -1;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public int getSecCurrent(){
        try {
            //read the 3206 registers
            int[] result = connector.getRegistersDataForNonjamod(3201, 1);

            if(result != null){
                return result[0];
            }else{
                return -1;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public int getPriVoltage(){
        try {
            //read the 3200 registers
            int[] result = connector.getRegistersDataForNonjamod(3204, 1);

            if(result != null){
                return result[0];
            }else{
                return -1;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public int getSecVoltage(){
        try {
            //read the 3201 registers
            int[] result = connector.getRegistersDataForNonjamod(3206, 1);

            if(result != null){
                return result[0];
            }else{
                return -1;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public int getConType(){
        try {
            //read the 3201 registers
            int[] result = connector.getRegistersDataForNonjamod(3199, 1);

            if(result != null){
                switch(result[0]){
                    case 10:
                        return 0;
                    case 11:
                        return 1;
                    case 12:
                        return 2;
                    case 30:
                        return 3;
                    case 31:
                        return 4;
                    case 40:
                        return 5;
                    case 42:
                        return 6;
                }
                return -1;
            }else{
                return -1;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * 改变互感比和连接方式
     * @return
     */
    public boolean changeBasicSetting(int CTN, int CTD,int PTN, int PTD,  int type){
        int[] data = new int[1];
        boolean b = false;
        try{

            //first step, command
            data[0] = 9020;
            b = connector.writeRegistersDataForNonJamod(7999, 1, data);
            if(!b){
                return false;
            }

            //change PT primary
            data = new int[1];
            data[0] = PTN;
            b = connector.writeRegistersDataForNonJamod(3204, 1, data);
            if(!b){
                return false;
            }

            //change PT second
            data[0] = PTD;
            b = connector.writeRegistersDataForNonJamod(3206, 1, data);
            if(!b){
                return false;
            }

            //change connection type
            switch(type){
                case 0:
                    type = 10;
                case 1:
                    type = 11;
                case 2:
                    type = 12;
                case 3:
                    type = 30;
                case 4:
                    type = 31;
                case 5:
                    type = 40;
                case 6:
                    type = 42;
                default:
                    type = 10;
            }
            data[0] = type;
            b = connector.writeRegistersDataForNonJamod(3199, 1, data);
            if(!b){
                return false;
            }

            //change CT primary
            data[0] = CTN;
            b = connector.writeRegistersDataForNonJamod(3200, 1, data);
            if(!b){
                return false;
            }

            //change CT second
            data[0] = CTD;
            b = connector.writeRegistersDataForNonJamod(3201, 1, data);
            if(!b){
                return false;
            }

            //end
            data[0] = 1;
            b = connector.writeRegistersDataForNonJamod(8000, 1, data);
            if(!b){
                return false;
            }

            //end
            data[0] = 9021;
            b = connector.writeRegistersDataForNonJamod(7999, 1, data);
            if(!b){
                return false;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    public  void getLog(Context context)
    {
        try
        {
            //6999 reference num  :存的是有关目前log的状态的所有参数
            this.connector.connectNonjamod(this.connector.ipAddressStr, this.connector.port, this.connector.unitID, this.connector.type);
            byte b[]=((ScheiderConnector)(this.connector)).getSendReadHoldingregisternumsPackage(6999,20);
            byte[] bytes=this.connector.sendPackageAndGetResponseNoBlock(b,b.length).data;

            //要做一些判断  之后再做 ！！！！！！！！！！
            int []ints=((ScheiderConnector)(this.connector)).parseReadHoldRegistersPackageToDataBytes(bytes);	//这个时候这个bytecount起到了很大的租用
            int referencesize=ints[4];	//包括了对应有时间的寄存器了
            int size=ints[7];		//size表示记录的总共条数
            int startreferencenum=ints[8];
            int endreferecenum=ints[9];
            int interval=ints[15];//分钟应该是ints[14]
            Log.i("referencesize:",""+referencesize);
            Log.i("size:",""+size);
            Log.i("start:",""+startreferencenum);
            Log.i("end:",""+endreferecenum);
            Log.i("interval:",""+interval);

            HisLogDB db_operator=new HisLogDB(context);
            db_operator.open();
            //startreferencenum
            //下面开始收集数据：  导入数据库中
            if(endreferecenum<startreferencenum)
            {
                endreferecenum+=5000;
            }
            for(int j=endreferecenum;j>startreferencenum;j--)
            {
                int  registernum=j;
                if(j>37768)
                    registernum-=5000;
                byte []sendbytes=((ScheiderConnector)(this.connector)).getSendReadGeneralReferencePackage(new Group[]{new Group(6,0x0001,registernum,referencesize)});
                byte []retbytes=this.connector.sendPackageAndGetResponseNoBlock(sendbytes, sendbytes.length).data;
                ElectricalLogInfo info=((ScheiderConnector)(this.connector)).ParseReadGeneralReference(retbytes);
                if(info.getBytes_length()>0)
                {
                    Date date=info.getDate();
                    float ap=info.getAp();
                    float rp=info.getRp();
                    float power_factor=info.getPower_factor();
                    if(db_operator.insert(date, ap,rp,power_factor)==false)	//把存的已经插了就不用继续往前了
                    {
                        Log.i("exits", "insert fail");
                        break;
                    }
                    Log.i("log in", date.toString()+" " +ap+"rp:"+rp+"factor"+power_factor);
                }
            }
            Log.i("get log end","end");
            db_operator.close();
            try {
                Thread.sleep(200);			//关闭需要一段时间
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("getlog,fail");
        }
    }





}
