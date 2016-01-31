package fisioeng.collector;

public class Measure {
    protected int value;
    protected int serie;
    protected String unit;
    
    public Measure setValue(int value) {
        this.value = value;
        return this;
    }
    
    public Measure setUnit(String unit) {
        this.unit = unit;
        return this;
    }
    
    public Measure setSerie(int serie) {
        this.serie = serie;
        return this;
    }
    
    public int getSerie(){
        return serie;
    }
    
    public int getValue(){
        return value;
    }
    
    public String getUnit(){
        return unit;
    }
}
