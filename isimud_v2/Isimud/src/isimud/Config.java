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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gunebakan
 */
public class Config {
    String serial_port = "";
    
    String server_add = "";
    int server_port;
    
    boolean transmit_mode;
    boolean modbus_mode;

    Properties prop = new Properties();
    
    public Config(){
        try {
            prop.load(new FileInputStream("system.conf"));
            
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        serial_port = prop.getProperty("serial_port");
        
        server_add = prop.getProperty("server_add");
        server_port = Integer.valueOf(prop.getProperty("server_port"));
        transmit_mode = Boolean.valueOf(prop.getProperty("transmit_mode"));
        
        
        modbus_mode = Boolean.valueOf(prop.getProperty("modbus_mode"));
        
    }
    public void Update(){
        
 
            try {
    		
                //set the properties value  
                prop.setProperty("serial_port",serial_port);
                
                prop.setProperty("server_add",server_add);
                prop.setProperty("server_port",String.valueOf(server_port));
                prop.setProperty("transmit_mode",String.valueOf(transmit_mode));
                
                prop.setProperty("modbus_mode",String.valueOf(modbus_mode));
             
    		//save properties to project root folder
    		prop.store(new FileOutputStream("system.conf"), null);
 
            } catch (IOException ex) {
                System.out.println(ex);
            }
    
    }
    
    
    public String getIp(){
        
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                
                    InetAddress addr = addresses.nextElement();
                    if(addr instanceof Inet4Address){
                        ip = addr.getHostAddress();
                        System.out.println( ip);
                        return ip;
                    }
                
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    return null; 
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
