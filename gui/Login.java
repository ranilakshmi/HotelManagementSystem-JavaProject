package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.Chef;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Login frame = new Login();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public Login() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setBackground(Color.GRAY);
	setBounds(450, 190, 1014, 597);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JLabel lblNewLabel = new JLabel("Login");
	lblNewLabel.setForeground(Color.BLACK);
	lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
	lblNewLabel.setBounds(423, 13, 273, 93);
	contentPane.add(lblNewLabel);

	textField = new JTextField();
	textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	textField.setBounds(481, 170, 281, 68);
	contentPane.add(textField);
	textField.setColumns(10);

	passwordField = new JPasswordField();
	passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	passwordField.setBounds(481, 286, 281, 68);
	contentPane.add(passwordField);

	JLabel lblUsername = new JLabel("Username");
	lblUsername.setBackground(Color.BLACK);
	lblUsername.setForeground(Color.BLACK);
	lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
	lblUsername.setBounds(250, 166, 193, 52);
	contentPane.add(lblUsername);

	JLabel lblPassword = new JLabel("Password");
	lblPassword.setForeground(Color.BLACK);
	lblPassword.setBackground(Color.CYAN);
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
	lblPassword.setBounds(250, 286, 193, 52);
	contentPane.add(lblPassword);

	btnNewButton = new JButton("Login");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
	btnNewButton.setBounds(545, 392, 162, 73);
	btnNewButton.addActionListener(new ActionListener() {

	   // @SuppressWarnings("deprecation")
		@Override
	    public void actionPerformed(ActionEvent e) {
		String userName = textField.getText();
		System.out.println(userName);
		// System.out.println(userName);
		//@SuppressWarnings("deprecation")
		String password = passwordField.getText();
		if (userName.contentEquals("ADMIN") && password.contentEquals("IMP")) {
			dispose();
			Entrepreneur en = new Entrepreneur();
			en.setVisible(true);
		}
		else {
		try {
		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");

		    PreparedStatement st = connection
			    .prepareStatement("Select SSN from Manager UNION select SSN from CHEF where SSN=?");

		    st.setString(1, userName);
		    // st.setString(2, password);
		    ResultSet rs = st.executeQuery();

		    if (rs.next()) {
			dispose();
			JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
			if (userName.charAt(0) == 'C') {
			    Chef c = new Chef(userName);
			    Chef_dashboard chef_dashboard = new Chef_dashboard();
			    chef_dashboard.init(c);
			    chef_dashboard.setVisible(true);
			} else if (userName.charAt(0) == 'B' && userName.charAt(1) == 'M') {
			    entity.Manager m = new entity.Manager(userName);
			    Manager_dashboard BM = new Manager_dashboard();
			    BM.init(m);
			    BM.setVisible(true);
			}	
		}
		    
		    
		    else {
				JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
			 }
	
		}
		catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		}
	    }
		}
	});

	contentPane.add(btnNewButton);

	label = new JLabel("");
	label.setBounds(0, 0, 1008, 562);
	contentPane.add(label);
    }
}
