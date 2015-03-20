   /*
 * Copyright (C) 2014 Bilal Tonga
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

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gunebakan
 */
public class Config {
    
    public String serial_port = "";
    public int serial_baud = 0;
    
    public String server_add = "";
    public int server_port = 0;
    
    public String local_ip = "";
    public String sat_ip = "";
    public String client_ip = ""; // connected device ip
    
    public String postserver_add = "";
    
    public boolean transmit_mode = false;
    public boolean modbus_mode = false;
    public boolean server_mode = false;
    public String working_mode = "";
    
    public String port_values;
    
    public PrintWriter log_file;
    Properties prop = new Properties();
    
    public Config(){
        
        
        
        try {
            prop.load(new FileInputStream("system.conf"));
            
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        serial_port = prop.getProperty("serial_port");
        serial_baud = Integer.parseInt(prop.getProperty("serial_baud"));
        
        server_add = prop.getProperty("server_add");
        server_port = Integer.parseInt(prop.getProperty("server_port"));
        
        local_ip = prop.getProperty("local_ip");
        client_ip = prop.getProperty("client_ip");
        
        postserver_add = prop.getProperty("postserver_add");
        
        sat_ip = prop.getProperty("sat_ip");
        
        port_values = prop.getProperty("port_values");
        
        modbus_mode = Boolean.valueOf(prop.getProperty("modbus_mode"));
        server_mode = Boolean.valueOf(prop.getProperty("server_mode"));
        working_mode = prop.getProperty("working_mode");
        transmit_mode = Boolean.valueOf(prop.getProperty("transmit_mode"));
    }
    
    public void Update(){
        
            
        //Properties prop = new Properties();
        
        try {
            
            //set the properties value  
                prop.setProperty("serial_port",serial_port);
                prop.setProperty("serial_baud",String.valueOf(serial_baud));
                
                prop.setProperty("server_add",server_add);
                prop.setProperty("server_port",String.valueOf(server_port));
                
                prop.setProperty("local_ip",local_ip);
                prop.setProperty("client_ip",client_ip);
                prop.setProperty("sat_ip",sat_ip);
                
                prop.setProperty("postserver_add",postserver_add);
                
                prop.setProperty("port_values",port_values);
                
                prop.setProperty("modbus_mode",String.valueOf(modbus_mode));
                prop.setProperty("server_mode",String.valueOf(server_mode));
                prop.setProperty("working_mode",String.valueOf(working_mode));
    		prop.setProperty("transmit_mode",String.valueOf(transmit_mode));
                
                //save properties to project root folder
    		prop.store(new FileOutputStream("system.conf"), null);
                
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
    
    }
    ///////////SQL LITE
      public void connectDb(){
          
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
      
      
    public void createTable(){
    
    
        Connection c = null;
        Statement stmt = null;
    
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE CONFIG " +
                       "(CONFIG_NAME NOT NULL," +
                       " VALUE NOT NULL);"; 
                        
        
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    
        System.out.println("Table created successfully");
  }

    ///////////777
    
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
    
    
    public void changeIp(String new_localip,String new_clientip){
        
        //String old_ip = getIp();
        
        String old_localip = local_ip;
        String old_clientip = client_ip;
        
        ////////create ip range
        int[] ip = new int[4];
        String[] parts = new_clientip.split("\\.");
        
        for(int i=0;i<4;i++){
            ip[i] = Integer.parseInt(parts[i]);
        }
        ip[3] += 10;
        String newclientip_end = ip[0] +"."+ip[1]+"."+ip[2]+"."+ip[3];
        ///////////7
        int[] oldip = new int[4];
        String[] oldparts = old_clientip.split("\\.");
        
        for(int i=0;i<4;i++){
            oldip[i] = Integer.parseInt(oldparts[i]);
        }
        oldip[3] += 10;
        String oldclientip_end = oldip[0] +"."+oldip[1]+"."+oldip[2]+"."+oldip[3];
        //////////////7
        
        
        
        String file = "/etc/network/interfaces";
        
        String clientfile = "/etc/dnsmasq.conf";
        String longclient_ip = client_ip + "," + oldclientip_end;
        String longnew_clientip = new_clientip + "," + newclientip_end;
                                      //find,replace_with,file
        String command =  "sed -i s/" + local_ip + "/" + new_localip + "/g "+ file;
        String command2 = "sed -i s/" + longclient_ip + "/" + longnew_clientip + "/g "+ clientfile;
        //String command3 = "sed -i s/" + oldclientip_end + "/" + newclientip_end + "/g "+ clientfile;
        
        System.out.println(command +"\n"+ command2);
        
        
        local_ip = new_localip;
        client_ip = new_clientip;
        Update();
        
        try {
            Process p = Runtime.getRuntime().exec(command);
            Process p1 = Runtime.getRuntime().exec(command2);
            //Process p1 = Runtime.getRuntime().exec("reboot");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public void writeLog(String log){
        
        try {    
            log_file = new PrintWriter(new BufferedWriter(new FileWriter("log/system.log", true)));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log_file.println("- " + log);
        log_file.close();
        
    }
    public void cleanLog(){
        try {
            log_file = new PrintWriter("log/system.log","UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log_file.println("");
        log_file.close();
        
    }
}
