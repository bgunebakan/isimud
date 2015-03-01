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

import static isimud.Comm.serialPort;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

/**
 *
 * @author gunebakan
 */
public class Comm {
    
    Config config = new Config();
    
    static SerialPort serialPort;
    
    byte[] readSerialData = new byte[32]; 
    
    //boolean bufferFull = false;
    boolean modbus_mode = config.modbus_mode; //false;
    String serial_port = config.serial_port; //"/dev/ttyACM0"; 
    int serial_baud = config.serial_baud;    // 9600;
    ////
    
    boolean server_mode = config.server_mode; //true;
    int server_port = config.server_port;     // 4000;
    String server_add = config.server_add;    //"178.62.254.151";
    
    Socket client; 
    ServerSocket server;
    DataOutputStream out;
    InputStream in;
    
    OutputStream outToServer;
    
    /////////////////////////////////////TCP/////////////////////////////////
     
    public void initTcp(){
        
        if(server_mode){
            
            try {
                server = new ServerSocket(server_port);
                client = server.accept();
                
                outToServer = client.getOutputStream();
                out = new DataOutputStream(outToServer);
                out.writeUTF("Server_mode: Connection ok with " + client.getLocalSocketAddress());
                
                
                in = client.getInputStream();
                
                if(in == null)
     
                System.out.println("input stream created alright");
                
            } catch (IOException ex) {
                Logger.getLogger(Comm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
        
            try{
         
                System.out.println("Client_mode: Connecting to " + server_add + " on port " + server_port);
         
                client = new Socket(server_add, server_port);
         
                System.out.println("Just connected to " + client.getRemoteSocketAddress());
         
                outToServer = client.getOutputStream();
         
                out = new DataOutputStream(outToServer);

         
                out.writeUTF("Connection ok with " + client.getLocalSocketAddress());
            
                in = client.getInputStream();
         
                if(in == null)
     
                System.out.println("input stream created alright");
            
            
            
            //client.close();
      
            }catch(IOException e){
                Logger.getLogger(Comm.class.getName()).log(Level.SEVERE, null, e);
            }    
        }
        
        
    
    }
    
    public void closeTcp(){
         
        if (client != null) {
        
            try {
            
                out.flush();
            
                out.close();
                in.close();
                client.close();
            
            } catch (IOException e) {
                Logger.getLogger(Comm.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    }
    
    
    /////////////////////////////////////SERIAL///////////////////////////////
    public void initSerial(){
    
        serialPort = new SerialPort(serial_port);
        
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(serial_baud, 8, 1, 0));
            
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }

    }
        
    public void writeSerial(byte[] data){
        
    
        try {
            System.out.println("w"+Arrays.toString(data));
            serialPort.writeBytes(data);
            data = null;
            
            
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        
    public void readSerial(){
        
        int mask = SerialPort.MASK_RXCHAR;// + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
        
        try {
            
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            
        } catch (jssc.SerialPortException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
        
    class SerialPortReader implements SerialPortEventListener {
    
        String data = "";
        int i =0;
        
        public void serialEvent(SerialPortEvent event) {
            
            
            
            if(event.isRXCHAR()){//If data is available               
                     
                    try {
                        readSerialData = null;
                        readSerialData = serialPort.readBytes(32);
                       
                            if(modbus_mode){
                                
                            }else{
                                System.out.println("s"+Arrays.toString(readSerialData));
                                out.write(readSerialData);
                                
                            }
                          
                    }
                    catch (SerialPortException | IOException ex) {
                        Logger.getLogger(Comm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
          
        }

        
    }
        
    
/////////////extra methods

        
    public void sendPost(String url,String urlParameters) throws Exception {

            
        URL obj = new URL(url);
            
        String USER_AGENT = "Mozilla/5.0";
            
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
            
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
            
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);    
        }
        
            
        in.close();
        //print result
        System.out.println(response.toString());
    
    }

    public void cleanBuff(byte[] buff){
       
       for(int i=0;i<buff.length;i++){
           buff[i] = 0;
       }
       
    }

}//end Comm class


class tcpThread implements Runnable {
   
    private Thread t;
   
    Comm comm = new Comm();
    Config config = new Config();
    byte[] buff = new byte[32];
    
   
    tcpThread(){
       
        System.out.println("tcp connection thread starting...");
        comm.initTcp();
        comm.initSerial();
        comm.readSerial();
    } 
   
   
   
    @Override
    public void run() {
    
        System.out.println("tcp connection running..");
      
        while(true){
        
            try {
             
                while ((comm.in.read(buff)) > 0) {
                  
                    try {
                        if(config.modbus_mode){
                            
                        }else{
                            serialPort.writeBytes(buff);
                            System.out.println("w"+Arrays.toString(buff));
                            comm.cleanBuff(buff);
                        }
                        
                    } catch (SerialPortException ex) {              
                        Logger.getLogger(tcpThread.class.getName()).log(Level.SEVERE, null, ex);                  
                    }
              
                }
            
            } catch (IOException ex) {
              
                Logger.getLogger(tcpThread.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
     
   
    }
   
   
    public void start (){
        
      
        System.out.println("Starting tcp connection.");
      
        if (t == null){
            
            t = new Thread (this);
            t.start ();
        }
    }

}

