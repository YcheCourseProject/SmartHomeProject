package com.xjtu.sglab.gateway.comm.plug;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class AbstractPlugCtrl {
	private String plugIP;
	private static int QUIC_PORT = 80;
	public abstract byte[] getSwitchOnBytes();
	public abstract byte[] getSwitchOffBytes();
	
	public String getPlugIP() {
		return plugIP;
	}
	public void setPlugIP(String plugIP) {
		this.plugIP = plugIP;
	}
	
	
	public AbstractPlugCtrl(String plugIP) {
		super();
		this.plugIP = plugIP;
	}
	
	public  void switchOn() {
		DatagramSocket clientSocket = null;
		try {
			 clientSocket= new DatagramSocket();
			DatagramPacket sendPacket = new DatagramPacket(getSwitchOnBytes(),
					getSwitchOnBytes().length, InetAddress.getByName(plugIP), QUIC_PORT);
			clientSocket.send(sendPacket);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail"+ "switchOn");
		}
		finally{
			clientSocket.close();
		}
	}

	public  void switchOff() {
		DatagramSocket clientSocket = null;
		try {
			 clientSocket = new DatagramSocket();
			DatagramPacket sendPacket = new DatagramPacket(getSwitchOffBytes(),
					getSwitchOffBytes().length, InetAddress.getByName(plugIP),
					QUIC_PORT);
			clientSocket.send(sendPacket);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail"+ "switchOff");
		}
		finally{
			clientSocket.close();
		}
	}
}
