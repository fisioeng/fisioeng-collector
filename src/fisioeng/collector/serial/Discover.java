/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector.serial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseronierison
 */
public class Discover {
    

    /**
     * Return the available ports in linux based systems
     * 
     * @return List<String> 
     */
    public List<String> getAvailablePorts() {        
        List<String> list = new ArrayList<String>();        
        String res = "";
        BufferedReader br;
        String cmd;
        Process proc;

        try {
            /*search for ports where Prolific PL2303 cable is attached*/
            cmd = "ls /dev/ttyUSB*";
            proc = Runtime.getRuntime().exec(new String[]{"bash","-c",cmd});
            
            /* parse the command line results*/
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            do {
                
                try {
                    res = br.readLine();
                    if (res == null)
                        break;
                    
                    list.add(res);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } while (!res.equals("null"));
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    
}
