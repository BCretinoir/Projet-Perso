import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JTextField expressionField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator = ' ';
    StringBuilder expression = new StringBuilder();
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        expressionField = new JTextField();
        expressionField.setBounds(60, 10, 300, 30);
        expressionField.setFont(myFont);
        expressionField.setEditable(false);
        expressionField.setBackground(Color.BLACK);
        expressionField.setForeground(Color.WHITE);
        frame.add(expressionField);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        frame.add(textField);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(Color.gray);
            functionButtons[i].setForeground(Color.white);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.gray);
            numberButtons[i].setForeground(Color.white);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.black);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
                expression.append(i);
                expressionField.setText(expression.toString());
            }
        }
        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }
        if (e.getSource() == addButton) {
            calculate();
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            calculate();
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            calculate();
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            calculate();
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            calculate();
            operator = '=';
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
            expression.setLength(0);
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
    }

    private void calculate() {
        if (!textField.getText().isEmpty()) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
                case '=':
                    break;
                default:
                    result = num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
