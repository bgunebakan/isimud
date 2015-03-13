#!/bin/sh
#
#	deploy script for isimud
#
#
#

if [ "all" == $1 ]
then
	echo "build all"
	cd ..
	scp -r isimud root@192.168.128.100:/root/
	echo "/root/isimud/build.sh" | ssh root@192.168.128.100 /bin/bash

elif [ "web" == $1 ]
then
	echo "web"
	cd ..
	scp -r isimud/web_interface/web root@192.168.128.100:/root/isimud/web_interface
	echo "/root/isimud/build.sh" | ssh root@192.168.128.100 /bin/bash

elif [ "java" == $1 ]
then
        echo "java"
        cd ..
        scp -r isimud/Isimud root@192.168.128.100:/root/isimud/
        echo "/root/isimud/build.sh" | ssh root@192.168.128.100 /bin/bash

elif [ "two" == $1 ]
then
	echo "web and java"
        cd ..
        scp -r isimud/web_interface/web root@192.168.128.100:/root/isimud/web_interface
	scp -r isimud/Isimud root@192.168.128.100:/root/isimud/
        echo "/root/isimud/build.sh" | ssh root@192.168.128.100 /bin/bash

fi

