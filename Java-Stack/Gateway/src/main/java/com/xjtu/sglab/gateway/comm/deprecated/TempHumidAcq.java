package com.xjtu.sglab.gateway.comm.deprecated;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;


public class TempHumidAcq {
	public static int TMP_HUM_PORT = 8000;
	public static String TMP_HUM_SERVER_IP = "10.0.0.152";
	public static void disTempratureHummidity(String tmphumip) {
		String temprature;
		String humidity;
		try {
			StringBuffer sb = new StringBuffer();
			Socket clientSocket = new Socket(
					InetAddress.getByName(TMP_HUM_SERVER_IP), TMP_HUM_PORT);
			BufferedReader infromServer = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream(),"ASCII"));
			int tempint;
			while (true) {
				tempint = infromServer.read();
				if (tempint == '%') {

					String tempstr = sb.toString();
					System.out.println(tempstr);
					String tempstrs[] = tempstr.split(":");
					temprature = tempstrs[1].substring(1, 6);
					System.out.println(tempstrs[1]);
					humidity = tempstrs[1].substring(8, 12);
					System.out.println("Tmp:" + temprature + ";" + "humidity"
							+ humidity);
					sb = new StringBuffer();
				} else
					sb.append((char) (tempint));

			}
		} catch (Exception e) {
			System.out.println("Fail"+"TmpHumidFail");
			// TODO: handle exception
		}
}
}