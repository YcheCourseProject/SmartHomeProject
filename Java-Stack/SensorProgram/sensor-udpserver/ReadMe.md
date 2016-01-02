## 程序说明
Arduino Board作为udpserver，注册监听2811端口：

``` cpp
#define LISTEN_PORT_UDP 2811
```

当接收到"where are you arduino"的UDP数据包时候，返回IP和设备名称

当接收"Start"的时候，采集传感器数据并通过UDP数据包返回

``` cpp
char clientString[500];
sprintf(clientString, "%s%d,%s%d,%s%d,%s%d,%s%d,%s%d", "T:", DHT.temperature, "H:", DHT.humidity, "L:", light, "G:", gas, "F:", fire, "HS:", state, "\n");
        udpServer.writeData(clientString, strlen(clientString), (sockaddr*)&sender);
        Serial.println(clientString);
```

## 代码结构
项目主程序：

udptest文件夹下的udptest.ino

项目使用了三个lib，分别是libs文件夹下的：

- Adafruit_CC3000_Library-master 板载lib
- dht11 传感器数据采集lib
- UDPServer UDP服务器lib，参考自["Benpart的代码"](http://forums.adafruit.com/viewtopic.php?f=25&t=55322)

