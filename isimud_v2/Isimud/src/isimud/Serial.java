/*
 * Copyright (C) 2015 gunebakan
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

import com.pi4j.io.serial.SerialPortException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

/**
 *
 * @author gunebakan
 */
public class Serial {
    
    static SerialPort serialPort;
    String readSerialData;
    
    Config config = new Config();
    
    public Serial(){
        serialPort = new SerialPort(config.serial_port);
        
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void writeSerial(String data){
        
        try {
            System.out.println(data);
            serialPort.writeBytes(data.getBytes());
            
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void readSerial(){
        
        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
        
        try {
            
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
        
    }
    public void closePort(){
        
        try {
            System.out.println("Port closed: " + serialPort.closePort());
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /*
     * In this class must implement the method serialEvent, through it we learn about 
     * events that happened to our port. But we will not report on all events but only 
     * those that we put in the mask. In this case the arrival of the data and change the 
     * status lines CTS and DSR
     */
    class SerialPortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            
            String data = "";
            
            if(event.isRXCHAR()){//If data is available
                if(event.getEventValue() == 10){//Check bytes count in the input buffer
                    //Read data, if 10 bytes available 
                    try {
                        byte buffer[] = serialPort.readBytes(10);
                        readSerialData = Arrays.toString(buffer);
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    } catch (jssc.SerialPortException ex) {
                        Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(!data.equals("")){
                    System.out.println(readSerialData);
                }
            }
            else if(event.isCTS()){//If CTS line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){///If DSR line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }


    
    
}

