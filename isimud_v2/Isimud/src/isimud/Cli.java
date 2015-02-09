package isimud;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
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

    public final GpioController gpio = GpioFactory.getInstance();

    //GpioPinDigitalOutput satReset = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18,"satReset",PinState.LOW);
    public GpioPinDigitalOutput satOn = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17,"satOn",PinState.LOW);
    
 
    public Cli(String[] args) {

        this.args = args;

        options.addOption("s", "start", false, "start communication");
        options.addOption("k", "kill", false, "stop communication");
        options.addOption("c", "change-ip", true, "<ip-address>  change local ip number with following");
        options.addOption("p", "send-port", true, "<ip-address/port>  set port with bit value");
        options.addOption("g", "get-ports", false, "get analog and digital ports");
        options.addOption("w", "web", false, "start web server");
        options.addOption("h", "help", false, "show help.");

    }

    public void parse(){
     
        CommandLineParser parser = new BasicParser();

        CommandLine cmd;
        try {
      
            cmd = parser.parse(options, args);

            if (cmd.hasOption("s")){
        
                System.out.println("starting Modem...");
                satOn.high();
     
                Process p = Runtime.getRuntime().exec("pppd call Thuraya &");
    
            }else if (cmd.hasOption("k")){
    
                System.out.println("Modem has stopped.");
                Process p = Runtime.getRuntime().exec("killall pppd");
    
            }else if (cmd.hasOption("c")){
        
                System.out.println("IP changing with " + cmd.getOptionValue("c"));
        
            }else if (cmd.hasOption("p")){
    
                System.out.println("port sending...");
                //Gpio.portSending(cmd.getOptionValue("p"));
        
            }else if (cmd.hasOption("g")){
        
                //Gpio.getPorts();
                System.out.println("get ports.");
    
            }else if (cmd.hasOption("w")){
    
                System.out.println("start web interface");
        
     
                if(cmd.getOptionValue("w").equals("1")){
                    Process p = Runtime.getRuntime().exec("nohup python /root/isimud/web_interface/web/index.py > /root/web_interface.log 2>&1 </dev/null &");
                }else{
                    Process p = Runtime.getRuntime().exec("killall python");
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
        }
    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("Isimud", options);
        System.exit(0);
 }
 





 
 
}
