package com.xjtu.sglab.gateway.comm;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import com.xjtu.sglab.exp.Constants;


public class ACctrl {
	static String AC_SERVER_IP = Constants.AirConditioner.IP;
	static int AC_SERVER_PORT = 60000;
	static byte AC_CODE_ON = (byte) 0x55;
	static byte AC_CODE_OFF = (byte) 0x80;
	public static enum AC_MODE {
		COLD, WARM, VENTILATION, DEHYDRATION
	}
	public static void setACSettings(byte sendbyte) {
		Socket clientSocket=new Socket();
		try {
			clientSocket= new Socket(
					InetAddress.getByName(AC_SERVER_IP), AC_SERVER_PORT);
			BufferedReader infromServer = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			DataOutputStream outToServer = new DataOutputStream(
					clientSocket.getOutputStream());
			outToServer.writeByte(sendbyte);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Fail" + "SetACsettings");
		}
		finally
		{
			try {
				clientSocket.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

	}
	
	
	public static void setACTemperatureMode(int temperature, AC_MODE mode) {
		byte temp;
		switch (mode) {
		case COLD:
			temp = (byte) 0x40;
			break;
		case WARM:
			temp = (byte) 0x50;
			break;
		case VENTILATION:
			temp = (byte) 0x60;
			break;
		case DEHYDRATION:
			temp = (byte) 0x70;
			break;
		default:
			temp = (byte) 0x40;
		}
		if (temperature < 16 || temperature > 30)
			temperature = 21;
		temp = (byte) (temp+(temperature-16));
		setACSettings(temp);
	}
	public static void onAC() {
		setACSettings(AC_CODE_ON);
	}
	public static void OffAC() {
		setACSettings(AC_CODE_OFF);
	}
	
}
