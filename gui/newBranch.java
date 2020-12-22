package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class newBranch extends JFrame {

    newBranch() {
	JLabel l1 = new JLabel("Enter ID:");
	l1.setBounds(30,70,80,30);
	getContentPane().add(l1);
	JTextField t1 = new JTextField();
	t1.setBounds(120,70,100,30);
	getContentPane().add(t1);
	JLabel l3 = new JLabel("Select city:");
	l3.setBounds(30,120,100,30);
	getContentPane().add(l3);
	JTextField t3 = new JTextField();
	t3.setBounds(150,120,100,30);
	getContentPane().add(t3);
	JLabel l4 = new JLabel("revenue_planned:");
	l4.setBounds(30, 180, 100, 30);
	getContentPane().add(l4);
	JTextField t4 = new JTextField();
	t4.setBounds(150, 180, 100, 30);
	getContentPane().add(t4);
	JLabel l5 = new JLabel("expenditure_expected:");
	l5.setBounds(30, 230, 150, 30);
	getContentPane().add(l5);
	JTextField t5 = new JTextField();
	t5.setBounds(200, 230, 150, 30);
	getContentPane().add(t5);
	JLabel l2 = new JLabel("Ph_no");
	l2.setBounds(30, 280, 50, 30);
	getContentPane().add(l2);
	JTextField t2 = new JTextField();
	t2.setBounds(100, 280, 150, 30);
	getContentPane().add(t2);
	// t4.setBounds(200, 50, 100, 200);
	// setVisible(true);
	// setSize(400, 400);
	JComboBox state = new JComboBox();
	JComboBox city = new JComboBox();
	getContentPane().add(state);
	state.setBounds(30,350,80,30);
	getContentPane().add(city);
	city.setBounds(30,400,80,30);
	
	getContentPane().setLayout(null);
	
	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");
	    PreparedStatement st1 = connection.prepareStatement("Select DISTINCT STATE from Location;");
	    ResultSet rs = st1.executeQuery();
	    while (rs.next()) {
		String s = rs.getString("State");
		state.addItem(s);
	    }
	    add(state);
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}

	state.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		try {

		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st2 = connection.prepareStatement("Select City from Location WHERE STATE = ?");

		    st2.setString(1, state.getSelectedItem().toString());
		    ResultSet rs2 = st2.executeQuery();
		    city.removeAllItems();
		    while (rs2.next()) {
			String c = rs2.getString("City");
			city.addItem(c);
			
		    }
		    add(city);
		} catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		}
	    }
	});

	JButton b = new JButton("Add");
	b.setBounds(50,450,80,30);
	getContentPane().add(b);
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {

		try {
		    int id = Integer.parseInt(t1.getText());
		    //String City = t3.getText();
		    double exp = Double.parseDouble(t5.getText());
		    double rev = Double.parseDouble((t4.getText()));
		    long ph = Long.parseLong((t2.getText()));
		    String City = city.getItemAt(city.getSelectedIndex()).toString();

		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st2 = connection.prepareStatement("INSERT INTO HOTEL VALUES(?,?,?,?,?,?)");

		    st2.setInt(1, id);
		    st2.setString(2, City);
		    st2.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
		    st2.setDouble(4, exp);
		    st2.setDouble(5, rev);
		    st2.setLong(6, ph);
		    st2.executeUpdate();
		    //model.addRow(new Object[] { cuisine_name, item_name, price });
		    dispose();
		    Entrepreneur e = new Entrepreneur();
		    e.setVisible(true);
		    
		} catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		}
	    }
	});

    }

}
