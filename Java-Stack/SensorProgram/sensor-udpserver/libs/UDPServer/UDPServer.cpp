#include "UDPServer.h"

UDPServer::UDPServer(uint16_t port) {
   _port = port;
   _socket = -1;
}

bool UDPServer::Reboot()
{
   if (_socket != -1)
   {
      int closeInt = closesocket(_socket);
      if (closeInt == -1)
      {
         Serial.println("Reboot() call failed");
         return false;
      }
      _socket = -1;
   }
   delay(400);
   begin();
}

bool UDPServer::begin() {
   //Serial.print("START of udpServer::Begin() _socket: "); Serial.println(_socket);
   // Open the socket if it isn't already open.
   if (_socket == -1) {
      // Create the UDP socket
      int soc = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
      if (soc < 0) {
         Serial.println("socket() call failed");
         return false;
      }

      sockaddr_in address;
      memset(&address, 0, sizeof(address));
      address.sin_family = AF_INET;
      address.sin_port = htons(_port);
      address.sin_addr.s_addr = 0;  // 0 => auto use own ip address
      socklen_t len = sizeof(address);
      if (bind(soc, (sockaddr*) &address, sizeof(address)) < 0) {
         Serial.println("bind() call failed");
         return false;
      }
      
      _socket = soc;
   }

   //Serial.print("END of udpServer::begin() _socket: "); Serial.println(_socket);

   return true;
}

bool UDPServer::available() {
   timeval timeout;
   timeout.tv_sec = 0;
   timeout.tv_usec = 200;
   fd_set reads;
   FD_ZERO(&reads);
   FD_SET(_socket, &reads);
   select(_socket + 1, &reads, NULL, NULL, &timeout);
   if (!FD_ISSET(_socket, &reads)) {
      // No data to read.
      //Serial.println("No data to read.");
      return false;
   }
   
   return true;
}

int UDPServer::readData(char *buffer, int bufferSize, sockaddr* sender) {
   // If there is data, then stores it into buffer &
   // returns the length of buffer. (-1 if none)
   socklen_t sendsize = 0;
   int n = recvfrom(_socket, buffer, bufferSize, 0, sender, &sendsize);
   if (n < 1)
   {
      // Error getting data.
      //Serial.println("Error getting data");
      return -1;
   }

   return n;
}

int UDPServer::writeData(char *buffer, int bufferSize, sockaddr* sender)
{
   int n = sendto(_socket, buffer, bufferSize, 0, sender, sizeof(*sender));
   if (n < 1)
   {
      // Error getting data.
      //Serial.print("Error sending data: ");
      //Serial.println(n);
      return -1;
   }
   
   return n;
}