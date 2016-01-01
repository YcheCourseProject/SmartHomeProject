package com.example.shems.models.load_info;

import java.util.Date;
import android.util.Log;
import com.example.shems.util.CommonFunction;
/**
 * @author cyl
 *	主要把读到的后面的一堆0给解析掉的
 *  寄存器  只存一个浮点类型的数据  否则会出错
 *	并且把function code =0x14的byte[]内容解析
 */
public  class ElectricalLogInfo
{
    private byte[]bytes;
    private int bytes_length;
    private Date date=new Date();
    private float ap;
    private float rp;
    private float power_factor;
    public ElectricalLogInfo(byte[] bytes, int bytes_length)
    {
        this.bytes=new byte[bytes_length];
        for(int i=0;i<bytes_length;i++)
        {
            this.bytes[i]=bytes[i];
        }
        this.bytes_length=bytes_length;
        date.setYear((bytes[2]&0xff));
        date.setMonth((bytes[0]&0xff)-1);		//要减去1因为这是跟1月比较的差量
        date.setDate(bytes[1]&0xff);
        date.setHours(bytes[3]&0xff);
        date.setMinutes(bytes[4]&0xff);
        date.setSeconds(bytes[5]&0xff);
        long type_long_date=date.getTime();
        type_long_date=(type_long_date/1000)*1000;
        date.setTime(type_long_date);
        int i=6;
        this.ap=CommonFunction.bytes2float(new byte[]{bytes[i],bytes[i+1],bytes[i+2],bytes[i+3]});
        i+=4;
        this.rp=CommonFunction.bytes2float(new byte[]{bytes[i],bytes[i+1],bytes[i+2],bytes[i+3]});
        i+=4;
        this.power_factor=CommonFunction.bytes2float(new byte[]{bytes[i],bytes[i+1],bytes[i+2],bytes[i+3]});
    }
    public float getRp() {
        return rp;
    }
    public void setRp(float rp) {
        this.rp = rp;
    }
    public float getPower_factor() {
        return power_factor;
    }
    public float getAp() {
        return ap;
    }
    public void setAp(float ap) {
        this.ap = ap;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public int getBytes_length() {
        return bytes_length;
    }
    public void setBytes_length(int bytes_length) {
        this.bytes_length = bytes_length;
    }
    public void show()
    {
        //前六个Byte是  :   月，日，年，时，分，秒
        Log.i("date log:",date.toLocaleString());
        for(int i=6;i<this.bytes.length;i+=4)
        {
            Log.i("current log:",""+CommonFunction.bytes2float(new byte[]{bytes[i],bytes[i+1],bytes[i+2],bytes[i+3]}));
        }
    }

}