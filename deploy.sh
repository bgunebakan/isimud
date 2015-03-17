#!/bin/sh
#
#	deploy script for isimud
#
#
#

IP=$2

echo "deploy to $IP"
if [ "all" == $1 ]
then
	echo "build all"
	cd ..
	scp -r isimud root@$IP:/root/
	echo "/root/isimud/build.sh" | ssh root@$IP /bin/bash

elif [ "web" == $1 ]
then
	echo "web"
	cd ..
	scp -r isimud/web_interface/web root@$IP:/root/isimud/web_interface
	echo "/root/isimud/build.sh" | ssh root@$IP /bin/bash

elif [ "java" == $1 ]
then
        echo "java"
        cd ..
        scp -r isimud/Isimud root@$IP:/root/isimud/
        echo "/root/isimud/build.sh" | ssh root@$IP /bin/bash

elif [ "two" == $1 ]
then
	echo "web and java"
        cd ..
        scp -r isimud/web_interface/web root@$IP:/root/isimud/web_interface
	scp -r isimud/Isimud root@$IP:/root/isimud/
        echo "/root/isimud/build.sh" | ssh root@$IP /bin/bash
elif [ "quick" == $1 ]
then
    	echo "quick"
        cd ..
        scp -r isimud/web_interface/web/index.py root@$IP:/opt/isimud/web_interface/web
        scp -r isimud/Isimud/dist/Isimud.jar root@$IP:/opt/isimud/
        #echo "/root/isimud/build.sh" | ssh root@$IP /bin/bash
fi

