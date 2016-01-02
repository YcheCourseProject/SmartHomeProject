#include <UDPServer.h>

#include <Adafruit_CC3000.h>
#include <Adafruit_CC3000_Server.h>
#include <ccspi.h>

#include <SPI.h>
#include <string.h>
#include "utility/debug.h"
#include <dht11.h>
#include "UDPServer.h"

dht11 DHT;

// Config the interrupt and control pins on Wido
#define ADAFRUIT_CC3000_IRQ   7
#define ADAFRUIT_CC3000_VBAT  5
#define ADAFRUIT_CC3000_CS    10
#define sensorPin 9     /*for human detection*/
#define indicator 13
#define DHT11_PIN 8     /*for tem and humidity*/

Adafruit_CC3000 cc3000 = Adafruit_CC3000(ADAFRUIT_CC3000_CS, ADAFRUIT_CC3000_IRQ, ADAFRUIT_CC3000_VBAT,
                         SPI_CLOCK_DIVIDER); // you can change this clock speed but DI

//Please enter the SSID and password of the router you want to connect
#define WLAN_SSID       "smartgridlab_2"
#define WLAN_PASS       "sichuanmeizi"

#define DEBUG 1

// Security can be WLAN_SEC_UNSEC, WLAN_SEC_WEP, WLAN_SEC_WPA or WLAN_SEC_WPA2
#define WLAN_SECURITY   WLAN_SEC_WPA2
#define TIMEOUT_MS 2000
//#define LISTEN_PORT 8080    /*What TCP port to listen on for connection*/


#define UDP_READ_BUFFER_SIZE 20
#define LISTEN_PORT_UDP 2811
UDPServer udpServer(LISTEN_PORT_UDP);

char AppareilId[7] = "CC3000";

uint32_t ipAddress, netmask, gateway, dhcpserv, dnsserv;

void setup() {
  Serial.begin(115200);
  //  while (!Serial) {
  //    ; // wait for serial port to connect. Needed for Leonardo only
  //  }
  delay(2000);
  Serial.println(F("Hello, CC3000!\n"));
  displayDriverMode();
  // Measure the free Ram
  Serial.print(F("Free RAM: ")); Serial.println(getFreeRam(), DEC);

  /* Initialise the module */
  Serial.println(F("\nInitializing the CC3000 ..."));
  if (!cc3000.begin())
  {
    Serial.println(F("Unable to initialize the CC3000! Check your wiring?"));
    while (1);
  }

  uint16_t firmware = checkFirmwareVersion();
  if (firmware < 0x113) {
    Serial.println(F("Wrong firmware version!"));
    for (;;);
  }

  displayMACAddress();

  /* Optional: Get the SSID list (not available in 'tiny' mode) */
  //#ifndef CC3000_TINY_DRIVER
  listSSIDResults();
  //#endif

  /* Delete any old connection data on the module */
  Serial.println(F("\nDeleting old connection profiles"));
  if (!cc3000.deleteProfiles()) {
    Serial.println(F("Failed!"));
    while (1);
  }

  /* Optional: Revert back from static IP addres to use DHCP.
   See note for setStaticIPAddress above, this only needs to be
   called once and will be remembered afterwards by the CC3000.
  */

  if (!cc3000.setDHCP()) {
    Serial.println(F("Failed to set DHCP!"));
    while (1);
  }

  /* Attempt to connect to an access point */
  char *ssid = WLAN_SSID;             /* Max 32 chars */
  Serial.print(F("\nAttempting to connect to ")); Serial.println(ssid);

  /* NOTE: Secure connections are not available in 'Tiny' mode!
    By default connectToAP will retry indefinitely, however you can pass an
    optional maximum number of retries (greater than zero) as the fourth parameter.
  */
  if (!cc3000.connectToAP(WLAN_SSID, WLAN_PASS, WLAN_SECURITY)) {
    Serial.println(F("Failed!"));
    while (1);
  }

  Serial.println(F("Connected!"));

  /* Wait for DHCP to complete */
  Serial.println(F("Request DHCP"));
  while (!cc3000.checkDHCP())
  {
    delay(100); // ToDo: Insert a DHCP timeout!
  }

  /* Display the IP address DNS, Gateway, etc. */
  while (! displayConnectionDetails()) {
    delay(1000);
  }

  pinMode(sensorPin, INPUT);
  pinMode(indicator, OUTPUT);
  pinMode(DHT11_PIN, INPUT);

  udpServer.begin();

  Serial.println(F("udp server start up!"));
}

