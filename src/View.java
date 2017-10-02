import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;



public class View extends JFrame {
	Model model = new Model();

	/** numero tecleado */
	JTextField pantalla;

	/** guarda el resultado de la operacion anterior o el número tecleado */
	double resultado;

	/** para guardar la operacion a realizar */
	String operacion;

	/** Los paneles donde colocaremos los botones */
	JPanel panelNumeros, panelOperaciones;

	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;

	/**
	 * Constructor. Crea los botones y componentes de la calculadora
	 */
	public View() {
		super();
		setSize(450, 400);
		setTitle("Calculadora");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		// Vamos a dibujar sobre el panel
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}

		nuevoBotonNumerico(".");

		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
		nuevoBotonOperacion("x2");
		nuevoBotonOperacion("M");
		nuevoBotonOperacion("M+");
		nuevoBotonOperacion("M-");
		nuevoBotonOperacion("MR");
		nuevoBotonOperacion("MC");
		nuevoBotonOperacion("\u221A");
		nuevoBotonOperacion("=");
		nuevoBotonOperacion("CE");

		panel.add("East", panelOperaciones);

		validate();
	}

	/**
	 * Crea un boton del teclado numérico y enlaza sus eventos con el listener
	 * correspondiente
	 * 
	 * @param digito
	 *            boton a crear
	 */
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	/**
	 * Crea un botón de operacion y lo enlaza con sus eventos.
	 * 
	 * @param operacion
	 */
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.RED);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}

	/**
	 * Gestiona las pulsaciones de teclas numéricas
	 * 
	 * @param digito
	 *            tecla pulsada
	 */
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	/**
	 * Gestiona el gestiona las pulsaciones de teclas de operación
	 * 
	 * @param tecla
	 */
	private void operacionPulsado(String tecla) {
		Double param1 = new Double(pantalla.getText());
		Double result;
		switch (tecla) {
		case "=":
			calcularResultado();
			break;
		case "CE":
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
			break;
		case "M":
			if(pantalla.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "Debes seleccionar un valor para almacenar en memoria");
			}else {
				String numberScreen = pantalla.getText();
				model.M(new Double(numberScreen));
				JOptionPane.showMessageDialog(null, "El número "+ numberScreen+" ha sido almacenado en memoria");
				pantalla.setText("");
			}
			break;
		case "M+":
			model.Mplus();
			pantalla.setText(model.getSto()+"");
			break;
		case "M-":
			model.Msub();
			pantalla.setText(model.getSto()+"");
			break;
		case "MR":
			pantalla.setText(model.getSto()+"");
			break;
		case "MC":
			model.setSto(0);
			break;
		case "\u221A":
			result = model.squareRoot(param1);
			pantalla.setText(result+"");
			break;
		case "x2":
			result = model.squared(param1);
			pantalla.setText(result+"");
			break;
		default:
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
			break;
		}

		nuevaOperacion = true;
	}

	/**
	 * Hace uso de los métodos del modelo para calcular el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
		switch (operacion) {
		case "+":
			resultado = model.add(resultado, new Double(pantalla.getText()));
			break;
		case "-":
			resultado = model.sub(resultado, new Double(pantalla.getText()));
			break;
		case "/":
			resultado = model.div(resultado, new Double(pantalla.getText()));
			break;
		case "*":
			resultado = model.product(resultado, new Double(pantalla.getText()));
			break;
		default:
			JOptionPane.showMessageDialog(null, "Error interno, operación no reconocida");
			break;
		}
		
		pantalla.setText("" + resultado);
		operacion = "";
	}
}