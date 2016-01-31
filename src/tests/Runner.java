/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import tests.collector.MeasureSpec;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Runner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(MeasureSpec.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    String endMsg = "Was successfull!";
    System.out.println(result.getRunCount() + " tests runned in ms: " + result.getRunTime());
    
    if(!result.wasSuccessful()) {
       endMsg = "The test suit failed!";
    }
    
    System.out.println(endMsg);
  }
}