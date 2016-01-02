package com.xjtu.sglab.gateway.comm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArduinoSensorDAO {
	public static final String SNIFF_STRING = "where are you arduino";
	public static final String DAO_STRING = "start";
	public static final int SENSOR_PORT = 2811;

	public static List<InetAddress> sniffModules(String severip) {
		List<InetAddress> inetAddressList = new ArrayList<InetAddress>();
		DatagramSocket clientSocket = null;
		try {
			clientSocket = new DatagramSocket(10000);
			DatagramPacket sendPacket = new DatagramPacket(
					SNIFF_STRING.getBytes(), SNIFF_STRING.getBytes().length,
					InetAddress.getByName(severip), SENSOR_PORT);
			clientSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);

			clientSocket.setSoTimeout(3000);
			while (true) {
				clientSocket.receive(receivePacket);
				byte[] realData = receivePacket.getData();
				System.out.println(InetAddress.getLocalHost().getHostAddress());
				System.out.println(receivePacket.getAddress().getHostAddress());
				System.out.println(Arrays.toString(realData));
				if (!InetAddress.getLocalHost().getHostAddress()
						.equals(receivePacket.getAddress().getHostAddress())) {
					inetAddressList.add(receivePacket.getAddress());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			return inetAddressList;
		} finally {
			if (clientSocket != null)
				clientSocket.close();
		}
	}

	
	
	public static String gather(InetAddress inetAddress) {
		String result = null;
		DatagramSocket clientSocket = null;
		InetAddress addr = null;
		try {
			clientSocket = new DatagramSocket();
			DatagramPacket sendPacket = new DatagramPacket(
					DAO_STRING.getBytes(), DAO_STRING.getBytes().length,
					inetAddress, SENSOR_PORT);
			clientSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.setSoTimeout(3000);
			clientSocket.receive(receivePacket);
			byte[] realData = receivePacket.getData();
			addr = receivePacket.getAddress();
//			System.out.println(receivePacket.getSocketAddress());
//			System.out.println(Arrays.toString(realData));
//			System.out.println(new String(realData));
			result=new String(realData);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail" + "gather");
			e.printStackTrace();
		} finally {
			if (clientSocket != null)
				clientSocket.close();
		}
		return result;
	}
}
