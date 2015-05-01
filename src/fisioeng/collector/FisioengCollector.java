/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;

/**
 *
 * @author joseronierison
 */
public class FisioengCollector {
     /* Constants */
    // TODO Replace with the port where your sender module is connected to.
    private static final String PORT = "COM1";
    // TODO Replace with the baud rate of your sender module.
    private static final int BAUD_RATE = 9600;
     
    private static final String DATA_TO_SEND = "Hello XBee World!";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);
        byte[] dataToSend = DATA_TO_SEND.getBytes();
         
        try {
            myDevice.open();
             
            System.out.format("Sending broadcast data: '%s'", new String(dataToSend));
             
            myDevice.sendBroadcastData(dataToSend);
             
            System.out.println(" >> Success");
             
        } catch (XBeeException e) {
            System.out.println(" >> Error");
            e.printStackTrace();
            System.exit(1);
        } finally {
            myDevice.close();
        }
    }
    
}
