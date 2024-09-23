package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;

public class CalculatorController {
    @FXML
    private Button bt_cero, bt_uno, bt_dos, bt_tres, bt_cuatro, bt_cinco, bt_seis, bt_siete, bt_ocho, bt_nueve;
    @FXML
    private Button igual, suma, resta, division, multiplicacion, decimal, limpiarPantalla;
    @FXML
    private TextField pantalla;

    private double num1, num2, resultado;
    private boolean newNum;
    private String operacionPantalla, operador;

    public void initialize() {
        num1 = 0;
        num2 = 0;
        newNum = false;
        operacionPantalla = "0";
        bt_cero.setOnAction(e -> clickBoton("0"));
        bt_uno.setOnAction(e -> clickBoton("1"));
        bt_dos.setOnAction(e -> clickBoton("2"));
        bt_tres.setOnAction(e -> clickBoton("3"));
        bt_cuatro.setOnAction(e -> clickBoton("4"));
        bt_cinco.setOnAction(e -> clickBoton("5"));
        bt_seis.setOnAction(e -> clickBoton("6"));
        bt_siete.setOnAction(e -> clickBoton("7"));
        bt_ocho.setOnAction(e -> clickBoton("8"));
        bt_nueve.setOnAction(e -> clickBoton("9"));
        suma.setOnAction(e -> clickOperador("+"));
        resta.setOnAction(e -> clickOperador("-"));
        division.setOnAction(e -> clickOperador("/"));
        multiplicacion.setOnAction(e -> clickOperador("*"));
        decimal.setOnAction(e -> clickBoton("."));
        limpiarPantalla.setOnAction(e -> limpiarPantalla());
        igual.setOnAction(e -> resultado());

        // Detectar teclat
        pantalla.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DIGIT0:
                    clickBoton("0");
                    break;
                case DIGIT1:
                    clickBoton("1");
                    break;
                case DIGIT2:
                    clickBoton("2");
                    break;
                case DIGIT3:
                    clickBoton("3");
                    break;
                case DIGIT4:
                    clickBoton("4");
                    break;
                case DIGIT5:
                    clickBoton("5");
                    break;
                case DIGIT6:
                    clickBoton("6");
                    break;
                case DIGIT7:
                    clickBoton("7");
                    break;
                case DIGIT8:
                    clickBoton("8");
                    break;
                case DIGIT9:
                    clickBoton("9");
                    break;
                case ADD:
                    clickOperador("+");
                    break;
                case SUBTRACT:
                    clickOperador("-");
                    break;
                case DIVIDE:
                    clickOperador("/");
                    break;
                case MULTIPLY:
                    clickOperador("*");
                    break;
                case DECIMAL:
                    clickBoton(".");
                    break;
                case ENTER:
                    resultado();
                    break;
                case ESCAPE:
                    limpiarPantalla();
                    break;
            }
        });
    }

    // Afegeix el número a la pantalla
    public void clickBoton(String valor) {
        if (valor.equals(".") && operacionPantalla.contains(".")) {
            // No pot tenir mes d'un decimal
            // Reproduir so error
            Toolkit.getDefaultToolkit().beep();
            System.out.println("Decimal repetit");
            return;
        }
        else if (valor.equals("0") && operacionPantalla.equals("0")) {
            // No es poden posar mes d'un zero a la esquerra.
            // Reproduir so error
            Toolkit.getDefaultToolkit().beep();
            System.out.println("Zero a l'esquerra");
            return;
        } else if (operacionPantalla.length() == 20) {
            // No es poden mostrar més de 20 caràcters a la pantalla
            // Reproduir so error
            Toolkit.getDefaultToolkit().beep();
            System.out.println("Màxim de caràcters a la pantalla");
            return;
        } else if (operacionPantalla.equals("0") || newNum) {
            if (valor.equals(".")) {
                operacionPantalla += valor;
            } else {
                operacionPantalla = valor;
            }
            newNum = false;
        } else {
            operacionPantalla += valor;
        }
        pantalla.setText(operacionPantalla);
    }

    // Guarda el número i l'operador per a la operació
    public void clickOperador(String valor) {
        newNum = true;
        operador = valor;
        num1 = Double.parseDouble(operacionPantalla);
    }

    // Esborra tot el contingut de la pantalla
    public void limpiarPantalla() {
        operacionPantalla = "0";
        num1 = 0;
        num2 = 0;
        resultado = 0;
        pantalla.setText(operacionPantalla);
    }
    // Calcular el resultat de la operació
    public void resultado() {
        num2 = Double.parseDouble(operacionPantalla);
        if (operador == null) {
            resultado = num2;
        }
        else if (operador.equals("+")) {
            resultado = num1 + num2;

        }
        else if (operador.equals("-")) {
            resultado = num1 - num2;
        }
        else if (operador.equals("*")) {
            resultado = num1 * num2;
        }
        else if (operador.equals("/")) {
            resultado = num1 / num2;
        }
        // Comprova si el resultat és un nombre enter
        if (resultado % 1 == 0) {
            int result = (int) resultado;
            operacionPantalla = String.valueOf(result);
        }
        else {
            operacionPantalla = String.valueOf(resultado);
        }
        operador = null;
        pantalla.setText(operacionPantalla);
    }
}
