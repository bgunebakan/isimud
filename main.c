#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include "systemControls.h"
#include "main.h"

/**********************************************************
* Developer: Bilal Tonga
*
* Control Software for Thuraya SM-2500 module
*
* January,2015
*
**********************************************************/

int main (int argc, char **argv){

  int c;
  gpioInit(); 

  while (1)
    {
      static struct option long_options[] =
        {
          {"start",     no_argument,       0, 's'},
          {"kill",      no_argument,       0, 'k'},
          {"change-ip", required_argument, 0, 'c'},
          {"send-port",  required_argument, 0, 'p'},
          {"get-ports", no_argument,       0, 'g'},
          {"web",       no_argument,       0, 'w'},
          {"help",      no_argument,       0, 'h'},
        };
      /* getopt_long stores the option index here. */
      int option_index = 0;

      c = getopt_long (argc, argv, "skhgw:c:p:",
                       long_options, &option_index);

      /* Detect the end of the options. */
      if (c == -1)
        break;

      switch (c)
        {
        case 0:
          /* If this option set a flag, do nothing else now. */
          if (long_options[option_index].flag != 0)
            break;
          printf ("option %s", long_options[option_index].name);
          if (optarg)
            printf (" with arg %s", optarg);
          printf ("\n");
          break;

        case 's': //start thuraya
          puts ("Sat module starting...");
	  digitalWrite(SAT_ON,1);
	  sleep(2000);
          system("pppd call Thuraya &");
          break;

        case 'k':// kill thuraya
          puts ("Sat module stopped!");
          system("killall pppd");
          break;

        case 'g':
          //puts ("getting digital and analog port values.");
          printf("%s\n",getPorts());

          break;

        case 'p': // send port data to server
          printf ("port data sending to: %s\n", optarg);
	  //char* portvalue;
	  char command[100];

	  while(1){
	    char *portvalue = getPorts();
	
	    sprintf(command,"echo %s > /dev/tcp/%s",portvalue,optarg);
	    system(command);
	    sleep(2000);
	  }
          break;

        case 'c': // change local ip
          printf ("Local IP changing with:%s\n", optarg);

          if(changeIP(optarg) == 0)
            puts("ip address has changed.");
          else
            puts("ip address cannot changed!");
          break;
        case 'w': // start web
        if(strcmp("1",optarg) == 0)
          system("nohup python /root/isimud/web_interface/web/index.py > /root/web_interface.log 2>&1 </dev/null &");
        else
          system("killall python");
          break;

        case 'h': // help
          printHelp();
          break;

        case '?':
          /* getopt_long already printed an error message. */
          break;

        default:
          abort ();
        }
    }


  /* Print any remaining command line arguments (not options). */
  if (optind < argc)
    {
      printf ("non-option ARGV-elements: ");
      while (optind < argc)
        printf ("%s ", argv[optind++]);
      putchar ('\n');
      printHelp();
    }

  exit (0);
}

void printHelp(){
  puts("Using:\nmodemcontrol [-argument] [value]");
  puts("--kill   stop communication");
  puts("--start  start communication");
  puts("--change-ip <ip-address>  change local ip number with following");
  puts("--send-port <ip-address/port>  set port with bit value");
  puts("--get-ports   get analog and digital ports");
  puts("--help   show help");
}
