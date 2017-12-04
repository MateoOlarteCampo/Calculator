package com.calculator.views;

import com.calculator.models.Calculator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

  private static final String ADD_SYMBOL = "+";
  private static final String SUB_SYMBOL = "-";
  private static final String DIV_SYMBOL = "/";
  private static final String PRODUCT_SYMBOL = "*";
  private static final String SQUARE_ROOT_SYMBOL = "\u221A";
  private static final String SQUARED_SYMBOL = "x2";
  private static final String MEMORY_SAVE_SYMBOL = "M";
  private static final String MEMORY_ADD_SYMBOL = "M+";
  private static final String MEMORY_SUB_SYMBOL = "M-";
  private static final String MEMORY_RECOVER_SYMBOL = "MR";
  private static final String MEMORY_CLEAR_SYMBOL = "MC";
  private static final String EQUAL_SYMBOL = "=";
  private static final String CLEAR_OPERATION_SYMBOL = "CE";
 
  /** Pantalla para mostrar datos */
  JTextField displayScreen;

  /** guarda el resultado de la operacion anterior o el número tecleado */
  double result;

  JPanel operationsPane;
  
  /** Indica si estamos iniciando o no una operación */
  boolean newOperation;
  
  String operation;
  
  Calculator calculator;

  /**
   * Constructor. Crea los botones y componentes de la calculadora
   */
  public View() {
    super();
    setSize(450, 400);
    setTitle("Calculadora");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);

    JPanel panel = (JPanel) this.getContentPane();
    panel.setLayout(new BorderLayout());

    result = 0;
    newOperation = true;
    operation = "";
    calculator = new Calculator();
    
    initDisplayScreen(panel);
    initNumbersPane(panel);
    initOperationsPane(panel);

    validate();        
  }
  
  private void initDisplayScreen(JPanel panel) {
    displayScreen = new JTextField("0", 20);
    displayScreen.setBorder(new EmptyBorder(4, 4, 4, 4));
    displayScreen.setFont(new Font("Arial", Font.BOLD, 25));
    displayScreen.setHorizontalAlignment(JTextField.RIGHT);
    displayScreen.setEditable(false);
    displayScreen.setBackground(Color.WHITE);
    panel.add("North", displayScreen);
  }
  
  private void initNumbersPane(JPanel panel) {
    JPanel numbersPane = new JPanel();
    JPanel rowNumbersPane;
    numbersPane.setLayout(new GridLayout(4, 1));
    numbersPane.setBorder(new EmptyBorder(4, 4, 4, 4));
    for (int row = 3; row > 0; row--){
      rowNumbersPane = new JPanel();
      rowNumbersPane.setLayout(new GridLayout(1, 3));
      for (int column = 3 * row - 2; column <= 3 * row; column++) {
        createNumberButton(String.valueOf(column),rowNumbersPane);
      }
      numbersPane.add(rowNumbersPane);
    }
    
    rowNumbersPane = new JPanel();
    rowNumbersPane.setLayout(new GridLayout(1, 3));
    createNumberButton(String.valueOf(0),rowNumbersPane);
    createNumberButton(".",rowNumbersPane);
    createNumberButton(EQUAL_SYMBOL,rowNumbersPane);
    numbersPane.add(rowNumbersPane);
   
    panel.add("Center", numbersPane); 
  }
  
  /**
   * Crea un boton del teclado numérico y enlaza sus eventos con el listener
   * correspondiente
   * 
   * @param digito boton a crear
   */
  private void createNumberButton(String digito, JPanel rowNumbersPane) {
    JButton button = new JButton();
    button.setText(digito);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent evt) {
        JButton btn = (JButton) evt.getSource();
        String symbolPressed = btn.getText();
        
        if (!symbolPressed.equals(EQUAL_SYMBOL)){
          numberPressed(symbolPressed);         
        } else {
          operacionPulsado(symbolPressed);
        }
      }
    });

    rowNumbersPane.add(button);
  }

  private void initOperationsPane(JPanel panel) {
    operationsPane = new JPanel();
    operationsPane.setLayout(new GridLayout(4, 1));
    operationsPane.setBorder(new EmptyBorder(4, 4, 4, 4));

    createOperationButton(ADD_SYMBOL);
    createOperationButton(SUB_SYMBOL);
    createOperationButton(PRODUCT_SYMBOL);
    createOperationButton(DIV_SYMBOL);
    createOperationButton(SQUARED_SYMBOL);
    createOperationButton(SQUARE_ROOT_SYMBOL);
    createOperationButton(MEMORY_SAVE_SYMBOL);
    createOperationButton(MEMORY_ADD_SYMBOL);
    createOperationButton(MEMORY_SUB_SYMBOL);
    createOperationButton(MEMORY_RECOVER_SYMBOL);
    createOperationButton(MEMORY_CLEAR_SYMBOL);
    createOperationButton(CLEAR_OPERATION_SYMBOL);
    
    panel.add("East", operationsPane);    
  }

  /**
   * Crea un botón de operacion y lo enlaza con sus eventos. 
   * @param operationName
   */
  private void createOperationButton(String operationName) {
    JButton button = new JButton(operationName);
    button.setForeground(Color.RED);

    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent evt) {
        JButton button = (JButton) evt.getSource();
        operacionPulsado(button.getText());
      }
    });

    operationsPane.add(button);
  }

  /**
   * Gestiona las pulsaciones de teclas numéricas
   * 
   * @param digito tecla pulsada
   */
  private void numberPressed(String digito) {
    if (displayScreen.getText().equals("0") || newOperation) {
      displayScreen.setText(digito);
    } else {
      displayScreen.setText(displayScreen.getText() + digito);
    }
    newOperation = false;
  }

  /**
   * Gestiona las pulsaciones de teclas de operación
   * 
   * @param tecla
   */
  private void operacionPulsado(String tecla) {
    switch (tecla) {
      case EQUAL_SYMBOL:
        if (!operation.equals("") && !newOperation) {
          calcularResultado();
        } else {
          result = new Double(displayScreen.getText());
          operation = "";
        }
        break;
      case CLEAR_OPERATION_SYMBOL:
        result = 0;
        newOperation = true;
        break;
      case SQUARE_ROOT_SYMBOL:
        result = calculator.squareRoot(new Double(displayScreen.getText()));
        break;
      case SQUARED_SYMBOL:
        result = calculator.squared(new Double(displayScreen.getText()));
        break;
      case MEMORY_SAVE_SYMBOL:
        calculator.memorySave(new Double(displayScreen.getText()));
        break;
      case MEMORY_ADD_SYMBOL:
        calculator.memoryPlus();
        result = calculator.memoryRecover();
        showResult(result);
        break;
      case MEMORY_SUB_SYMBOL:
        calculator.memorySub();
        result = calculator.memoryRecover();
        showResult(result);
        break;
      case MEMORY_RECOVER_SYMBOL:
        result = calculator.memoryRecover();
        break;
      case MEMORY_CLEAR_SYMBOL:
        calculator.memoryClear();
        break;
      default:
        operation = tecla;
        if ((result > 0) && !newOperation) {
          calcularResultado();
        } else {
          result = new Double(displayScreen.getText());
        }
        break;
    }

    showResult(result); 
    newOperation = true;
  }

  /**
   * Hace uso de los métodos del modelo para calcular el resultado y lo muestra por pantalla
   */
  private void calcularResultado() {
    switch (operation) {
      case ADD_SYMBOL:
        result = calculator.add(result, new Double(displayScreen.getText()));
        break;
      case SUB_SYMBOL:
        result = calculator.sub(result, new Double(displayScreen.getText()));
        break;
      case DIV_SYMBOL:
        double denominator = new Double(displayScreen.getText());
        if (denominator > 0) {
          result = calculator.div(result, denominator);
        } else {
          JOptionPane.showMessageDialog(null, "División por cero no permitida");
          result = 0;
        }
        break;
      case PRODUCT_SYMBOL:
        result = calculator.product(result, new Double(displayScreen.getText()));
        break;
      default:
        JOptionPane.showMessageDialog(null, "Error interno, operación no reconocida");
        break;
    }
  }

  private void showResult(double result) {
    if (result % 1 == 0) {
      displayScreen.setText(String.valueOf((int)this.result));
    } else {
      displayScreen.setText(String.valueOf((this.result))); 
    }
  }
}