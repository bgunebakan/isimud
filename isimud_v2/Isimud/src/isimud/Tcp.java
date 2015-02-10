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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.gt;
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

public void initConnection(String[] args){
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
    
    
}
