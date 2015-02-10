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

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gunebakan
 */
public class Gpio {
    
    public final GpioController gpio = GpioFactory.getInstance();

    //GpioPinDigitalOutput satReset = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18,"satReset",PinState.LOW);
    public GpioPinDigitalOutput satOn = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17,"satOn",PinState.LOW);
    
    GpioPinDigitalInput D0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,"D0",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,"D1",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,"D2",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,"D3",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06,"D4",PinPullResistance.PULL_DOWN);

    public void portSending(String optionValue) {
        
    }

    public void getPorts() {
       int stateD0 = 0;
       int stateD1 = 0;
       int stateD2 = 0;
       int stateD3 = 0;
       int stateD4 = 0;
       
       if(D0.isHigh()) stateD0 = 1;
       if(D1.isHigh()) stateD1 = 1;
       if(D2.isHigh()) stateD2 = 1;
       if(D3.isHigh()) stateD3 = 1;
       if(D4.isHigh()) stateD4 = 1;
       
       
    }    
    
int changeIP(String ipAdd){
    
    String file = "/etc/network/interfaces";
         
                                //find,replace_with,file
    String command = "sed -i s/"+ getIP() +"/"+ ipAdd +"/g " + file  ;
         
    System.out.println(command);
    
    try {
         
        Process p = Runtime.getRuntime().exec(command);
        Process p1 = Runtime.getRuntime().exec("reboot");
         
     } catch (IOException ex) {
         Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
         return 0;
     }

return 1;
}

private String getIP(){
    
  return "0";

}
 
    
    
    
}
