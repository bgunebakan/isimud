#include <stdio.h>
#include <stdlib.h>
#include <wiringPi.h>
#include <time.h>
#include <unistd.h>
#include <memory.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <sys/ioctl.h>
#include <netinet/in.h>
#include <net/if.h>
#include <arpa/inet.h>
#include <string.h> /* for strncpy */

#include "systemControls.h"

int gpioInit() {
  // Setup wiringPi
  wiringPiSetupGpio() ;

  //wiringPiSPISetup (1,1000000);

  pinMode(D0, INPUT);
  pinMode(D1, INPUT);
  pinMode(D2, INPUT);
  pinMode(D3, INPUT);
  pinMode(D4, INPUT);
  pinMode(SAT_ON, INPUT);

  pullUpDnControl(D0, PUD_DOWN);
  pullUpDnControl(D1, PUD_DOWN);
  pullUpDnControl(D2, PUD_DOWN);
  pullUpDnControl(D3, PUD_DOWN);
  pullUpDnControl(D4, PUD_DOWN);

  return 0;
}

char* getPorts(){

  char* returnValue = malloc(5+6+1); //define string space in memory

  sprintf(returnValue,"%d:%d:%d:%d:%d-0:0:0:0:0",digitalRead(D0),
                                         digitalRead(D1),
                                         digitalRead(D2),
                                         digitalRead(D3),
                                         digitalRead(D4));

  return returnValue;
}

int changeIP(char* ipadd){
  char command[100];

  char* file = "/etc/network/interfaces";

  //printf("%s",getIP());
                                      //find,replace_with,file
  sprintf(command,"sed -i s/%s/%s/g %s",getIP(),ipadd,file);

  puts(command);
  system(command);
  system("reboot");
  return 0;
}

char* getIP(){
  int fd;
  struct ifreq ifr;

  fd = socket(AF_INET, SOCK_DGRAM, 0);

  /* I want to get an IPv4 IP address */
  ifr.ifr_addr.sa_family = AF_INET;

  /* I want IP address attached to "eth0" */
  strncpy(ifr.ifr_name, "eth0", IFNAMSIZ-1);

  ioctl(fd, SIOCGIFADDR, &ifr);

  close(fd);

  return inet_ntoa(((struct sockaddr_in *)&ifr.ifr_addr)->sin_addr);

}