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
    Gpio gpio = new Gpio();
    Tcp tcp = new Tcp();
    Serial serial = new Serial();
    
    //public final GpioController gpio = GpioFactory.getInstance();

    //GpioPinDigitalOutput satReset = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18,"satReset",PinState.LOW);
    //public GpioPinDigitalOutput satOn = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17,"satOn",PinState.LOW);
    
 
    public Cli(String[] args) {

        this.args = args;

        options.addOption("s", "start", false, "start communication");
        options.addOption("k", "kill", false, "stop communication");
        options.addOption("c", "change-ip", true, "<ip-address>  change local ip number with following");
        options.addOption("p", "send-port", true, "<ip-address>  send port with bit value");
        options.addOption("m", "modbus", true, "<1/0> change serial mode");
        options.addOption("g", "get-ports", false, "get analog and digital ports");
        options.addOption("w", "web", false, "start web server");
        
        options.addOption("T", "transmit-mode", true, "<1 Server/0 Client> transmitter mode Server or Client");
        options.addOption("S", "server-ip", true, "Server Address");
        options.addOption("P", "server-port", true, "Server port");
        
        options.addOption("h", "help", false, "show help.");

    }

    public void parse(){
     
        CommandLineParser parser = new BasicParser();

        CommandLine cmd;
        try {
      
            cmd = parser.parse(options, args);

            if (cmd.hasOption("s")){
        
                System.out.println("starting Modem...");
                gpio.initGpio();
                Process saton = Runtime.getRuntime().exec("echo '1' > /sys/class/gpio/gpio0/value");
                Process satreset = Runtime.getRuntime().exec("echo '0' > /sys/class/gpio/gpio2/value");
                Process satled = Runtime.getRuntime().exec("echo '1' > /sys/class/gpio/gpio15/value");
      //          satOn.high();
                
                Process p = Runtime.getRuntime().exec("pppd call Thuraya &");
                System.out.println(p);
    
            }else if (cmd.hasOption("k")){
    
                System.out.println("Modem has stopped.");
                Process p = Runtime.getRuntime().exec("killall pppd");
    
            }else if (cmd.hasOption("c")){
        
                System.out.println("IP changing with " + cmd.getOptionValue("c"));
                config.changeIp(cmd.getOptionValue("c"));
        
            }else if (cmd.hasOption("p")){
    
                System.out.println("port sending...");
                //Gpio.portSending(cmd.getOptionValue("p"));
                tcp.sendPost(cmd.getOptionValue("c"), gpio.getPorts());
        
            }else if (cmd.hasOption("g")){
        
                
                System.out.println(gpio.getPorts());
    
            }else if (cmd.hasOption("w")){
    
                System.out.println("start web interface");
        
     
                if(cmd.getOptionValue("w").equals("1")){
                    Process p = Runtime.getRuntime().exec("nohup python /root/isimud/web_interface/web/index.py > /root/web_interface.log 2>&1 </dev/null &");
                }else{
                    Process p = Runtime.getRuntime().exec("killall python");
                }
            }else if (cmd.hasOption("m")){
    
                System.out.println("changing modbus mode..");
        
     
                if(cmd.getOptionValue("m").equals("1")){
                    config.modbus_mode = true;
                    config.Update();
                }else{
                    config.modbus_mode = false;
                    config.Update();
                }
            }else if (cmd.hasOption("T")){
    
                String serverAdd;
                String serverPort;
                
                if(cmd.getOptionValue("T").equals("1")){
                    System.out.println("server mode...");
                    serverPort = cmd.getOptionValue("P");
                    
                    config.serial_port = serverPort;
                    config.Update();
                    serial.readSerial();
                    
                }else{
                    System.out.println("client mode...");
                    serverAdd = cmd.getOptionValue("S");
                    serverPort = cmd.getOptionValue("P");
                    
                    config.server_add = serverAdd;
                    config.serial_port = serverPort;
                    config.Update();
                    
                    serial.readSerial();
                    
                }
    
            }else if (cmd.hasOption("h")){
    
                help();
    
            }else {
    
                //log.log(Level.SEVERE, "MIssing v option");
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
