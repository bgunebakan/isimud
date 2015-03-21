BUILD_DIR='/opt/isimud'
SOURCE_DIR='/root/isimud'


#sh $SOURCE_DIR/install.sh

#delete build directory if exist
rm -r $BUILD_DIR
mkdir $BUILD_DIR

#copy source files
cp -r $SOURCE_DIR/web_interface $BUILD_DIR
cp $SOURCE_DIR/Isimud/dist/Isimud.jar $BUILD_DIR
cp -r $SOURCE_DIR/Isimud/dist/lib $BUILD_DIR
cp $SOURCE_DIR/Isimud/system.conf $BUILD_DIR
cp $SOURCE_DIR/Isimud/command.sh $BUILD_DIR
cp $SOURCE_DIR/Isimud/port_command.sh $BUILD_DIR

cp -r $SOURCE_DIR/log $BUILD_DIR

#copy binary files
cp -r $SOURCE_DIR/config/bin /usr/local/bin

#copy config files
cp $SOURCE_DIR/config/etc/dnsmasq.conf /etc/
cp -r $SOURCE_DIR/config/etc/ppp /etc/
cp $SOURCE_DIR/config/etc/network/interfaces /etc/network
cp $SOURCE_DIR/config/etc/userdefined.rules /etc/
cp -r $SOURCE_DIR/config/etc/chatscripts /etc/

