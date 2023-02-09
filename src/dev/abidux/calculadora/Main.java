package dev.abidux.calculadora;

import javax.swing.*;
import java.util.HashMap;

public class Main {

    public static final String APP_TITLE = "Calculadora";

    public static final HashMap<Character, Operation> OPERATIONS = new HashMap<>() {{
        put('+', (a, b) -> a+b);
        put('-', (a, b) -> a-b);
        put('*', (a, b) -> a*b);
        put('/', (a, b) -> a/b);
        put('^', Math::pow);
    }};

    public static void main(String[] args) {
        String symbol = JOptionPane.showInputDialog(null, "Insira a operação:", APP_TITLE, JOptionPane.PLAIN_MESSAGE);
        Operation operation = OPERATIONS.get(symbol.charAt(0));
        if (operation == null) {
            JOptionPane.showMessageDialog(null, "Operação inválida.", APP_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String first = JOptionPane.showInputDialog(null, "Insira o primeiro número:", APP_TITLE, JOptionPane.PLAIN_MESSAGE);
        double a = parseNumber(first);
        if (Double.isNaN(a)) {
            JOptionPane.showMessageDialog(null, "Número inválido.", APP_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String second = JOptionPane.showInputDialog(null, "Insira o segundo número:", APP_TITLE, JOptionPane.PLAIN_MESSAGE);
        double b = parseNumber(second);
        if (Double.isNaN(b)) {
            JOptionPane.showMessageDialog(null, "Número inválido.", APP_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double result = operation.calculate(a, b);
            JOptionPane.showMessageDialog(null, "O resultado é: " + result, APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Conta inválida", APP_TITLE, JOptionPane.ERROR);
        }
    }

    private static double parseNumber(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }

    private interface Operation {
        double calculate(double a, double b) throws NumberFormatException;
    }

}