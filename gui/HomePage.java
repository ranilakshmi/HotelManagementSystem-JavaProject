package gui;

 

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

import javax.swing.JButton;
import javax.swing.JFrame;

 

@SuppressWarnings("serial")
public class HomePage extends JFrame {
    JButton b1, b2, b3;

 

    HomePage() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("HOMEPAGE");
    getContentPane().setBackground(Color.CYAN);
    b1 = new JButton("CUSTOMER");
    b1.setBounds(300, 100, 200, 50);

 

    add(b1);
    b2 = new JButton("EMPLOYEE");
    b2.setBounds(300, 200, 200, 50);

 

    add(b2);
    b3 = new JButton("ENTREPRENEUR");
    b3.setBounds(300, 300, 200, 50);

 

    add(b3);
    setSize(800, 600);
    setLayout(null);
    setVisible(true);
    b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        gui.Menu menu = new gui.Menu();
        menu.setVisible(true);
        }
    });
    b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        Login login = new Login();
        login.setVisible(true);
        }
    });
    b3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        Login login = new Login();
        login.setVisible(true);
        }
    });
    }

 

    public static void main(String[] args) {
    new HomePage();
    }
}