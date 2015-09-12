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


/**
 *
 * @author joseronierison
 */
public class XBee  implements Runnable, SerialPortEventListener {
    
    /**
     * Data received from serial
     * 
     * @String
     */
    protected String data;
    
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

    private final byte[] readBuffer;

    public XBee() {
        this.timeout = 1000;
        this.baudrate = 9600;
        this.readBuffer = new byte[400];
    }
    
    
    
    /**
     * Open the serial port
     * 
     * @throws java.lang.Exception
     */
    public void connect() throws Exception {
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            
            port = (SerialPort) portIdentifier.open(this.getClass().getName(), timeout);
            
            port.setSerialPortParams(baudrate, port.DATABITS_8, SerialPort.STOPBITS_1, port.PARITY_NONE);         
            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                        
            output = port.getOutputStream();
            input = port.getInputStream();
            
            port.addEventListener(this);
            port.notifyOnDataAvailable(true);
            
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Send message to serial
     * 
     * @param msg 
     *  
     * @throws java.lang.Exception 
     */
    public void sendMensage(String msg) throws Exception {
        System.out.println("trying to send message ..");
        try {
            System.out.println();
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
    
    private String readSerial() {
        try {
            int availableBytes = input.available();
            if (availableBytes > 0) {
                input.read(readBuffer, 0, availableBytes);
                
                return new String(readBuffer, 0, availableBytes);
            }
        } catch (IOException e) {
            System.out.println("ERROR!!!!! " + e.getMessage());
        }
        return "no data";
    }

    //Este método monitora e obtem os dados da porta
    @Override
    public void serialEvent(SerialPortEvent ev) {
        StringBuilder responseBuffer = new StringBuilder();
        int novoDado = 0;
        switch (ev.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                System.out.println("new data");
            break;
            case SerialPortEvent.DATA_AVAILABLE:
            System.out.println(1);
                while (novoDado != -1) {
                    System.out.println(2);
                    try {
                        System.out.println(3);
                        System.out.println("aasdd " + input.available());
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
        } catch (Exception e) {
            throw e;
        }
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