void loop() {
  if (cc3000.checkConnected())
  {
    checkUDP();
    delay(50);
    //Serial.print(F("Free RAM: ")); Serial.println(getFreeRam(), DEC);
  }
  else
  {
    cc3000.reboot();
    //here the code to connect to AP
    /* Attempt to connect to an access point */
    char *ssid = WLAN_SSID;             /* Max 32 chars */
    Serial.print(F("\nAttempting to connect to ")); Serial.println(ssid);

    /* NOTE: Secure connections are not available in 'Tiny' mode!
      By default connectToAP will retry indefinitely, however you can pass an
      optional maximum number of retries (greater than zero) as the fourth parameter.
    */
    if (!cc3000.connectToAP(WLAN_SSID, WLAN_PASS, WLAN_SECURITY)) {
      Serial.println(F("Failed!"));
      while (1);
    }
    while (udpServer.Reboot() == 0);
  }

}

/**************************************************************************/
/*!
    @brief  Displays the driver mode (tiny of normal), and the buffer
            size if tiny mode is not being used

    @note   The buffer size and driver mode are defined in cc3000_common.h
*/
/**************************************************************************/
void displayDriverMode(void)
{
#ifdef CC3000_TINY_DRIVER
  Serial.println(F("CC3000 is configure in 'Tiny' mode"));
#else
  Serial.print(F("RX Buffer : "));
  Serial.print(CC3000_RX_BUFFER_SIZE);
  Serial.println(F(" bytes"));
  Serial.print(F("TX Buffer : "));
  Serial.print(CC3000_TX_BUFFER_SIZE);
  Serial.println(F(" bytes"));
#endif
}

/**************************************************************************/
/*!
    @brief  Tries to read the CC3000's internal firmware patch ID
*/
/**************************************************************************/
uint16_t checkFirmwareVersion(void)
{
  uint8_t major, minor;
  uint16_t version;

#ifndef CC3000_TINY_DRIVER
  if (!cc3000.getFirmwareVersion(&major, &minor))
  {
    Serial.println(F("Unable to retrieve the firmware version!\r\n"));
    version = 0;
  }
  else
  {
    Serial.print(F("Firmware V. : "));
    Serial.print(major); Serial.print(F(".")); Serial.println(minor);
    version = major; version <<= 8; version |= minor;
  }
#endif
  return version;
}

/**************************************************************************/
/*!
    @brief  Tries to read the 6-byte MAC address of the CC3000 module
*/
/**************************************************************************/
void displayMACAddress(void)
{
  uint8_t macAddress[6];

  if (!cc3000.getMacAddress(macAddress))
  {
    Serial.println(F("Unable to retrieve MAC Address!\r\n"));
  }
  else
  {
    Serial.print(F("MAC Address : "));
    cc3000.printHex((byte*)&macAddress, 6);
  }
}

/**************************************************************************/
/*!
    @brief  Tries to read the IP address and other connection details
*/
/**************************************************************************/
bool displayConnectionDetails(void)
{
  uint32_t ipAddress, netmask, gateway, dhcpserv, dnsserv;

  if (!cc3000.getIPAddress(&ipAddress, &netmask, &gateway, &dhcpserv, &dnsserv))
  {
    Serial.println(F("Unable to retrieve the IP Address!\r\n"));
    return false;
  }
  else
  {
    Serial.print(F("\nIP Addr: ")); cc3000.printIPdotsRev(ipAddress);
    Serial.print(F("\nNetmask: ")); cc3000.printIPdotsRev(netmask);
    Serial.print(F("\nGateway: ")); cc3000.printIPdotsRev(gateway);
    Serial.print(F("\nDHCPsrv: ")); cc3000.printIPdotsRev(dhcpserv);
    Serial.print(F("\nDNSserv: ")); cc3000.printIPdotsRev(dnsserv);
    Serial.println();
    return true;
  }
}


/**************************************************************************/
/*!
    @brief  Begins an SSID scan and prints out all the visible networks
*/
/**************************************************************************/

