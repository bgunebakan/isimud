cd ..
scp -r isimud root@192.168.128.100:/root/
echo "/root/isimud/build.sh" | ssh root@192.168.128.100 /bin/bash 
