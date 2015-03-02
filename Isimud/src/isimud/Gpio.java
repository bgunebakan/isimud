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
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author gunebakan
 */
public class Gpio {
    
    Config config = new Config();
    public final GpioController gpio = GpioFactory.getInstance();

    GpioPinDigitalInput D0;
    GpioPinDigitalInput D1;
    GpioPinDigitalInput D2;
    GpioPinDigitalInput D3;
    

    public void portSending(String optionValue) {
        
    }

    public void init(){
        
        D0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01,"D0",PinPullResistance.PULL_DOWN);
        D1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,"D1",PinPullResistance.PULL_DOWN);
        D2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06,"D2",PinPullResistance.PULL_DOWN);
        D3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07,"D3",PinPullResistance.PULL_DOWN);
    
        
    }
    public String getPorts() {
 
       int stateD0 = 0;
       int stateD1 = 0;
       int stateD2 = 0;
       int stateD3 = 0;
       int stateD4 = 0;
       
       if(D0.isHigh()) stateD0 = 1;
       if(D1.isHigh()) stateD1 = 1;
       if(D2.isHigh()) stateD2 = 1;
       if(D3.isHigh()) stateD3 = 1;
       
        
    
        String portValues = "" + stateD0 + stateD1 + stateD2 + stateD3;
        
        config.port_values = portValues;
        config.Update();
        
        return portValues;
    }    
    
}
