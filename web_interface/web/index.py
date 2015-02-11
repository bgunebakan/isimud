from flask import Flask,request,render_template,flash
from subprocess import call
import subprocess

from tornado.wsgi import WSGIContainer
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
import netifaces as ni
import os
#from yourapplication import app

#localip = "192.168.128.100"

networkInterface = "lo"
localNetworkInterface = "eth0"
#satipaddress = "85.115.12.57"
# "ip -f inet -o addr show ppp0|cut -d\  -f 7 | cut -d/ -f 1"


app = Flask(__name__)



@app.route("/", methods=['GET', 'POST'])
def index():

    if request.method == 'POST':
        return request.form['name']
    else:

        ni.ifaddresses(networkInterface)
        satip = ni.ifaddresses(networkInterface)[2][0]['addr']
        localip = ni.ifaddresses(localNetworkInterface)[2][0]['addr']

        p1 = subprocess.Popen(['isimud', '-g'], stdout=subprocess.PIPE,
                                                            stderr=subprocess.PIPE)
        portvalues, err = p1.communicate()

        return render_template('index.html',localip=localip,satipaddress=satip,portvalues=portvalues)


@app.route('/disconnect', methods=['GET', 'POST'])
def disconnect():

    return format( call(["isimud", "-k"]) )


@app.route('/connect', methods=['GET', 'POST'])
def connect():

    p = subprocess.Popen(['isimud', '-s'], stdout=subprocess.PIPE,
                                       stderr=subprocess.PIPE)

    out, err = p.communicate()
    return out

@app.route('/restart', methods=['GET', 'POST'])
def restart():
    p = subprocess.Popen(['sudo', 'reboot'], stdout=subprocess.PIPE,
                                       stderr=subprocess.PIPE)

    out, err = p.communicate()
    return "System going to restart now.Please refresh your page after device has started."


@app.route('/serverConnect', methods=['GET', 'POST'])
def serverConnect():

    if request.method == 'POST':
            
	if request.form["workingmode"] == 'Client':
	    print "client"
	    serverIP = request.form["Serveripaddress"]
            serverPort = request.form["Serverport"]

            os.system("killall isimud.jar")

            args = "isimud.jar -T 0 -S "+serverIP+" -P " +serverPort + " &"
            print args
            os.system(args)
	else:
	    print "server"
            os.system("killall isimud.jar")
            clientPort = request.form["Serverport"]

            args = "isimud.jar -T 1 -P " + clientPort + " &"

            print args
            os.system(args)


    return render_template('index.html')

@app.route('/saveSettings', methods=['GET','POST'])
def saveSettings():

    if request.method == 'POST':
        Localip = request.form['Localipaddress']
        #print Localip
        args = "isimud.jar -c " + Localip
        os.system(args)

    return render_template('index.html')

@app.route('/modbusMode', methods=['GET','POST'])
def modbusMode():

    if request.method == 'POST':
        ModbusMode = request.form['modbus']
        #print Localip
        if ModbusMode == 'On':
            args = "isimud.jar -m 1"
	    print args
            os.system(args)
        else:
            args = "isimud.jar -m 0"
	    print args
            os.system(args)

    return render_template('index.html')

@app.route('/sendPortValues', methods=['GET', 'POST'])
def sendPortValues():

    if request.method == 'POST':

        ServerIP = request.form["Serveripaddress"]

        args = "isimud.jar -p " + ServerIP + " &"


        print args
        os.system(args)

    return render_template('index.html')

if __name__ == "__main__":
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()

#    app.run(host="localhost",debug=True)
