
echo "update to toolchain"
sudo apt-get update
sudo apt-get upgrade

echo "install required packages"
sudo apt-get install ppp htop emacs python python-dev python-pip easy-install minicom

echo "install python packages"
pip install flask
pip install jprops
pip install tornado
pip install netifaces
