import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import java.util.Stack;



public class Calculator extends JFrame {
    JButton[] digits = new JButton[10];
    JButton[] operators = new JButton[8];
    String[] oper_values = {"+", "-", "*", "/", "=", "C", ")", "("};
    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(400, 500);
        calculator.setTitle("Java-Calc-Prefix (Forma Poloneza)");
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static int GradSemn(char semn) {
        switch (semn) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '(': case ')': return 0;
            default: return -1;
        }
    }

    public static String Operatie(char op, double n1, double n2) {
        switch (op) {
            case '+': return String.valueOf(n1 + n2);
            case '-': return String.valueOf(n1 - n2);
            case '*': return String.valueOf(n1 * n2);
            case '/': return n2 != 0 ? String.valueOf(n1 / n2) : "0";
            default: return "0";
        }
    }

    /**
     * Evalueaza Forma Poloneza (Prefix)
     * Parcurgere: Dreapta -> Stanga
     */
    public static String EvaluareExpresie(String expresie) {
        Stiva stack = new Stiva();
        for (int i = expresie.length() - 1; i >= 0; i--) {
            char c = expresie.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                double n1 = Double.parseDouble(stack.top()); stack.pop();
                double n2 = Double.parseDouble(stack.top()); stack.pop();
                // In Prefix, n1 e primul operand, n2 al doilea
                stack.push(Operatie(c, n1, n2));
            }
        }
        return stack.top();
    }

    /**
     * Transforma Infix in Forma Poloneza (Prefix)
     */
    public static String FormaPoloneza(String infix) {
        StringBuilder rezultat = new StringBuilder();
        Stiva stack = new Stiva();
        //(4+2)/5
        //

        String reversedInfix = new StringBuilder(infix).reverse().toString();
        System.out.println(reversedInfix);

        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);

            if (Character.isDigit(c)) {
                rezultat.append(c);
            } else if (c == ')') {
                stack.push("(");
            } else if (c == '(') {
                while (!stack.IsEmpty() && !stack.top().equals("(")) {
                    rezultat.append(stack.top());
                    stack.pop();
                }
                stack.pop();
            } else {
                while (!stack.IsEmpty() && GradSemn(c) < GradSemn(stack.top().charAt(0))) {
                    rezultat.append(stack.top());
                    stack.pop();
                }
                stack.push(String.valueOf(c));
            }
        }
        while (!stack.IsEmpty()) {
            rezultat.append(stack.top());
            stack.pop();
        }
        //Rezultatul final se inverseaza din nou
        return rezultat.reverse().toString();
    }

    public Calculator() {
        setLayout(new BorderLayout());
        add(new JScrollPane(area), BorderLayout.NORTH);
        area.setEditable(false);

        JPanel buttonpanel = new JPanel(new FlowLayout());
        for (int i = 0; i < 10; i++) {
            digits[i] = new JButton(" " + i + " ");
            int val = i;
            digits[i].addActionListener(e -> area.append(String.valueOf(val)));
            buttonpanel.add(digits[i]);
        }

        for (int i = 0; i < operators.length; i++) {
            operators[i] = new JButton(oper_values[i]);
            int index = i;
            operators[i].addActionListener(e -> handleOperator(index));
            buttonpanel.add(operators[index]);
        }
        add(buttonpanel, BorderLayout.CENTER);
    }

    private void handleOperator(int index) {
        String val = oper_values[index];
        if (val.equals("C")) {
            area.setText("");
        } else if (val.equals("=")) {
            try {
                String infix = area.getText();
                String prefix = FormaPoloneza(infix);
                area.setText(EvaluareExpresie(prefix));
            } catch (Exception ex) {
                area.setText("Eroare");
            }
        } else {
            area.append(val);
        }
    }
}