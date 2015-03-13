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

#satInterface = "ppp0"
#localNetworkInterface = "wlan0"

config_file = '/opt/isimud/system.conf'
log_file = '/opt/isimud/log/system.log'
command_file = '/opt/isimud/command.sh'
portcommand_file = '/opt/isimud/port_command.sh'

# "ip -f inet -o addr show ppp0|cut -d\  -f 7 | cut -d/ -f 1"
logs = ""
sat_ip = ""
local_ip = ""
client_ip = ""
serial_baud = ""
port_values = ""

server_add = 0
server_port = 0
postserver_add = ""

modbus_mode = ""
working_mode = ""

app = Flask(__name__)



@app.route("/", methods=['GET', 'POST'])
def index():

    if request.method == 'POST':
        return request.form['name']
    else:
        readData()
        return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                               portvalues=port_values,serial_baud=serial_baud,
                               logs=logs,server_add=server_add,server_port=server_port,
                               postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)


@app.route('/disconnect', methods=['GET', 'POST'])
def disconnect():

    args = "killall pppd &"
    print args
    os.system(args)

    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/connect', methods=['GET', 'POST'])
def connect():
    
    args = "pppd call Thuraya &"
    print args
    os.system(args)

    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/restart', methods=['GET', 'POST'])
def restart():
    
    p = subprocess.Popen(['sudo', 'reboot'], stdout=subprocess.PIPE,
                         stderr=subprocess.PIPE)

    out, err = p.communicate()

    return "System going to restart now.Please refresh your page after device has started."

@app.route('/cutConnections', methods=['GET', 'POST'])
def cutConnections():

    args = "killall java"
    print args
    os.system(args)

    fo = open(command_file,"w")
    fo.write('')
    fo.close()

    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/serverConnect', methods=['GET', 'POST'])
def serverConnect():
    global server_add
    global server_port

    if request.form["workingmode"] == 'Client':
        print "client"
        server_add = request.form["server_add"]
        server_port = request.form["server_port"]
        #os.system("killall java")

        args = "isimud -T 0 -S " + server_add + " -P " + server_port + " &"
        #print args
        #os.spawn(os.P_NOWAIT,args)

        fo = open(command_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()
        
        args2 = ['isimud', '-T','0','-S',server_add,'-P',server_port]
        print args2
	subprocess.Popen(args2)

    else:
        print "server"
        #os.system("killall java")
        server_port = request.form["server_port"]
        
        args = "isimud -T 1 -P " + server_port + " &"
        #print args
        #os.system(args)
        
        fo = open(command_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()
        
        args2 = ['isimud', '-T','1','-P',server_port]
        print args2
        subprocess.Popen(args2)

    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/saveSettings', methods=['GET','POST'])
def saveSettings():

    global local_ip
    global client_ip

    if request.method == 'POST':
        local_ip = request.form['local_ip']
        client_ip = request.form['client_ip']
        
        #print Localip
        #args = "isimud -c " + local_ip + " -O " + client_ip
        #print args
        #os.system(args)
        
        args2 = ['isimud', '-c',local_ip,'-O',client_ip]
        print args2
        subprocess.Popen(args2)
        
    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/saveSerial', methods=['GET','POST'])
def saveSerial():

    global serial_baud

    if request.method == 'POST':
        serial_baud = request.form['serial_baud']
        #print Localip
        #args = "isimud -b " + serial_baud
        #print args
        #os.system(args)

        args2 = ['isimud', '-b',serial_baud]
        print args2
        subprocess.Popen(args2)

    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/modbusMode', methods=['GET','POST'])
def modbusMode():

    global modbus_mode

    if request.method == 'POST':
        modbus_mode = request.form['modbus']
        #print Localip
        if modbus_mode == 'On':
            #args = "isimud -m 1"
            args2 = ['isimud', '-m','1']
        
        else:
            #args = "isimud -m 0"
            args2 = ['isimud', '-m','0']

            #print args
        #os.system(args)

        print args2
        subprocess.Popen(args2)
        
    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/sendPortValues', methods=['GET', 'POST'])
def sendPortValues():

    global postserver_add

    if request.method == 'POST':

        postserver_add = request.form["postserver_add"]

        args = "isimud -p -S " + postserver_add + " &"
        #print args
        #os.system(args)
        
        fo = open(portcommand_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()

        args2 = ['isimud', '-p','-S',postserver_add]
        print args2
        subprocess.Popen(args2)


    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

#read main page variables
def readData():
    global sat_ip
    global local_ip
    global client_ip
    global serial_baud
    global port_values

    global server_add
    global server_port
    global postserver_add
    global logs

    global modbus_mode
    global working_mode
    
    logs_raw = ""
    logs = ""

    props = read_properties_file(config_file)

    # and if you deal with optional settings, use:
    sat_ip = props.get('sat_ip', None)
    local_ip = props.get('local_ip', None)
    client_ip = props.get('client_ip', None)
    serial_baud = props.get('serial_baud', None)

    server_add = props.get('server_add', None)
    server_port = props.get('server_port', None)
    postserver_add = props.get('postserver_add', None)
        
    working_mode = props.get('working_mode', None)
    modbus_mode = props.get('modbus_mode', None)
    
    #args = 'echo 0000' #"isimud -g"
    #os.system(args)

    port_values = '0000'#props.get('port_values', None)


    fo = open(log_file,"r")
    logs_raw = fo.read()


    for line in logs_raw.split('\n'):
        logs += flask.Markup.escape(line) + flask.Markup('<br />')

## read config file

def read_properties_file(file_path):
    with open(file_path) as f:
        config = StringIO.StringIO()
        config.write('[dummy_section]\n')
        config.write(f.read())
        config.seek(0, os.SEEK_SET)

        cp = ConfigParser.SafeConfigParser()
        cp.readfp(config)
        
        f.close()
        return dict(cp.items('dummy_section'))

if __name__ == "__main__":
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()

#    app.run(host="localhost",debug=True)
