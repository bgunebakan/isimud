from flask import Flask,request,render_template,flash
from subprocess import call
import subprocess
import flask

from tornado.wsgi import WSGIContainer
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
import netifaces as ni
import os
import ConfigParser
import StringIO

satInterface = "ppp0"
localNetworkInterface = "wlan0"

config_file = '/home/gunebakan/isimud/Isimud/system.conf'
log_file = '/home/gunebakan/isimud/Isimud/system.log'
# "ip -f inet -o addr show ppp0|cut -d\  -f 7 | cut -d/ -f 1"

app = Flask(__name__)



@app.route("/", methods=['GET', 'POST'])
def index():

    if request.method == 'POST':
        return request.form['name']
    else:

        #interfaceList = ni.interfaces() #create interface list

        #if satInterface in interfaceList: #find interface in list
        #    ni.ifaddresses(satInterface) #get ip
        #    satip = ni.ifaddresses(satInterface)[2][0]['addr']
        #else:
        #    satip = 'no connection interface'

        #localip = ni.ifaddresses(localNetworkInterface)[2][0]['addr']


        #p1 = subprocess.Popen(['isimud', '-g'], stdout=subprocess.PIPE,
        #                                                    stderr=subprocess.PIPE)
        #portvalues, err = p1.communicate()
        #print err
        props = read_properties_file(config_file)

        # and if you deal with optional settings, use:
        sat_ip = props.get('sat_ip', None)
        local_ip = props.get('local_ip', None)
        serial_baud = props.get('serial_baud', None)
        port_values = 'd0=1;d1=0'

        logs = ""
        fo = open(log_file,"r")
        logs_raw = fo.read()


        for line in logs_raw.split('\n'):
            logs += flask.Markup.escape(line) + flask.Markup('<br />')


        return render_template('index.html',localip=local_ip,satipaddress=sat_ip,
                                            portvalues=port_values,serialbaud=serial_baud,
                                            logs=logs)


@app.route('/disconnect', methods=['GET', 'POST'])
def disconnect():

    args = "killall pppd &"
    os.system(args)

    return render_template('index.html')

@app.route('/connect', methods=['GET', 'POST'])
def connect():

    args = "pppd call Thuraya &"
    os.system(args)

    return render_template('index.html')


@app.route('/restart', methods=['GET', 'POST'])
def restart():
    p = subprocess.Popen(['sudo', 'reboot'], stdout=subprocess.PIPE,
                                       stderr=subprocess.PIPE)

    out, err = p.communicate()
    return "System going to restart now.Please refresh your page after device has started."

@app.route('/cutConnections', methods=['GET', 'POST'])
def cutConnections():

    args = "killall java &"
    os.system(args)

    return render_template('index.html')


@app.route('/serverConnect', methods=['GET', 'POST'])
def serverConnect():

    if request.method == 'POST':

	if request.form["workingmode"] == 'Client':
	    print "client"
	    serverIP = request.form["Serveripaddress"]
            serverPort = request.form["Serverport"]

            os.system("killall isimud.jar")

            args = "isimud -T 0 -S "+serverIP+" -P " +serverPort + " &"
            print args
            os.system(args)
	else:
	    print "server"
            os.system("killall isimud.jar")
            clientPort = request.form["Serverport"]

            args = "isimud -T 1 -P " + clientPort + " &"

            print args
            os.system(args)


    return render_template('index.html')

@app.route('/saveSettings', methods=['GET','POST'])
def saveSettings():

    if request.method == 'POST':
        Localip = request.form['Localipaddress']
        #print Localip
        args = "isimud -c " + Localip
        os.system(args)

    return render_template('index.html')

@app.route('/saveSerial', methods=['GET','POST'])
def saveSerial():

    if request.method == 'POST':
        serialBaud = request.form['serialbaud']
        #print Localip
        args = "isimud -b " + serialBaud
        os.system(args)

    return render_template('index.html')

@app.route('/modbusMode', methods=['GET','POST'])
def modbusMode():

    if request.method == 'POST':
        ModbusMode = request.form['modbus']
        #print Localip
        if ModbusMode == 'On':
            args = "isimud -m 1"
	    print args
            os.system(args)
        else:
            args = "isimud -m 0"
	    print args
            os.system(args)

    return render_template('index.html')

@app.route('/sendPortValues', methods=['GET', 'POST'])
def sendPortValues():

    if request.method == 'POST':

        ServerIP = request.form["Serveripaddress"]

        args = "isimud -p " + ServerIP + " &"


        print args
        os.system(args)

    return render_template('index.html')


## read config file

def read_properties_file(file_path):
    with open(file_path) as f:
        config = StringIO.StringIO()
        config.write('[dummy_section]\n')
        config.write(f.read())
        config.seek(0, os.SEEK_SET)

        cp = ConfigParser.SafeConfigParser()
        cp.readfp(config)

        return dict(cp.items('dummy_section'))

if __name__ == "__main__":
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()

#    app.run(host="localhost",debug=True)
