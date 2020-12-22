package gui;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Container;       
import static javax.swing.GroupLayout.Alignment.*; 
import java.lang.String;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class customer extends JFrame {
	static customer frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1; 
	private JTextField textField_2; 

	/**
	 * Launch the application.
	 */
	
	public customer(double totalPrice,int selected_hotel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.PINK);
		JLabel head = new JLabel("Your total Amount is: "+ totalPrice);
		head.setBounds(50,40,300,30);
		getContentPane().add(head);
		
		
		JLabel lblCustomer = new JLabel("Customer Details");
		lblCustomer.setForeground(Color.DARK_GRAY);
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(30,150,50,30);
		
		//JLabel lblDate = new JLabel("Date: ");
		//lblDate.setBounds(30,150,50,30);
		
		JLabel lblPaymode = new JLabel("Payment Mode: ");
		lblPaymode.setBounds(30,200,100,30);
		
		textField = new JTextField();
		textField.setBounds(100,150,150,30);
		
		//textField_1 = new JTextField();
		//textField_1.setColumns(10);
		
		 String choices[]={"cash","credit","gpay","debit"};        
		    JComboBox cb=new JComboBox(choices);
		    cb.setBounds(150,200,100,30);
		
		JButton btnNewButton = new JButton("Confirm Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				//String date=textField_1.getText();
				//System.out.println(name);
				//System.out.println(date);
				//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				//Date Date= format.parse(date);
				String payment_mode=cb.getItemAt(cb.getSelectedIndex()).toString();  
				
			/*int i=CustomerDetails.save(date, name,payment_mode);*/new Menu();

			
				try {
	                 Connection connection = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
	                 
	                 PreparedStatement st = (PreparedStatement) connection
	                     .prepareStatement("insert into order_details (customer_name,order_date,payment_mode,amount,hotel_id) values (?,?,?,?,?)");

	                 st.setString(1, name);
	                 st.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
	                 st.setString(3, payment_mode);	
	                 st.setDouble(4,totalPrice);
	                 st.setInt(5,selected_hotel);
	                 st.executeUpdate();
	   
	     				JOptionPane.showMessageDialog(customer.this,"Payment Successful!");
	     				frame.dispose();
			
			}
				catch (SQLException sqlException) {
	                 sqlException.printStackTrace();
	             }
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(80,300,150,50);
		getContentPane().add(btnNewButton);
		getContentPane().add(lblName);
		//contentPane.add(lblDate);
		getContentPane().add(lblPaymode);
		getContentPane().add(textField);
		//contentPane.add(textField_1);
		getContentPane().add(cb);

		
	}
}

