/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector.serial;

import gnu.io.CommPortIdentifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
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


        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            list.add(port.getName());
        }

        return list;
    }
    
    
}
