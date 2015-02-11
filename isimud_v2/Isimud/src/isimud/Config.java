/*
 * Copyright (C) 2014 gunebakan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package isimud;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gunebakan
 */
public class Config {
    String serial_port = "";
    
    
    boolean modbus_mode;

    Properties prop = new Properties();
    
    public Config(){
        try {
            prop.load(new FileInputStream("system.conf"));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        serial_port = prop.getProperty("serial_port");
        
        
        
        modbus_mode = Boolean.valueOf(prop.getProperty("modbus_mode"));
        
    }
    public void Update(){
        
 
            try {
    		
                //set the properties value  
                prop.setProperty("serial_port",serial_port);
                
                prop.setProperty("modbus_mode",String.valueOf(modbus_mode));
             
    		//save properties to project root folder
    		prop.store(new FileOutputStream("config.properties"), null);
 
            } catch (IOException ex) {
                System.out.println(ex);
            }
    
    }
    
    
    public String getIp(){
        
        
        return "";
    }
    
    
    public void changeIp(String new_ip){
        
        String old_ip = getIp();
        
        
        
        String file = "/etc/network/interfaces";

      
                                      //find,replace_with,file
        String command = "sed -i s/" + old_ip + "/" + new_ip + "/g "+ file;

        System.out.println(command);
        
        try {
            Process p = Runtime.getRuntime().exec(command);
            Process p1 = Runtime.getRuntime().exec("reboot");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
