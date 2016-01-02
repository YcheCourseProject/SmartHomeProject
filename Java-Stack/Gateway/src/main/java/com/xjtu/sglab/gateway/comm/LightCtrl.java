package com.xjtu.sglab.gateway.comm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class LightCtrl {
	public static final byte[] SNIFF_BYTES = { 0x68, 0x64, 0x00, 0x12, 0x71,
			0x67, (byte) 0xac, (byte) 0xcf, 0x23, 0x2b, 0x04, (byte) 0xa4,
			0x20, 0x20, 0x20, 0x20, 0x20, 0x20 };
	public static final byte[] AUTHENTIFICATION_BYTES = { 0x68, 0X64, 0X00,
			0X1e, 0X63, 0X6c, (byte) 0Xac, (byte) 0Xcf, 0X23, 0X2b, 0X04,
			(byte) 0Xa4, 0X20, 0X20, 0X20, 0X20, 0X20, 0X20, (byte) 0Xa4, 0X04,
			0X2b, 0X23, (byte) 0Xcf, (byte) 0Xac, 0X20, 0X20, 0X20, 0X20, 0X20,
			0X20 };
	public static final byte[] SEND_BYTES={
		0x68,0x64,0x00,0x1e,0x64,0x63,
		(byte) 0xac,(byte) 0xcf,0x23,0x2b,0x04,(byte) 0xa4,
		0x20,0x20,0x20,0x20,0x20,0x20,
		0x04,(byte) 0xab,(byte) 0xef,0x0b,0x38,(byte) 0xe3,
		0x01,0x29,0x00,(byte) 0xd2,(byte) 0x91,(byte) 0xe9};
	
	public static InetAddress sniffModules(String severip) {
		DatagramSocket clientSocket = null;
		InetAddress addr = null;
		try {
			clientSocket = new DatagramSocket(10000);
			DatagramPacket sendPacket = new DatagramPacket(SNIFF_BYTES,
					SNIFF_BYTES.length, InetAddress.getByName(severip), 10000);
			clientSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			
			clientSocket.setSoTimeout(5000);
			while(true){
				clientSocket.receive(receivePacket);
				byte[] realData = receivePacket.getData();
				addr = receivePacket.getAddress();
				System.out.println("1:"+InetAddress.getLocalHost().getHostAddress());
				System.out.println("2:"+receivePacket.getAddress().getHostAddress());
				System.out.println(Arrays.toString(realData));
				if(!InetAddress.getLocalHost().getHostAddress().equals(receivePacket.getAddress().getHostAddress()))
				{
					return receivePacket.getAddress();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail" + "sniff");
			e.printStackTrace();
		} finally {
			if(clientSocket!=null)
				clientSocket.close();
		}
		return addr;
	}

	public static void confirm(InetAddress inetAddress) {
		DatagramSocket clientSocket = null;
		InetAddress addr = null;
		try {
			clientSocket = new DatagramSocket(10000);
			DatagramPacket sendPacket = new DatagramPacket(AUTHENTIFICATION_BYTES,
					AUTHENTIFICATION_BYTES.length, inetAddress, 10000);
			clientSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			clientSocket.setSoTimeout(5000);
			byte[] realData = receivePacket.getData();
			addr = receivePacket.getAddress();
			System.out.println(receivePacket.getSocketAddress());
			System.out.println(Arrays.toString(realData));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail" + "confirm");
			e.printStackTrace();
		} finally {
			if(clientSocket!=null)
				clientSocket.close();
		}
	}

	public static void control(int index,boolean On,InetAddress inetAddress){
		DatagramSocket clientSocket = null;
		byte[] sendBytes=Arrays.copyOf(SEND_BYTES, 30);
		sendBytes[22]=(byte) (Math.random()*256);
		sendBytes[23]=(byte) (Math.random()*256);
		if(On==true){
			sendBytes[24]=1;
		}
		else
			sendBytes[24]=0;
		switch(index){
		case 1:
			sendBytes[25]=0x29;
			break;
		case 2:
			sendBytes[25]=0x2a;
			break;
		case 3:
			sendBytes[25]=0x2b;
			break;
		}
		
		try {
			clientSocket = new DatagramSocket(10000);
			DatagramPacket sendPacket = new DatagramPacket(sendBytes,
					sendBytes.length, inetAddress, 10000);
			clientSocket.send(sendPacket);
			System.out.println("SendBytes"+Arrays.toString(sendBytes));
			System.out.println("success" + "control");
			
		} catch (Exception e) {
			System.out.println("fail" + "control");
		} finally {
			if(clientSocket!=null)
				clientSocket.close();
		}
	}
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		InetAddress inetAddress=sniffModules("192.168.1.255");
		confirm(inetAddress);
		Thread.sleep(3000);
		for(int i=1;i<4;i++)
			control(i,false,inetAddress);
	}
}
