/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author Siti Alfiyyatuz Z A
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame {

    private JTextField displayField;
    private String currentNumber = "";
    private String previousNumber = "";
    private String operator = "";

    public calculator() {
        setTitle("Kalkulator Sederhana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayField = new JTextField(10);
        displayField.setFont(new Font("Arial", Font.BOLD, 22));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayPanel.add(displayField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5, 5, 5));

        String[] angka = {"7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "/", "="};
        for (String text : angka) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }

        String[] operatorText = {"%", "Mod", "Exit"};
        for (String text : operatorText) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(displayPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("0") || actionCommand.equals("1") || actionCommand.equals("2") ||
                    actionCommand.equals("3") || actionCommand.equals("4") || actionCommand.equals("5") ||
                    actionCommand.equals("6") || actionCommand.equals("7") || actionCommand.equals("8") ||
                    actionCommand.equals("9") || actionCommand.equals(".")) {
                currentNumber += actionCommand;
                displayField.setText(currentNumber);
            } else if (actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("*") ||
                    actionCommand.equals("/")) {
                if (!currentNumber.isEmpty()) {
                    previousNumber = currentNumber;
                    currentNumber = "";
                    operator = actionCommand;
                    displayField.setText("");
                }
            } else if (actionCommand.equals("=")) {
                if (!currentNumber.isEmpty()) {
                    double result = calculate(Double.parseDouble(previousNumber), Double.parseDouble(currentNumber), operator);
                    displayField.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                    previousNumber = "";
                    operator = "";
                }
            } else if (actionCommand.equals("%")) {
                if (!currentNumber.isEmpty()) {
                    double result = Double.parseDouble(currentNumber) / 100;
                    displayField.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                }
            } else if (actionCommand.equals("Mod")) {
                if (!currentNumber.isEmpty()) {
                    int result = (int) (Double.parseDouble(currentNumber) % 2);
                    displayField.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                }
            } else if (actionCommand.equals("Exit")) {
                System.exit(0);
            }
        }
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }
    
    public static void main(String[] args) {
        Calculator kalkulator = new Calculator();
        kalkulator.setLocationRelativeTo(null);
    }
}
