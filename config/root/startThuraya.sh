#!/bin/bash
# Start Thuraya module
#
#

THURAYA_ON="/sys/class/gpio/gpio17/value"
THURAYA_RESET="/sys/class/gpio/gpio27/value"

echo "17" > /sys/class/gpio/export
echo "out" > /sys/class/gpio/gpio17/direction

echo "27" > /sys/class/gpio/export
echo "out" > /sys/class/gpio/gpio27/direction

echo "Thuraya module is starting..."

echo "1" > $THURAYA_ON
sleep 2
echo "1" > $THURAYA_RESET
#sleep 1
#echo "0" > $THURAYA_RESET
#echo "1" > $THURAYA_ON
