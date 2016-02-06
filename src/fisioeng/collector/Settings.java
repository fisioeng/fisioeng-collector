package fisioeng.collector;

import java.util.prefs.Preferences;


public class Settings {
    final String URL_KEY = "url";
    final String SERIE_KEY = "serie";
    final String APIKEY_KEY = "apikey";
    
    protected String url;
    protected String serie;
    protected String apiKey;
    protected Preferences proferences;
    
    public Settings() {
        proferences = Preferences.userNodeForPackage(Settings.class);
        
        setUrl(proferences.get(URL_KEY, "http://fisioeng.local:3000"));
        setSerie(proferences.get(SERIE_KEY, "1"));
        setApiKey(proferences.get(APIKEY_KEY, ""));
    }
    
    public void Save() {
        proferences.put(URL_KEY, getUrl());
        proferences.put(SERIE_KEY, getSerie());
        proferences.put(APIKEY_KEY, getApiKey());
    }
    
    public final Settings setUrl(String url) {
        this.url = url;
        return this;
    }
    
    public final Settings setSerie(String serie) {
        this.serie = serie;
        return this;
    }
        
    public final Settings setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getSerie() {
        return serie;
    }
        
    public String getApiKey() {
        return apiKey;
    }
    
}
