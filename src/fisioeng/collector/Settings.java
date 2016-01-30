package fisioeng.collector;

import java.util.prefs.Preferences;


public class Settings {
    final String HOST_KEY = "host";
    final String ROTE_KEY = "route";
    final String HTTPVERB_KEY = "httpverb";
    final String APIKEY_KEY = "apikey";
    
    protected String host;
    protected String rote;
    protected String httpVerb;
    protected String apiKey;
    protected Preferences proferences;
    
    public Settings() {
        proferences = Preferences.userNodeForPackage(Settings.class);
        
        setHost(proferences.get(HOST_KEY, "http://fisioeng.local:3000"));
        setRote(proferences.get(ROTE_KEY, "/api"));
        setHttpVerb(proferences.get(HTTPVERB_KEY, "post"));
        setApiKey(proferences.get(APIKEY_KEY, ""));
    }
    
    public void Save() {
        proferences.put(HOST_KEY, getHost());
        proferences.put(ROTE_KEY, getRote());
        proferences.put(HTTPVERB_KEY, getHttpVerb());
        proferences.put(APIKEY_KEY, getApiKey());
    }
    
    public Settings setHost(String host) {
        this.host = host;
        return this;
    }
    
    public Settings setRote(String rote) {
        this.rote = rote;
        return this;
    }
    
    public Settings setHttpVerb(String httpVerb) {
        this.httpVerb = httpVerb;
        return this;
    }
    
    public Settings setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
    
    public String getHost() {
        return host;
    }
    
    public String getRote() {
        return rote;
    }
    
    public String getHttpVerb() {
        return httpVerb;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
}
