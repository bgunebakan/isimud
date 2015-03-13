/*
 * Copyright (C) 2015 Bilal Tonga
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package isimud;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {

    private static final Logger log = Logger.getLogger(Cli.class.getName());
    private String[] args = null;
    private Options options = new Options();
    
    Config config = new Config();
 
    public Cli(String[] args) {

        this.args = args;

        options.addOption("s", "start", false, "start communication");
        options.addOption("k", "kill", false, "stop communication");
        options.addOption("c", "change-ip", true, "<ip-address>  change local ip number with following");
        options.addOption("p", "send-port", false, "<-S ip-address>  send port with bit value");
        options.addOption("m", "modbus", true, "<1/0> change serial mode");
        options.addOption("g", "get-ports", false, "get analog and digital ports");
        options.addOption("w", "web", true, "start(1)/stop(0) web server");
        
        options.addOption("b", "serial-baud", true, "<baud> Baudrate of serial");
        
        options.addOption("T", "transmit-mode", true, "<1 Server/0 Client> transmitter mode Server or Client");
        options.addOption("S", "server-ip", true, "Server Address");
        options.addOption("P", "server-port", true, "Server port");
        
        options.addOption("O", "client-ip", true, "<ip-address> client ip address");
        
        options.addOption("h", "help", false, "show help.");

    }

    public void parse(){
     
        CommandLineParser parser = new BasicParser();

        CommandLine cmd;
        try {
      
            cmd = parser.parse(options, args);

            if (cmd.hasOption("s")){
        
                System.out.println("starting Modem...");
                config.writeLog("Sattellite connecting...");
                Process p = Runtime.getRuntime().exec("sudo pppd call Thuraya");
               
    
            }else if (cmd.hasOption("k")){
    
                System.out.println("Modem has stopped.");
                config.writeLog("Sattellite connection stopped.");
                Process p = Runtime.getRuntime().exec("killall pppd");
    
            }else if (cmd.hasOption("c")){
                if(cmd.hasOption("O")){
                    System.out.println("Local IP changing with " + cmd.getOptionValue("c") +" old:"+config.local_ip);
                    System.out.println("Client IP changing with " + cmd.getOptionValue("O") +" old:"+config.client_ip);
                    config.writeLog("IP changing with " + cmd.getOptionValue("c") +"old:"+config.local_ip);
                    config.writeLog("IP changing with " + cmd.getOptionValue("O") +"old:"+config.client_ip);
                    config.changeIp(cmd.getOptionValue("c"),cmd.getOptionValue("O"));
            
                }else{
                    System.out.println("use -O option with old ip");
                }
                
            }else if (cmd.hasOption("p")){
                
                String address =  cmd.getOptionValue("S");
                
                System.out.println("port sending...");
                config.writeLog("Port values sending to " + address);
                config.postserver_add = address;
                config.Update();
        
                (new sendPostThread("http://" + address)).start();
                
                
            }else if (cmd.hasOption("g")){
                Gpio gpio = new Gpio();
                gpio.init();
                System.out.println(gpio.getPorts());
    
            }else if (cmd.hasOption("w")){
    
                if(cmd.getOptionValue("w").equals("1")){
                    System.out.println("start web interface");
                    config.cleanLog();
                    Process p = Runtime.getRuntime().exec("nohup python /opt/isimud/web_interface/web/index.py > /opt/isimud/log/web_interface.log 2>&1 </dev/null &");
                }else{
                    System.out.println("stop web interface");
                    Process p = Runtime.getRuntime().exec("killall python");
                }
            }else if (cmd.hasOption("m")){
    
                System.out.println("changing modbus mode..");
                
                if(cmd.getOptionValue("m").equals("1")){
                    config.writeLog("Modbus mode is on" );
                    config.modbus_mode = true;
                    config.Update();
                }else{
                    config.writeLog("Modbus mode is off" );
                    config.modbus_mode = false;
                    config.Update();
                }
            }else if (cmd.hasOption("T")){
    
                String serverAdd;
                int serverPort;
                
                if(cmd.getOptionValue("T").equals("1")){
                    System.out.println("server mode...");
                    serverPort = Integer.valueOf(cmd.getOptionValue("P"));
                    
                    config.writeLog("Server mode @ port: "+ serverPort);
                    config.server_port = serverPort;
                    config.server_mode = true;
                    config.working_mode = "Server";
                    config.Update();
                    
                    tcpThread clientThread = new tcpThread();
                    clientThread.start();
                    
                }else{
                    System.out.println("client mode...");
                    serverAdd = cmd.getOptionValue("S");
                    serverPort = Integer.valueOf(cmd.getOptionValue("P"));
                    
                    config.writeLog("Client mode @ add: "+ serverAdd +":" +serverPort);
                    
                    config.server_add = serverAdd;
                    config.server_port = serverPort;
                    config.server_mode = false;
                    config.working_mode = "Client";
                    config.Update();
                    
                    
                    tcpThread clientThread = new tcpThread();
                    clientThread.start();
                    
                    
                }
    
            }else if (cmd.hasOption("b")){
        
                config.serial_baud = Integer.parseInt(cmd.getOptionValue("b"));
                System.out.println("Serial baud changed with " + config.serial_baud);
                config.writeLog("Serial baud changed with " + config.serial_baud);
                
                config.Update();
    
            }else if (cmd.hasOption("h")){
    
                help();
    
            }else {
    
                log.log(Level.SEVERE, "MIssing v option");
                help();
    
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse comand line properties", e);
            help();
        }  catch (IOException ex) {
             
            Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("Isimud", options);
        System.exit(0);
 }
 





 
 
}
