package com.calculator.models.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.calculator.models.Calculator;

public class AdvancedOperationsTest {

  private Calculator calculator; 
  private double resultExpected;
  private double resultActual;
  
  @Before
  public void setUp() throws Exception {
    calculator = new Calculator();
  }
  
  @Test 
  public void rootSquareTest(){
    resultExpected = 2;
    resultActual = calculator.squareRoot(4);
    assertEquals(resultExpected, resultActual,0);
  }
  
  @Test 
  public void squaredTest(){
    resultExpected = 4;
    resultActual = calculator.squared(2);
    assertEquals(resultExpected, resultActual,0);
  }
  

}