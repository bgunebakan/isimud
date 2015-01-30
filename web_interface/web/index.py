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

networkInterface = "ppp0"
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
        serverIP = request.form["Serveripaddress"]
        serverPort = request.form["Serverport"]

        os.system("killall socat")

        args = "socat -d -d pty,link=/dev/ttyUSB0,raw,echo=0,waitslave tcp:" + serverIP + ":"+serverPort + "; &"

        print args
        os.system(args)

    return render_template('index.html')

@app.route('/startServer', methods=['GET', 'POST'])
def startServer():

    if request.method == 'POST':
        print "server"
        os.system("killall socat")
        clientPort = request.form["Clientport"]

        args = "socat tcp-l:" + clientPort + ",reuseaddr,fork file:/dev/ttyUSB0,nonblock,waitlock=/var/run/ttyUSB0.lock &"

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

@app.route('/sendPortValues', methods=['GET', 'POST'])
def sendPortValues():

    if request.method == 'POST':

        ServerIP = request.form["Serveripaddress"]
        ServerPort = request.form["Serverport"]


        args = "isimud -p " + ServerIP + "/" + ServerPort+ " &"


        print args
        os.system(args)

    return render_template('index.html')

@app.route('/updateSoftware', methods=['GET', 'POST'])
def updateSoftware():

    if request.method == 'POST':
        #clone from git repo and update software
        os.system("cd /root/isimud")
        os.system("git pull")
        os.system("./build.sh")
        os.system("sudo reboot")

    return render_template('index.html')


if __name__ == "__main__":
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()

#    app.run(host="localhost",debug=True)
