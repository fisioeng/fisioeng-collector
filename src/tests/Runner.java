package tests;

import tests.collector.MeasureSpec;
import junit.framework.TestSuite;

public class Runner {
  public Runner() {
      
  }
  
  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  public static TestSuite suite() { 
    TestSuite suite = new TestSuite(MeasureSpec.class);
    return suite;
  }
}