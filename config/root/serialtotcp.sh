#!/bin/bash
# Serial to TCP IP port sender
#
#

IP="ufakisler.net"
PORT="2345"
SERIAL="/dev/ttyUSB0"


while true
do
  echo "--------------------------"
  echo "data sending"
  echo "from" $SERIAL
  echo "to" $IP":"$PORT
  echo ""
  echo "Press [CTRL+C] to stop.."
  echo "--------------------------"
  
 # cat $SERIAL > /dev/tcp/$IP/$PORT < $SERIAL
  nc -l $PORT > $SERIAL < $SERIAL
  
  sleep 1
done
