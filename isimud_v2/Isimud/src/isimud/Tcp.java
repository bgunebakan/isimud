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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

/**
 *
 * @author gunebakan
 */


public class Tcp {
    
TCPMasterConnection con = null; //the connection
ModbusTCPTransaction trans = null; //the transaction
ReadInputDiscretesRequest req = null; //the request
ReadInputDiscretesResponse res = null; //the response

/* Variables for storing the parameters */
InetAddress addr = null; //the slave's address
int port = Modbus.DEFAULT_PORT;
int ref = 0; //the reference; offset where to start reading from
int count = 0; //the number of DI's to read
int repeat = 1; //a loop for repeating the transaction


Config config = new Config();
Serial serial = new Serial();

private final String USER_AGENT = "Mozilla/5.0";

public void initModbus(String[] args){
    //1. Setup the parameters
    if (args.length < 3) {
        System.exit(1);
    } else {
    
        try {
            String astr = args[0];
            int idx = astr.indexOf(':');
            
            if( true ) {
                port = Integer.parseInt(astr.substring(idx+1));
                astr = astr.substring(0,idx);
            }
    
            addr = InetAddress.getByName(astr);
            ref = Integer.decode(args[1]);
            count = Integer.decode(args[2]);
    
            if (args.length == 4) {
                repeat = Integer.parseInt(args[3]);
            }
        } catch (NumberFormatException | UnknownHostException ex) {
            System.exit(1);
        }
    }
    
    //2. Open the connection
    con = new TCPMasterConnection(addr);
    con.setPort(port);
    
    try {
        con.connect();
    } catch (Exception ex) {
        Logger.getLogger(Tcp.class.getName()).log(Level.SEVERE, null, ex);
    }

    //3. Prepare the request
    req = new ReadInputDiscretesRequest(ref, count);

    //4. Prepare the transaction
    trans = new ModbusTCPTransaction(con);
    trans.setRequest(req);


    //5. Execute the transaction repeat times
    int k = 0;

    do {
        try {
            trans.execute();
        } catch (ModbusSlaveException ex) {
            Logger.getLogger(Tcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModbusException ex) {
            Logger.getLogger(Tcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        res = (ReadInputDiscretesResponse) trans.getResponse();
        System.out.println("Digital Inputs Status=" + res.getDiscretes().toString());
        k++;
    } while (k < repeat);

    //6. Close the connection
    con.close();
    
}
   
	public void sendPost(String url,String urlParameters) throws Exception {
 
		
		URL obj = new URL(url);
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
   public void startServer(String data) {
       
      
      try {
         ServerSocket srvr = new ServerSocket(Integer.valueOf(config.server_port));
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         
         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         
         out.print(data);
         
         out.close();
         skt.close();
         srvr.close();
      }
      catch(NumberFormatException | IOException e) {
         System.out.print("Whoops! It didn't work!\n");
      }
   }
   public void connectServer(){
       
       String data = "";
       try {
        Socket skt = new Socket(config.server_add, Integer.valueOf(config.serial_port));
        BufferedReader in = new BufferedReader(new
            
        InputStreamReader(skt.getInputStream()));
        System.out.print("Received string: '");

        while (!in.ready()) {}
        System.out.println(in.readLine()); // Read one line and output it
         
        data = data + in.readLine();
         
        serial.writeSerial(data);
         
        System.out.print("'\n");
        in.close();
         
      }
      catch(NumberFormatException | IOException e) {
         System.out.print("Whoops! It didn't work!\n");
      }
       
   }

}
