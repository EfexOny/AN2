import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Calculator extends JFrame {


    JButton digits[] = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton operators[] = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C ")
    };

    String oper_values[] = {"+", "-", "*", "/", "=", ""};

    String value;
    char operator;

    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//        calculator.setSize(230, 200);
//        calculator.setTitle(" Java-Calc-Extended, PP Lab1 ");
//        calculator.setResizable(false);
//        calculator.setVisible(true);
//        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(FormaPoloneza("1+2/2+(2-3*4)"));


    }


    /*
        Determina daca semnul extras mai devreme este mai mic ca si prioritate fata de cel din stiva
        Returneaza 1 daca semnul extras e mai mic in prioritate altfel returneaza 0
     */
    public static int GradSemn(char semn){
        switch (semn) {
            case ')': case '(': return 0;
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }

    public static StringBuilder FormaPoloneza(String expresiaInfixata){

        StringBuilder rezultat=new StringBuilder();
        Stiva stack=new Stiva();

        for(int i=expresiaInfixata.length() -1 ;i>=0;i--) {
            char c = expresiaInfixata.charAt(i);
            // daca e cifra o pui direct
            if(Character.isDigit(c)) {
                rezultat.append(c);
            }else if(c=='('){
                while(!stack.IsEmpty() && !stack.top().equals(")")){
                    rezultat.append(stack.top());
                    stack.pop();
                }
                stack.pop();//scapi de )
            }else {
                if(!stack.IsEmpty() && GradSemn(c) < GradSemn(stack.top().charAt(0))){
                    while (!stack.IsEmpty() && GradSemn(c) < GradSemn(stack.top().charAt(0))) {
                        rezultat.append(stack.top());
                        stack.pop();
                        stack.push(String.valueOf(c));
                    }
                }
                else{
                    stack.push(String.valueOf(c));
                }
            }
        }
        while(!stack.IsEmpty()){
            rezultat.append(stack.top());
            stack.pop();
        }
        return rezultat.reverse();
    }

    public Calculator() {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i=0;i<10;i++)
            buttonpanel.add(digits[i]);

        for (int i=0;i<6;i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i=0;i<10;i++) {
            int finalI = i;
            digits[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    area.append(Integer.toString(finalI));
                }
            });
        }

        for (int i=0;i<6;i++){
            int finalI = i;
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (finalI == 5)
                        area.setText("");
                    else
                    if (finalI == 4) {
                        String lhs;
                        String rhs;
                        try {

                            lhs = area.getText().substring(0, area.getText().indexOf(operator + ""));
                            rhs = area.getText().substring(area.getText().indexOf(operator + "") + 1, area.getText().length());
                            switch (operator) {
                                case '+': area.append(" = " + ((Double.parseDouble(lhs) + Double.parseDouble(rhs)))); break;
                                case '-': area.append(" = " + ((Double.parseDouble(lhs) - Double.parseDouble(rhs)))); break;
                                case '/': area.append(" = " + ((Double.parseDouble(lhs) / Double.parseDouble(rhs)))); break;
                                case '*': area.append(" = " + ((Double.parseDouble(lhs) * Double.parseDouble(rhs)))); break;
                                default: area.setText(" "); break;
                            }
                        } catch (Exception e) {
                            area.setText(" !!!Probleme!!! ");
                        }
                    }
                    else {
                        area.append(oper_values[finalI]);
                        operator = oper_values[finalI].charAt(0);
                    }
                }
            });
        }
    }
}