void listSSIDResults(void)
{
  uint8_t valid, rssi, sec, index;
  char ssidname[33];

  index = cc3000.startSSIDscan();

  Serial.print(F("Networks found: ")); Serial.println(index);
  Serial.println(F("================================================"));

  while (index) {
    index--;

    valid = cc3000.getNextSSID(&rssi, &sec, ssidname);

    Serial.print(F("SSID Name    : ")); Serial.print(ssidname);
    Serial.println();
    Serial.print(F("RSSI         : "));
    Serial.println(rssi);
    Serial.print(F("Security Mode: "));
    Serial.println(sec);
    Serial.println();
  }
  Serial.println(F("================================================"));

  cc3000.stopSSIDscan();
}



void checkUDP()
{
  if ( udpServer.available())
  {
    sockaddr_in sender;
    memset(&sender, 0, sizeof(sender));

    char bufferUDP[200] = {0};
    int n = udpServer.readData(bufferUDP, 200, (sockaddr*)&sender);  // n contains # of bytes read into buffer

    if (n > 0)
    {
      if (strncmp(bufferUDP, "where are you arduino", 22) == 0)
      {
        Serial.println(F("Receive Broadcast"));
        delay(100);
        if (!cc3000.getIPAddress(&ipAddress, &netmask, &gateway, &dhcpserv, &dnsserv))
        {
          udpServer.writeData("Unable to retrieve the IP Address", 33, (sockaddr*)&sender);
        }
        else
        {
          delay(100);

          char ipstring[14] = {0x00};
          char buf[10] = {0x00};

          itoa((uint8_t)(ipAddress >> 24), buf, 10);
          strcat(ipstring, buf);
          strcat(ipstring, ".");
          buf[0] = 0;
          itoa((uint8_t)(ipAddress >> 16), buf, 10);
          strcat(ipstring, buf);
          strcat(ipstring, ".");
          buf[0] = 0;
          itoa((uint8_t)(ipAddress >> 8), buf, 10);
          strcat(ipstring, buf);
          strcat(ipstring, ".");
          buf[0] = 0;
          itoa((uint8_t)(ipAddress), buf, 10);
          strcat(ipstring, buf);
          buf[0] = 0;

          char resp[40] = {0x00};
          strncpy(resp, "IP=", 3);
          strncat(resp, ipstring, sizeof(ipstring));
          strncat(resp, ",", 1);
          strncat(resp, "Name=", 5);
          strncat(resp, AppareilId, sizeof(AppareilId));
          udpServer.writeData(resp, strlen(resp), (sockaddr*)&sender);
        }
      }
      else if (strncmp(bufferUDP, "start", 6) == 0)
      {
        Serial.println(F("Receive start signal"));
        int chk;
        int light;
        int gas;
        int fire;
        chk = DHT.read(DHT11_PIN);
        switch (chk) {
          case DHTLIB_OK:
            /*Serial.print("OK,\t");*/
            break;
          case DHTLIB_ERROR_CHECKSUM:
            Serial.print(F("Checksum error,\t"));
            break;
          case DHTLIB_ERROR_TIMEOUT:
            Serial.print(F("Time out error,\t"));
            break;
          default:
            Serial.print(F("Unknown error,\t"));
            break;
        }
        byte state = digitalRead(sensorPin);
        digitalWrite(indicator, state);
        light = analogRead(0);
        gas = analogRead(1);
        fire = analogRead(2);
        char clientString[200] = {0};
        sprintf(clientString, "%s%d,%s%d,%s%d,%s%d,%s%d,%s%d", "T:", DHT.temperature, "H:", DHT.humidity, "L:", light, "G:", gas, "F:", fire, "HS:", state, "\n");
        //Serial.println(F("Send out sensor data"));
        udpServer.writeData(clientString, strlen(clientString), (sockaddr*)&sender);
        //Serial.println(clientString);
      }
      else if (strncmp(bufferUDP, "reboot", 7) == 0)
      {
        Serial.println(F("Reveive Reboot signal"));
        cc3000.reboot();
        //here the code to connect to AP
        /* Attempt to connect to an access point */
        char *ssid = WLAN_SSID;             /* Max 32 chars */
        Serial.print(F("\nAttempting to connect to ")); Serial.println(ssid);

        /* NOTE: Secure connections are not available in 'Tiny' mode!
          By default connectToAP will retry indefinitely, however you can pass an
          optional maximum number of retries (greater than zero) as the fourth parameter.
        */
        if (!cc3000.connectToAP(WLAN_SSID, WLAN_PASS, WLAN_SECURITY)) {
          Serial.println(F("Failed!"));
          while (1);
        }

        while (udpServer.Reboot() == 0);
        Serial.println(F("Reboot successed"));
      }
    }
  }
}



