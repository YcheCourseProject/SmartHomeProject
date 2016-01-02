package com.xjtu.sglab.exp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.xjtu.sglab.gateway.comm.ArduinoSensorDAO;

public class ExpSensor {
	public static void main(String[] args) throws UnknownHostException {
//		List<InetAddress> inetAddresses = ArduinoSensorDAO
//				.sniffModules("192.168.1.255");
//		System.out.println(inetAddresses.size());
//		for(InetAddress addr :inetAddresses){
//			ArduinoSensorDAO.gather(addr);
//		}
		String result=ArduinoSensorDAO.gather(InetAddress.getByName(Constants.Sensor.IP));
		String s[]=result.split(",");
		String temperature=s[0].split(":")[1];
		String humidity=s[1].split(":")[1];
		String light=s[2].split(":")[1];
		String gas=s[3].split(":")[1];
		String fire=s[4].split(":")[1];
		String human=s[5].split(":")[1];
		System.out.println(temperature);
		System.out.println(humidity);
		System.out.println(light);
		System.out.println(gas);
		System.out.println(fire);
		System.out.println(human);
	}
}
