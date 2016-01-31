package tests.collector;

import fisioeng.collector.Measure;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MeasureSpec {
  String unit = "%";
  int value = 18372;
  
  @Test
  public void getValueNull() {
    Measure measure = new Measure();

    assertEquals("Should value be '0' by default'", 0, measure.getValue());
  }
  
  @Test
  public void getValueReturnWhatWasProvided() {
    Measure measure = new Measure();
    measure.setValue(value);
    assertEquals("Should getValue return what was provided int setValue", value, measure.getValue());
  }
  
  @Test
  public void setValueReturnMeasureInstance() {
    Measure measure = new Measure();
    assertEquals("Should setValue to return measure instance", measure, measure.setValue(value));
  }
  
  @Test
  public void getUnitNull() {
    Measure measure = new Measure();

    assertEquals("Should unit be 'null' by default'", null, measure.getUnit());
  }
  
  @Test
  public void getUnitReturnWhatWasProvided() {
    Measure measure = new Measure();
    measure.setUnit(unit);
    
    assertEquals("Should getUnit return what was provided int setUnit", unit, measure.getUnit());
  }
  
  @Test
  public void setUnitReturnMeasureInstance() {
    Measure measure = new Measure();
    assertEquals("Should setUnit to return measure instance", measure, measure.setUnit(unit));
  }
}
