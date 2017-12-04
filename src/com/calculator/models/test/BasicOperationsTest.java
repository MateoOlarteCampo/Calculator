package com.calculator.models.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.calculator.models.Calculator;

public class BasicOperationsTest {
	 private Calculator calculator; 
	 private double resultExpected;
	 private double resultActual;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	@Test
	public void addTest() {
	    //Prueba para valores mayores al maximo double permitido
	    resultExpected = Double.POSITIVE_INFINITY;
	    resultActual = calculator.add(Double.MAX_VALUE,Double.MAX_VALUE);
	    assertEquals(resultExpected,resultActual,0);
	   
	    //Prueba para valores dentro del intervalo
	    resultExpected = 10;
	    resultActual = calculator.add(5,5);
	    assertEquals(resultExpected,resultActual,0);
	    
	    //Prueba para valores mayores al minimo double permitido
	    resultExpected = Double.NEGATIVE_INFINITY;
	    resultActual = calculator.add(-Double.MAX_VALUE,-Double.MAX_VALUE);
	    assertEquals(resultExpected,resultActual,0); 
	  }
	  
	  
	  @Test
	  public void subTest() {
	    //Prueba para valores mayores al maximo double permitido
	    resultExpected = Double.POSITIVE_INFINITY;
	    resultActual = calculator.sub(Double.MAX_VALUE*3,Double.MAX_VALUE);
	    assertEquals(resultExpected,resultActual,0);
	   
	    //Prueba para valores dentro del intervalo
	    resultExpected = 0;
	    resultActual = calculator.sub(5,5);
	    assertEquals(resultExpected,resultActual,0);
	    
	    //Prueba para valores mayores al minimo double permitido
	    resultExpected = Double.NEGATIVE_INFINITY;
	    resultActual = calculator.sub(-Double.MAX_VALUE,Double.MAX_VALUE);
	    assertEquals(resultExpected,resultActual,0); 
	  }
	  
	  @Test
	  public void productTest() {
	  //Prueba para valores mayores al maximo double permitido
	    resultExpected = Double.POSITIVE_INFINITY;
	    resultActual = calculator.product(Double.MAX_VALUE,2);
	    assertEquals(resultExpected,resultActual,0);
	   
	    //Prueba para valores dentro del intervalo
	    resultExpected = 25;
	    resultActual = calculator.product(5,5);
	    assertEquals(resultExpected,resultActual,0);
	    
	    //Prueba para valores mayores al minimo double permitido
	    resultExpected = Double.NEGATIVE_INFINITY;
	    resultActual = calculator.product(-Double.MAX_VALUE,2);
	    //assertEquals(resultExpected,resultActual,0); 
	  }
	  
	  @Test
	  public void divTest() {
	  //Prueba para valores mayores al maximo double permitido
	    resultExpected = Double.POSITIVE_INFINITY;
	    resultActual = calculator.div(Double.MAX_VALUE*2,1);
	    assertEquals(resultExpected,resultActual,0);
	   
	    //Prueba para valores dentro del intervalo
	    resultExpected = 1;
	    resultActual = calculator.div(5,5);
	    assertEquals(resultExpected,resultActual,0);
	    
	    //Prueba para valores mayores al minimo double permitido
	    resultExpected = Double.NEGATIVE_INFINITY;
	    resultActual = calculator.div(-Double.MAX_VALUE*2,1);
	    assertEquals(resultExpected,resultActual,0); 
	    
	    
	    // Prueba cuando hay division por cero 
	    resultExpected = Double.POSITIVE_INFINITY;
	    resultActual = calculator.div(5,0);
	    assertEquals(resultExpected,resultActual,0);   
	  }

}
