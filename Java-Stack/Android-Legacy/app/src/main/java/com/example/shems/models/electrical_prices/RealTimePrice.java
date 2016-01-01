package com.example.shems.models.electrical_prices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealTimePrice {
    // 对应9个地区的电价
    public static String region1 = "MAINE";
    public static String region2 = "NEWHAMPSHIRE";
    public static String region3 = "VERMONT";
    public static String region4 = "WCMASS";
    public static String region5 = "SEMASS";
    public static String region6 = "NEMASSBOST";
    public static String region7 = "CONNECTICUT";
    public static String region8 = "RHODEISLAND";
    public static String region9 = "INTERNAL_HUB";

    /**
     * 通过网站域名URL获取该网站的源码，这边不用改
     *
     * @param url
     * @return String
     * @throws Exception
     */

    public static String getURLSource(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream(); // 通过输入流获取html二进制数据
        // 把二进制数据转化为byte字节数据
        String htmlSource = readInputStream(inStream);
        return htmlSource;
    }

    /**
     * 把二进制流转化为byte字节数组,这边还要再修改成安卓上的文件的路径
     *
     * @param instream
     * @return byte[]
     * @throws Exception
     */

    public static String readInputStream(InputStream instream) throws Exception {
        File file = new File("data_weather.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1204];
        int max = 60000;
        long amount = 0;
        int len = 0;
        while ((len = instream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
            amount = amount + len;
            if (amount > max) { // modify
                break;
            }
        }
        String result = new String(outStream.toByteArray());

        bw.write(result);
        bw.flush();
        outStream.close();

        outStream = new ByteArrayOutputStream();
        amount = 0;
        len = 0;
        while ((len = instream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
            amount = amount + len;
            if (amount > max) { // modify
                break;
            }
        }

        String s = new String(outStream.toByteArray());
        result += s;
        bw.write(s);

        instream.close();
        bw.close();
        return result;
    }/**
     * 解析对应的价格的txt文本
     *
     * @param file
     * @return
     */
    public static List<RegionPrice> parsetxt(File file) {
        BufferedReader br = null;
        List<RegionPrice> list = new ArrayList<RegionPrice>();
        String readlinestr = null;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((readlinestr = br.readLine()) != null) {
                    list.add(parseLine(readlinestr));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        list = handleList(list);
        return list;
    }

    public static List<RegionPrice> handleList(List<RegionPrice> list) {
        if (list == null)
            return list;
        else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).needmodify == true) {
                    int j;
                    for (j = i + 1; j < list.size(); j++) {
                        if (list.get(j).needmodify == false) // 进行修改
                        {
                            list.get(i).prices = list.get(j).prices;
                        }
                    }
                }
            }
        }
        return list;
    }

    public static int monthMatch(String month) {
        String str = month.substring(0, 3);
        if (str.equalsIgnoreCase("Jan"))
            return 1;
        else if (str.equalsIgnoreCase("Feb"))
            return 2;
        else if (str.equalsIgnoreCase("Mar"))
            return 3;
        else if (str.equalsIgnoreCase("Apr"))
            return 4;
        else if (str.equalsIgnoreCase("May"))
            return 5;
        else if (str.equalsIgnoreCase("Jun"))
            return 6;
        else if (str.equalsIgnoreCase("Jul"))
            return 7;
        else if (str.equalsIgnoreCase("Aug"))
            return 8;
        else if (str.equalsIgnoreCase("Sep"))
            return 9;
        else if (str.equalsIgnoreCase("Oct"))
            return 10;
        else if (str.equalsIgnoreCase("Nov"))
            return 11;
        else if (str.equalsIgnoreCase("Dec"))
            return 12;
        else
            return -1;// 表示没有
    }

    public static RegionPrice parseLine(String linestr) // parse readline
    {

        Date date = new Date();
        String[] strs_raw_spilt = linestr.split("[ ]+");
        String[] strs_price;
        String[] strs_clock;
        String datestr = strs_raw_spilt[0];
        String nextstr = strs_raw_spilt[1];
        strs_raw_spilt = datestr.split("[-]+");
        date.setDate(Integer.parseInt(strs_raw_spilt[0]));
        date.setMonth(monthMatch(strs_raw_spilt[1]) - 1);
        date.setYear(Integer.parseInt(strs_raw_spilt[2]) - 1900);
        strs_price = nextstr.split("[,]+"); // strs_price分割出价格来
        String timestr = strs_price[0];

        strs_clock = timestr.split("[:]+");
        date.setHours(Integer.parseInt(strs_clock[0]));
        date.setMinutes(Integer.parseInt(strs_clock[1]));
        date.setSeconds(Integer.parseInt(strs_clock[2]));
        float prices[] = new float[strs_price.length - 2];

        for (int i = 2; i < strs_price.length; i++) {
            prices[i - 2] = Float.parseFloat(strs_price[i].replaceAll("[$]+",
                    ""));
        }
        RegionPrice regionPrice = new RegionPrice(date);
        if (prices != null && prices.length != 9) // 短路运算符
        {
            float prices_substitude[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            regionPrice.add(prices_substitude);
        } else {
            regionPrice.add(prices);
        }
        return regionPrice;
    }
}
