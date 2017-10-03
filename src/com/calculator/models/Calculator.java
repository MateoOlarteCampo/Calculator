package com.calculator.models;

public class Calculator {

  double memory;
  
  public Calculator() {
    memory = 0;
  }
  
  public double add(double operator1, double operator2) {
    return operator1 + operator2;
  }

  public double sub(double operator1, double operator2) {
    return operator1 - operator2;
  }

  public double div(double operator1, double operator2) {
    return operator1 / operator2;
  }

  public double product(double operator1, double operator2) {
    return operator1 * operator2;
  }

  public double squareRoot(double operator1) {
    return Math.sqrt(operator1);
  }

  public double squared(double operator1) {
    return Math.pow(operator1, 2);
  }

  public void memorySave(double newMemory) {
    this.memory = newMemory;
  }

  public void memoryPlus(double value) {
    this.memory += value;
  }

  public void memorySub(double value) {
    this.memory -= value;
  }

  public void memoryClear() {
    this.memory = 0;
  }
  
  public double memoryRecover() {
    return memory; 
  }
}