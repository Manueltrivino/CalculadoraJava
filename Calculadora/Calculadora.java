import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    // Componentes de la interfaz gráfica
    private JTextField pantalla; // Pantalla para mostrar números y resultados
    private JButton[] botonesNumeros = new JButton[10]; // Botones para los números 0-9
    private JButton[] botonesFunciones = new JButton[9]; // Botones para operaciones y funciones
    private JButton botonSuma, botonResta, botonMultiplica, botonDivide;
    private JButton botonDecimal, botonIgual, botonBorrar, botonLimpiar, botonPorcentaje;
    private JPanel panelBotones; // Panel para organizar los botones

    // Variables para la lógica de la calculadora
    private double num1 = 0, num2 = 0, resultado = 0;
    private char operador;
    private boolean nuevaEntrada = true; // Indica si la siguiente entrada numérica debe reemplazar el contenido de la pantalla

    // Fuentes
    Font fuentePantalla = new Font("Arial", Font.BOLD, 30);
    Font fuenteBotones = new Font("Arial", Font.PLAIN, 18);

    public CalculadoraGUI() {
        // Configuración de la ventana principal
        this.setTitle("Calculadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 550);
        this.setLayout(null); // Usamos layout nulo para posicionar componentes manualmente
        this.setResizable(false); // Evitar que se redimensione la ventana
        this.getContentPane().setBackground(Color.decode("#f0f0f0")); // Color de fondo

        // Inicialización de la pantalla
        pantalla = new JTextField();
        pantalla.setBounds(30, 25, 360, 60);
        pantalla.setFont(fuentePantalla);
        pantalla.setEditable(false); // No se puede escribir directamente en la pantalla
        pantalla.setHorizontalAlignment(JTextField.RIGHT); // Alineación del texto a la derecha
        pantalla.setText("0"); // Valor inicial
        pantalla.setBackground(Color.WHITE);
        pantalla.setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc")));

        // Inicialización de los botones de funciones
        botonSuma = new JButton("+");
        botonResta = new JButton("-");
        botonMultiplica = new JButton("*");
        botonDivide = new JButton("/");
        botonDecimal = new JButton(".");
        botonIgual = new JButton("=");
        botonBorrar = new JButton("DEL"); // Borrar último dígito
        botonLimpiar = new JButton("C"); // Limpiar todo
        botonPorcentaje = new JButton("%");

        botonesFunciones[0] = botonSuma;
        botonesFunciones[1] = botonResta;
        botonesFunciones[2] = botonMultiplica;
        botonesFunciones[3] = botonDivide;
        botonesFunciones[4] = botonDecimal;
        botonesFunciones[5] = botonIgual;
        botonesFunciones[6] = botonBorrar;
        botonesFunciones[7] = botonLimpiar;
        botonesFunciones[8] = botonPorcentaje;

        for (int i = 0; i < botonesFunciones.length; i++) {
            botonesFunciones[i].addActionListener(this);
            botonesFunciones[i].setFont(fuenteBotones);
            botonesFunciones[i].setFocusable(false); // Quitar el borde de foco
            botonesFunciones[i].setBackground(Color.decode("#e0e0e0"));
            botonesFunciones[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc")));
        }
        botonIgual.setBackground(Color.decode("#4CAF50")); // Color diferente para el botón igual
        botonIgual.setForeground(Color.WHITE);
        botonLimpiar.setBackground(Color.decode("#f44336")); // Color para Limpiar
        botonLimpiar.setForeground(Color.WHITE);
        botonBorrar.setBackground(Color.decode("#ff9800")); // Color para Borrar
        botonBorrar.setForeground(Color.WHITE);


        // Inicialización de los botones numéricos
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = new JButton(String.valueOf(i));
            botonesNumeros[i].addActionListener(this);
            botonesNumeros[i].setFont(fuenteBotones);
            botonesNumeros[i].setFocusable(false);
            botonesNumeros[i].setBackground(Color.WHITE);
            botonesNumeros[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc")));
        }

        // Posicionamiento de los botones de control (Limpiar, Borrar, Porcentaje)
        botonLimpiar.setBounds(30, 100, 80, 50);
        botonBorrar.setBounds(120, 100, 80, 50);
        botonPorcentaje.setBounds(210, 100, 80, 50);
        botonDivide.setBounds(300, 100, 80, 50);


        // Panel para los botones numéricos y de operaciones
        panelBotones = new JPanel();
        panelBotones.setBounds(30, 160, 360, 340);
        panelBotones.setLayout(new GridLayout(4, 4, 10, 10)); // Cuadrícula para los botones
        panelBotones.setBackground(Color.decode("#f0f0f0")); // Color de fondo del panel

        // Añadir botones al panel en el orden deseado
        // Fila 1
        panelBotones.add(botonesNumeros[7]);
        panelBotones.add(botonesNumeros[8]);
        panelBotones.add(botonesNumeros[9]);
        panelBotones.add(botonMultiplica);
        // Fila 2
        panelBotones.add(botonesNumeros[4]);
        panelBotones.add(botonesNumeros[5]);
        panelBotones.add(botonesNumeros[6]);
        panelBotones.add(botonResta);
        // Fila 3
        panelBotones.add(botonesNumeros[1]);
        panelBotones.add(botonesNumeros[2]);
        panelBotones.add(botonesNumeros[3]);
        panelBotones.add(botonSuma);
        // Fila 4
        panelBotones.add(botonesNumeros[0]); // Botón 0
        panelBotones.add(botonDecimal);
        panelBotones.add(botonIgual);


        // Añadir componentes a la ventana
        this.add(pantalla);
        this.add(botonLimpiar);
        this.add(botonBorrar);
        this.add(botonPorcentaje);
        this.add(botonDivide);
        this.add(panelBotones);

        this.setVisible(true); // Hacer visible la ventana
    }

    public static void main(String[] args) {
        // Crear una instancia de la calculadora
        new CalculadoraGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand(); // Obtener el texto del botón presionado

        // Lógica para los botones numéricos
        for (int i = 0; i < 10; i++) {
            if (comando.equals(String.valueOf(i))) {
                if (nuevaEntrada) {
                    pantalla.setText(comando);
                    nuevaEntrada = false;
                } else {
                    if (pantalla.getText().equals("0")) {
                        pantalla.setText(comando);
                    } else {
                        pantalla.setText(pantalla.getText() + comando);
                    }
                }
                return;
            }
        }

        // Lógica para el botón decimal
        if (comando.equals(".")) {
            if (nuevaEntrada) {
                pantalla.setText("0.");
                nuevaEntrada = false;
            } else if (!pantalla.getText().contains(".")) {
                pantalla.setText(pantalla.getText() + ".");
            }
            return;
        }

        // Lógica para el botón Limpiar (C)
        if (comando.equals("C")) {
            pantalla.setText("0");
            num1 = 0;
            num2 = 0;
            operador = '\0'; // Carácter nulo
            nuevaEntrada = true;
            return;
        }

        // Lógica para el botón Borrar (DEL)
        if (comando.equals("DEL")) {
            String textoActual = pantalla.getText();
            if (textoActual.length() > 0) {
                // Si solo hay un dígito o es un número negativo con un dígito, poner 0
                if (textoActual.length() == 1 || (textoActual.startsWith("-") && textoActual.length() == 2)) {
                    pantalla.setText("0");
                    nuevaEntrada = true;
                } else {
                    pantalla.setText(textoActual.substring(0, textoActual.length() - 1));
                }
            }
             if (pantalla.getText().isEmpty() || pantalla.getText().equals("-")) { // Si se borra todo o solo queda el signo negativo
                pantalla.setText("0");
                nuevaEntrada = true;
            }
            return;
        }

        // Lógica para el botón de porcentaje (%)
        if (comando.equals("%")) {
            if (!pantalla.getText().equals("Error")) {
                try {
                    double valorActual = Double.parseDouble(pantalla.getText());
                    resultado = valorActual / 100.0;
                    pantalla.setText(String.valueOf(resultado));
                    nuevaEntrada = true;
                } catch (NumberFormatException ex) {
                    pantalla.setText("Error");
                    nuevaEntrada = true;
                }
            }
            return;
        }


        // Lógica para los operadores (+, -, *, /)
        if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            if (!pantalla.getText().equals("Error")) {
                // Si ya hay un num1 y se presiona otro operador, calcular el resultado intermedio
                if (operador != '\0' && !nuevaEntrada) {
                     botonIgual.doClick(); // Simula un clic en el botón igual
                }
                num1 = Double.parseDouble(pantalla.getText());
                operador = comando.charAt(0);
                nuevaEntrada = true;
            }
            return;
        }

        // Lógica para el botón Igual (=)
        if (comando.equals("=")) {
            if (!pantalla.getText().equals("Error") && operador != '\0') {
                num2 = Double.parseDouble(pantalla.getText());
                switch (operador) {
                    case '+':
                        resultado = num1 + num2;
                        break;
                    case '-':
                        resultado = num1 - num2;
                        break;
                    case '*':
                        resultado = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            pantalla.setText("Error: Div por 0");
                            num1 = 0; // Resetear para evitar errores en cálculos subsiguientes
                            operador = '\0';
                            nuevaEntrada = true;
                            return;
                        }
                        resultado = num1 / num2;
                        break;
                }
                // Formatear el resultado para evitar ".0" en enteros
                if (resultado == (long) resultado) {
                    pantalla.setText(String.format("%d", (long) resultado));
                } else {
                    pantalla.setText(String.format("%s", resultado));
                }
                num1 = resultado; // El resultado se convierte en el primer operando para cálculos continuos
                operador = '\0'; // Resetear el operador
                nuevaEntrada = true; // Preparar para una nueva entrada
            }
        }
    }
}
