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
    
    /*public final GpioController gpio = GpioFactory.getInstance();

    //GpioPinDigitalOutput satReset = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18,"satReset",PinState.LOW);
    public GpioPinDigitalOutput satOn = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17,"satOn",PinState.LOW);
    
    GpioPinDigitalInput D0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,"D0",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,"D1",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,"D2",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,"D3",PinPullResistance.PULL_DOWN);
    GpioPinDigitalInput D4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06,"D4",PinPullResistance.PULL_DOWN);
*/
    public void portSending(String optionValue) {
        
    }

    public void initGpio(){
        
        try {
            //sat on
            Process a = Runtime.getRuntime().exec("echo '0' > /sys/class/gpio/export");
            Process a1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio0/direction");
            //sat reset
            Process b = Runtime.getRuntime().exec("echo '2' > /sys/class/gpio/export");
            Process b1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio2/direction");
            //sat led
            Process f = Runtime.getRuntime().exec("echo '3' > /sys/class/gpio/export");
            Process f1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio3/direction");
            //gps led
            Process c = Runtime.getRuntime().exec("echo '4' > /sys/class/gpio/export");
            Process c1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio4/direction");
            //d0
            Process d = Runtime.getRuntime().exec("echo '1' > /sys/class/gpio/export");
            Process d1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio1/direction");
            //d1
            Process e = Runtime.getRuntime().exec("echo '5' > /sys/class/gpio/export");
            Process e1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio5/direction");
            //d2
            Process g = Runtime.getRuntime().exec("echo '6' > /sys/class/gpio/export");
            Process g1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio6/direction");
            //d3
            Process h = Runtime.getRuntime().exec("echo '7' > /sys/class/gpio/export");
            Process h1 = Runtime.getRuntime().exec("echo 'out' > /sys/class/gpio/gpio7/direction");
            
        } catch (IOException ex) {
            Logger.getLogger(Gpio.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        
        
    }
    public String getPorts() {
 /*
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
       
   */    
        return "d0=1&d1=0&d2=1&d3=0&d4=1&";
    }    
    
}
