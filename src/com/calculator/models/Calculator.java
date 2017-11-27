package com.calculator.models;

public class Calculator {

  private double memory;
  
  public Calculator() {
    memory = 0;
  }
  
  public double add(double first_operator, double second_operator) {
    return first_operator + second_operator;
  }

  public double sub(double first_operator, double second_operator) {
    return first_operator - second_operator;
  }

  public double div(double first_operator, double second_operator) {
    return first_operator / second_operator;
  }

  public double product(double first_operator, double second_operator) {
    return first_operator * second_operator;
  }

  public double squareRoot(double operator) {
    return Math.sqrt(operator);
  }

  public double squared(double operator) {
    return Math.pow(operator, 2);
  }

  public void memorySave(double new_memory) {
    this.memory = new_memory;
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