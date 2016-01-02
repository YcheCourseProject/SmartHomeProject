package com.xjtu.sglab.exp;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import com.xjtu.sglab.gateway.comm.ArduinoSensorDAO;
import com.xjtu.sglab.gateway.withserver.SensorComm;

public class ExpSensorServer {

	public static void main(String[] args) {
		// Sensor
			Timer tiemr4 = new Timer();
			tiemr4.schedule(new TimerTask() {
				String ip=Constants.Sensor.IP;
				
				public String getIp() {
					return ip;
				}

				public void setIp(String ip) {
					this.ip = ip;
				}

				@Override
				public void run() {
					try {
						
						String result = ArduinoSensorDAO.gather(InetAddress
								.getByName(getIp()));
						System.out.println(result);
						String s[] = result.split(",");
						String temperature = s[0].split(":")[1];
						System.out.println(temperature);
						String humidity = s[1].split(":")[1];
						String light = s[2].split(":")[1];
						String gas = s[3].split(":")[1];
						String fire = s[4].split(":")[1];
						String human = s[5].split(":")[1];
						SensorComm.getInstance().saveFlameSensorInfo(
								Float.parseFloat(fire), Constants.Sensor.ID);
						SensorComm.getInstance().saveGasSensorInfo(
								Float.parseFloat(gas), Constants.Sensor.ID);
						SensorComm.getInstance().saveLightSensorInfo(
								Float.parseFloat(light), Constants.Sensor.ID);
						SensorComm.getInstance().savePlrSensorInfo(
								Float.parseFloat(human), Constants.Sensor.ID);
						SensorComm.getInstance().saveTemperatureSensorInfo(
								Float.parseFloat(temperature), Constants.Sensor.ID);
						SensorComm.getInstance().saveHumidtySensorInfo(
								Float.parseFloat(humidity), Constants.Sensor.ID);
					} catch (Exception e) {
						setIp(ArduinoSensorDAO.sniffModules("192.168.1.255").get(0).getHostAddress());
					}
				}
			}, 1000, 10000);

	}

}
