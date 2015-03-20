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
import jprops
import collections

config_file = '/opt/isimud/system.conf'
log_file = '/opt/isimud/log/system.log'
command_file = '/opt/isimud/command.sh'
portcommand_file = '/opt/isimud/port_command.sh'

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
serial_port = ""

app = Flask(__name__)



@app.route("/", methods=['GET', 'POST'])
def index():

    if request.method == 'POST':
        return ""
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

    sat_ip = 'none'
    updateConfig()
    
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

    writeLog('Connecting to Sattellite...')        
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
    
    fo = open(portcommand_file,"w")
    fo.write('')
    fo.close()
    writeLog('All connections has closed.')
    
    #readData()
    return render_template('index.html',local_ip=local_ip,client_ip=client_ip,sat_ip=sat_ip,
                           portvalues=port_values,serial_baud=serial_baud,
                           logs=logs,server_add=server_add,server_port=server_port,
                           postserver_add=postserver_add,working_mode=working_mode,modbus_mode=modbus_mode)

@app.route('/serverConnect', methods=['GET', 'POST'])
def serverConnect():
    global server_add
    global server_port
    global  working_mode

    if request.form["workingmode"] == 'Client':
        working_mode = "Client" 
        print working_mode
        
	server_add = request.form["server_add"]
        server_port = request.form["server_port"]
      
        args = "isimud -T 0 -S " + server_add + " -P " + server_port + " &"
        
        fo = open(command_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()

	writeLog('Client connect to ' +server_add+':'+server_port)        
    else:
        working_mode = "Server" 
        print working_mode
        
        server_port = request.form["server_port"]
        
        args = "isimud -T 1 -P " + server_port + " &"
        
        fo = open(command_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()
        writeLog('TCP Server on port: ' + server_port)

    updateConfig()
        
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
        
        args2 = ['isimud', '-c',local_ip,'-O',client_ip]
        print args2
        subprocess.Popen(args2)
        
        writeLog('IP addresses changed with' + local_ip + ' - ' + client_ip)


    #updateConfig()
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
        writeLog('Serial baud changed with ' + serial_baud)

    updateConfig()
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

	writeLog('Modbus mode is now: ' + modbus_mode)

        if modbus_mode == 'On':
	    modbus_mode = 'true'
        else:
	    modbus_mode = 'false'

    updateConfig()
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
           
        fo = open(portcommand_file,"w")
        fo.write('\n')
        fo.write(args)
        fo.write('\nsleep 10 &&\n')
        fo.close()

	writeLog('Port values will be send to ' + postserver_add)


    updateConfig()
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

    global serial_port
    
    logs = ""
    logs_raw = ""

    with open(config_file) as fp:
        config = jprops.load_properties(fp, collections.OrderedDict)

    # and if you deal with optional settings, use:
    sat_ip = config['sat_ip']
    local_ip = config['local_ip']
    client_ip = config['client_ip']
    serial_baud = config['serial_baud']

    server_add = config['server_add']
    server_port = config['server_port']
    postserver_add = config['postserver_add']
        
    working_mode = config['working_mode']
    modbus_mode = config['modbus_mode']
    serial_port = config['serial_port']
    
    #args = 'echo 0000' #"isimud -g"
    #os.system(args)

    port_values = '0000' #props.get('port_values', None)

    fo = open(log_file,"r")
    logs_raw = fo.read()

    for line in logs_raw.split('\n'):
        logs += flask.Markup.escape(line) + flask.Markup('<br />')


def updateConfig():
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
    global serial_port
    
    with open(config_file,'w') as fp:
        jprops.write_property(fp, 'sat_ip', sat_ip)
        jprops.write_property(fp, 'local_ip', local_ip)
        jprops.write_property(fp, 'client_ip',client_ip )
        jprops.write_property(fp, 'serial_baud',serial_baud )
        jprops.write_property(fp, 'server_add',server_add )
        jprops.write_property(fp, 'server_port',server_port )
        jprops.write_property(fp, 'postserver_add',postserver_add )
        jprops.write_property(fp, 'working_mode',working_mode )
        jprops.write_property(fp, 'modbus_mode',modbus_mode )
        jprops.write_property(fp, 'port_values',port_values )
        jprops.write_property(fp, 'serial_port',serial_port )
        
def writeLog(log):
    global logs
 
    logs = ""
    logs_raw = ""	
    fo = open(log_file,"a")
    fo.write('- ' + log + '\n')
    fo.close()

    fo = open(log_file,"r")
    logs_raw = fo.read()

    for line in logs_raw.split('\n'):
        logs += flask.Markup.escape(line) + flask.Markup('<br />')

	        
        
if __name__ == "__main__":
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()

#    app.run(host="localhost",debug=True)
