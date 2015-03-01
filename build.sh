BUILD_DIR='/opt/isimud'
SOURCE_DIR='/root/isimud'

#delete build directory if exist
rm -r $BUILD_DIR
mkdir $BUILD_DIR

#copy source files
cp -r $SOURCE_DIR/web_interface $BUILD_DIR
cp $SOURCE_DIR/Isimud/dist/Isimud.jar $BUILD_DIR
cp -r $SOURCE_DIR/Isimud/dist/lib $BUILD_DIR
cp $SOURCE_DIR/Isimud/system.conf $BUILD_DIR
cp -r $SOURCE_DIR/log $BUILD_DIR

#copy binary files
cp -r $SOURCE_DIR/config/bin /usr/local/bin

