package com.example.shems.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import android.util.Log;

public class StaticFunc {

    //保留六位小数
    public static String FloatString2FloatString(String s)
    {
        String temp=null;
        try
        {
            DecimalFormat df=new DecimalFormat("0.000000");
            temp= df.format(Float.parseFloat(s));

        }
        catch(Exception e)
        {
            Log.i("temp",temp);
        }
        return temp;
    }

    /**
     * 通过网站域名URL获取该网站的源码
     * @param url
     * @return String
     * @throws Exception
     */
    public static String getURLSource(URL url) throws Exception    {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        //把二进制数据转化为byte字节数据
        String htmlSource = readInputStream(inStream);
        return htmlSource;
    }



    /**
     * 把二进制流转化为byte字节数组
     * @param instream
     * @return byte[]
     * @throws Exception
     */
    public static String readInputStream(InputStream instream) throws Exception {
        File file = new File("data_weather.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int max = 60000;
        long amount = 0;
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
            amount = amount+len;
            if(amount>max){		//modify
                break;
            }
        }
//        String a=new String(new byte[]{01,00,02});
//        System.out.println(a);
        String result = new String(outStream.toByteArray());

        bw.write(result);
        outStream.close();
        outStream = new ByteArrayOutputStream();
        amount = 0;
        len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
            amount = amount+len;
            if(amount>max){		//modify
                break;
            }
        }

        String s = new String(outStream.toByteArray());
        result+=s;
        bw.write(s);

        instream.close();
        bw.close();
        return result;
    }
}
