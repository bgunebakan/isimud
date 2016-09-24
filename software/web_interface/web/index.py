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

	#"s", "start", false, "start communication");
        #"k", "kill", false, "stop communication");
        #"c", "change-ip", true, "<ip-address>  change local ip number with following");
        #"p", "send-port", true, "<ip-address>  send port with bit value");
        #"m", "modbus", true, "<1/0> change serial mode");
        #"g", "get-ports", false, "get analog and digital ports");
        #"w", "web", false, "start web server");
        #"T", "transmit-mode", true, "<1 Server/0 Client> transmitter mode Server or Client");
        #"S", "server-ip", true, "Server Address");
        #"P", "server-port", true, "Server port");
        #"h", "help", false, "show help.");


@app.route("/", methods=['GET', 'POST'])
def index():

    if request.method == 'POST':
        return request.form['name']
    else:

        ni.ifaddresses(networkInterface)
        satip = ni.ifaddresses(networkInterface)[2][0]['addr']
        localip = ni.ifaddresses(localNetworkInterface)[2][0]['addr']
	#get digital port status
        p1 = subprocess.Popen(['isimud', '-g'], stdout=subprocess.PIPE,
                                                            stderr=subprocess.PIPE)
        portvalues, err = p1.communicate()

        return render_template('index.html',localip=localip,satipaddress=satip,portvalues=portvalues)


@app.route('/disconnect', methods=['GET', 'POST'])
def disconnect():

    return format( call(["ifdown", "ppp0"]) ) #return format( call(["isimud", "-k"]) )


@app.route('/connect', methods=['GET', 'POST'])
def connect():

    
    #p = subprocess.Popen(['isimud', '-s'], stdout=subprocess.PIPE,
    #                                   stderr=subprocess.PIPE)

    #out, err = p.communicate()
    subprocess_cmd('echo '1' > /sys/class/gpio/gpio0/value; echo '0' > /sys/class/gpio/gpio2/value; echo '1' > /sys/class/gpio/gpio15/value; ifup ppp0')
	
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



def subprocess_cmd(command):
    process = subprocess.Popen(command,stdout=subprocess.PIPE, shell=True)
    proc_stdout = process.communicate()[0].strip()
    print proc_stdout

def setIpAddr(iface, ip):
    bin_ip = socket.inet_aton(ip)
    ifreq = struct.pack('16sH2s4s8s', iface, socket.AF_INET, '\x00'*2, bin_ip, '\x00'*8)
    fcntl.ioctl(sock, SIOCSIFADDR, ifreq)


#    app.run(host="localhost",debug=True)
