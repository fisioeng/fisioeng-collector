/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisioeng.collector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lisso
 */
public class MeasureTest {
    String unit = "%";
    int value = 18372;
    
    public MeasureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetValueNull() {
      Measure measure = new Measure();

      assertEquals("Should value be '0' by default'", 0, measure.getValue());
    }

    @Test
    public void testGetValueReturnWhatWasProvided() {
      Measure measure = new Measure();
      measure.setValue(value);
      assertEquals("Should getValue return what was provided int setValue", value, measure.getValue());
    }

    @Test
    public void testSetValueReturnMeasureInstance() {
      Measure measure = new Measure();
      assertEquals("Should setValue to return measure instance", measure, measure.setValue(value));
    }

    @Test
    public void testGetUnitNull() {
      Measure measure = new Measure();

      assertEquals("Should unit be 'null' by default'", null, measure.getUnit());
    }

    @Test
    public void testGetUnitReturnWhatWasProvided() {
      Measure measure = new Measure();
      measure.setUnit(unit);

      assertEquals("Should getUnit return what was provided int setUnit", unit, measure.getUnit());
    }

    @Test
    public void testSetUnitReturnMeasureInstance() {
      Measure measure = new Measure();
      assertEquals("Should setUnit to return measure instance", measure, measure.setUnit(unit));
    }
}
