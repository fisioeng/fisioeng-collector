/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author joseronierison
 */
public class XBee  implements Runnable, SerialPortEventListener {
    public String Dadoslidos;
    public int nodeBytes;
    private int baudrate;
    private int timeout;
    private CommPortIdentifier cp;
    private SerialPort porta;
    private OutputStream saida;
    private InputStream entrada;
    private Thread threadLeitura;
    private boolean IDPortaOK;
    private boolean PortaOK;
    private boolean Leitura;
    private boolean Escrita;
    private String Porta;
    protected String dado;

    private byte[] readBuffer = new byte[400];
    
    public String getDado() {
        return dado;
    }

    public void setDado(String peso) {
        this.dado = peso;
    }

    public void setPorta(String p){
        this.Porta = p;
    }

    public boolean getEscrita(){
        return Escrita;
    }

    public boolean getLeitura(){
        return Leitura;
    }

    public void HabilitarEscrita() {
        Escrita = true;
        Leitura = false;
    }

    public void HabilitarLeitura() {
        Escrita = false;
        Leitura = true;

    }
    //Obtem o Id da porta
    public void ObterIdDaPorta() {
        try {
            cp = CommPortIdentifier.getPortIdentifier(Porta);
            if (cp == null) {
                System.out.println("Erro na porta");
                IDPortaOK = false;
                System.exit(1);
            }
            IDPortaOK = true;
        } catch (Exception e) {
            System.out.println("Erro obtendo ID da porta:" + e);
            IDPortaOK = false;
            System.exit(1);
        }
    }
    //Abre cominicaçao da porta escolhida
    public void AbrirPorta() {
        try {
            ObterIdDaPorta();
            porta = (SerialPort) cp.open("SerialComLeitura", 10);
            PortaOK = true;

            //configurar parâmetros
            porta.setSerialPortParams(9600,
                    porta.DATABITS_8,
                    porta.STOPBITS_1,
                    porta.PARITY_NONE);
            porta.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (Exception e) {
            PortaOK = false;
            System.out.println("Erro abrindo comunicação: " + e);
            System.exit(1);
        }
    }

    public void LerDados() {
        if (Escrita == false) {
            try {
                entrada = porta.getInputStream();
            } catch (Exception e) {
                System.out.println("Erro de stream: " + e);
                System.exit(1);
            }
            try {
                porta.addEventListener(this);
            } catch (Exception e) {
                System.out.println("Erro de listener: " + e);
                System.exit(1);
            }
            porta.notifyOnDataAvailable(true);
            try {
                threadLeitura = new Thread(this);
                threadLeitura.start();
                run();
            } catch (Exception e) {
                System.out.println("Erro de Thred: " + e);
            }
        }
    }

    public void EnviarUmaString(String msg) {
        if (Escrita == true) {
            try {
                saida = porta.getOutputStream();
                System.out.println("FLUXO OK!");
            } catch (Exception e) {
                System.out.println("Erro.STATUS: " + e);
            }
            try {
                System.out.println("Enviando um byte para " + Porta);
                System.out.println("Enviando : " + msg);
                saida.write(msg.getBytes());
                Thread.sleep(100);
                //saida.flush();
            } catch (Exception e) {
                System.out.println("Houve um erro durante o envio. ");
                System.out.println("STATUS: " + e);
                System.exit(1);
            }
        } else {
            System.exit(1);
        }
    }

    public String readSerial() {
        try {
            int availableBytes = entrada.available();
            if (availableBytes > 0) {
                // Read the serial port
                entrada.read(readBuffer, 0, availableBytes);

                // Print it out
                return new String(readBuffer, 0, availableBytes);
            }
        } catch (IOException e) {
            System.out.println("ERROR!!!!!");
        }
        return "no data";
    }

    public void run() {
        try {
            Thread.sleep(10000);
            }catch (Exception e) {
            System.out.println("Erro de Thred: " + e);
        }
    }

    //Este método monitora e obtem os dados da porta
    @Override
    public void serialEvent(SerialPortEvent ev) {
        System.out.println("entering in a event...");
        StringBuffer bufferLeitura = new StringBuffer();
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
                break;
            case SerialPortEvent.DATA_AVAILABLE:
        //Algortimo de leitura
                while (novoDado != -1) {
                    try {
                        novoDado = entrada.read();
                        if (novoDado == -1) {
                            break;
                        }
                        if ('\r' == (char) novoDado) {
                            bufferLeitura.append('\n');
                        } else {
                            bufferLeitura.append((char) novoDado);
                        }
                    } catch (IOException ioe) {
                        System.out.println("Erro de leitura serial: " + ioe);
                    }
                }
                setDado(new String(bufferLeitura));
                recebeuDado();
                break;
        }
    }
    //Método para conferir se recebeu dados
    public void recebeuDado(){
        String dado = getDado();
        if(dado != null){
            System.out.print(getDado()+"\n");
        }
    }

    //Metodo para fechar a porta COMq
    public void FecharCom() {
        try {
            porta.close();
            System.out.println("Porta "+this.Porta+" fechada");
        } catch (Exception e) {
            System.out.println("Erro fechando porta: " + e);
            System.exit(0);
        }
    }

    public String obterPorta() {
        return Porta;
    }

    public int obterBaudrate() {
        return baudrate;
    }
}
