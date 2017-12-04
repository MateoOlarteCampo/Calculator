package com.calculator.models.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.calculator.models.Calculator;

public class MemoryTest {

	 private Calculator calculator; 
	 private double resultExpected;
	 private double resultActual;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	//Prueba memorySave y memoryRecover
	  @Test 
	  public void memorySaveTest(){
	    resultExpected = 2;
	    calculator.memorySave(2);
	    resultActual = calculator.memoryRecover();
	    assertEquals(resultExpected, resultActual,0);
	  }
	  
	  @Test 
	  public void memoryPlusTest(){
	    resultExpected = 2;
	    calculator.memorySave(1);
	    calculator.memoryPlus();
	    resultActual = calculator.memoryRecover();
	    assertEquals(resultExpected, resultActual,0);
	  }
	  
	  @Test 
	  public void memorySubTest(){
	    resultExpected = 1;
	    calculator.memorySave(2);
	    calculator.memorySub();
	    resultActual = calculator.memoryRecover();
	    assertEquals(resultExpected, resultActual,0);
	  }
	  
	  @Test 
	  public void memoryClearTest(){
	    resultExpected = 0;
	    calculator.memoryClear();
	    resultActual = calculator.memoryRecover();
	    assertEquals(resultExpected, resultActual,0);
	  }

}
