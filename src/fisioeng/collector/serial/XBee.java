/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class XBee  implements Runnable, SerialPortEventListener {
    
    protected String data;
  
    protected Boolean connected = false;
    
    private CommPortIdentifier portIdentifier;
    
    private SerialPort port;
    
    protected String portName;
    
    private OutputStream output;
    private InputStream input;
    
    private final int timeout;
    private final int baudrate;
    
    public String Dadoslidos;
    public int nodeBytes;
    
    private Thread threadLeitura;

    public XBee() {
        this.timeout = 1000;
        this.baudrate = 9600;
    }
    
    
    public void connect() throws Exception {
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            
            
            port = (SerialPort) portIdentifier.open(this.getClass().getName(), timeout);
            
            port.setSerialPortParams(baudrate, port.DATABITS_8, SerialPort.STOPBITS_1, port.PARITY_NONE);         
            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            
            System.out.println(portName);
            output = port.getOutputStream();
            input = port.getInputStream();
            
            port.addEventListener(this);
            port.notifyOnDataAvailable(true);
            
            connected = true;
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public void sendMensage(String msg) throws Exception {
        try {
            output.write(msg.getBytes());            
            output.write(0xd);
            
            Thread.sleep(1000);
            
            threadLeitura = new Thread(this);
            threadLeitura.start();
            
            run();
        } catch (IOException | InterruptedException e) {
            throw e;
        }     
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            
        }
    }
    
    @Override
    public void serialEvent(SerialPortEvent ev) {
        StringBuilder responseBuffer = new StringBuilder();
        int novoDado = 0;
        switch (ev.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                while (novoDado != -1) {
                    try {
                        novoDado = input.read();
                        
                        if (novoDado == -1) {
                            break;
                        }

                        if ('\r' == (char) novoDado) {
                            responseBuffer.append('\n');
                        } else {
                            responseBuffer.append((char) novoDado);
                        }
                    } catch (IOException ioe) {
                        System.out.println("Erro de leitura serial: " + ioe.getMessage());
                    }
                }
                
                setData(new String(responseBuffer));
                break;
                
        }
    }

    /**
     * 
     * @throws Exception
     */
    public void disconnect() throws Exception {
        try {
            port.close();
            
            connected = false;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Boolean isConnected() {
        return connected;
    }

    public String getPortName() {
        return portName;
    }

    public int getBaudrate() {
        return baudrate;
    }
    
    public String getData() {
        return data;
    }

    public XBee setData(String data) {
        this.data = data;
        return this;
    }
    
    public void setPortName(String p){
        this.portName = p;
    }
}
