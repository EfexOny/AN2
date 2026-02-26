import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Stack;



public class Calculator extends JFrame {

    JButton digits[] = {
            new JButton(" 0 "), new JButton(" 1 "), new JButton(" 2 "),
            new JButton(" 3 "), new JButton(" 4 "), new JButton(" 5 "),
            new JButton(" 6 "), new JButton(" 7 "), new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton operators[] = {
            new JButton(" + "), new JButton(" - "), new JButton(" * "),
            new JButton(" / "), new JButton(" = "), new JButton(" C "),
            new JButton(" ) "), new JButton(" ( ")
    };

    String oper_values[] = {"+", "-", "*", "/", "=", "", ")", "("};
    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(400, 500);
        calculator.setTitle(" Java-Calc-Extended, PP Lab1 ");
        calculator.setResizable(true);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Returneaza precedenta operatorilor.
     * Parantezele au prioritate 0 in interiorul stivei pentru a nu scoate alti operatori.
     */
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
     * Evalueaza expresia in forma postfixata (poloneza).
     * Ordinea operanzilor: n2 este operandul din stanga, n1 cel din dreapta.
     */
    public static String EvaluareExpresie(String expresie) {
        if (expresie.isEmpty()) return "";
        Stiva stack = new Stiva();

        for (int i = 0; i < expresie.length(); i++) {
            char c = expresie.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                if (stack.IsEmpty()) continue;
                double n1 = Double.parseDouble(stack.top()); stack.pop();
                if (stack.IsEmpty()) return String.valueOf(n1);
                double n2 = Double.parseDouble(stack.top()); stack.pop();
                stack.push(Operatie(c, n2, n1));
            }
        }
        return stack.top();
    }

    /**
     * Transforma infix in postfix
     * Parcurgere standard de la stanga la dreapta.
     */
    public static StringBuilder FormaPoloneza(String expresiaInfixata) {
        StringBuilder rezultat = new StringBuilder();
        Stiva stack = new Stiva();

        for (int i = 0; i < expresiaInfixata.length(); i++) {
            char c = expresiaInfixata.charAt(i);

            if (Character.isDigit(c)) {
                rezultat.append(c);
            } else if (c == '(') {
                stack.push(String.valueOf(c));
            } else if (c == ')') {
                while (!stack.IsEmpty() && !stack.top().equals("(")) {
                    rezultat.append(stack.top());
                    stack.pop();
                }
                if (!stack.IsEmpty()) stack.pop(); // Elimina "("
            } else {
                // Operator: scoate din stiva operatorii cu prioritate mai mare sau egala
                while (!stack.IsEmpty() && !stack.top().equals("(") &&
                        GradSemn(c) <= GradSemn(stack.top().charAt(0))) {
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
        return rezultat;
    }

    public Calculator() {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (JButton b : digits) buttonpanel.add(b);
        for (JButton b : operators) buttonpanel.add(b);

        add(buttonpanel, BorderLayout.CENTER);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        // ActionListeners pentru cifre
        for (int i = 0; i < digits.length; i++) {
            int finalI = i;
            digits[i].addActionListener(e -> area.append(Integer.toString(finalI)));
        }

        for (int i = 0; i < operators.length; i++) {
            int finalI = i;
            operators[i].addActionListener(e -> {
                if (finalI == 5) { // Butonul 'C'
                    area.setText("");
                } else if (finalI == 4) { // Butonul '='
                    try {
                        String infix = area.getText();
                        String postfix = FormaPoloneza(infix).toString();
                        area.setText(EvaluareExpresie(postfix));
                    } catch (Exception ex) {
                        area.setText(" Eroare ");
                    }
                } else { // Operatori si paranteze
                    area.append(oper_values[finalI]);
                }
            });
        }
    }
}