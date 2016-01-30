package fisioeng.collector;

import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
import java.util.Date;

public class Logger {
    protected JTextArea jTextArea;
    
    public Logger(JTextArea textArea) {
        jTextArea = textArea;
    }
    
    public void error(String message) {
        jTextArea.append(getDate() + " ERROR: " + message + "\n");
    }
    
    public void info(String message) {
        jTextArea.append(getDate() + " INFO:   " + message+ "\n");
    }
    
    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        return dateFormat.format(date);
    }
}
