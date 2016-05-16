package fisioeng.collector;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class Request {
    
    public HttpResponse saveMeasure(String url, Measure measure) throws UnsupportedEncodingException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setEntity(buildParams(measure));
        request.addHeader("content-type", "application/json");              
        
        return httpClient.execute(request);
    }
    
    public String getReturn(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        InputStream input = entity.getContent();
        StringBuilder responseBuffer = new StringBuilder();
        int newData = 0;

        while (newData != -1) {
            newData = input.read();

            if (newData == 13 || newData == -1) {                            
                break;
            }

            if ('\r' == (char) newData ) {
                responseBuffer.append('\n');
                continue;
            }
            
            responseBuffer.append((char) newData);
        }
        
        return new String(responseBuffer);
    }
    
    private StringEntity buildParams(Measure measure) throws UnsupportedEncodingException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        StringEntity params = new StringEntity("{\"measure\": {"
                + "\"value\":\"" + measure.getValue() + "\","
                + "\"unit\":\"" + measure.getUnit() + "\", "
                + "\"serie\":\"" + measure.getSerie() + "\", "
                + "\"dateandtime\":\"" + dateFormat.format(date) + "\"}}");
        
        return params;
    }
    
    
}
