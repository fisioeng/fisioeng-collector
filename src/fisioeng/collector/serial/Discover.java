package fisioeng.collector.serial;

import gnu.io.CommPortIdentifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Discover {
    
    public List<String> getAvailablePorts() {        
        List<String> list = new ArrayList<>();    


        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            list.add(port.getName());
        }

        return list;
    }
    
    
}
