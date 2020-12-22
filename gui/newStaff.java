package gui;
import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class newStaff extends JFrame {
  

    newStaff(Manager m) {
	
	JLabel l1 = new JLabel("Enter SSN:");
	l1.setBounds(30,70,80,30);
	getContentPane().add(l1);
	JTextField t1 = new JTextField();
	t1.setBounds(120,70,100,30);
	getContentPane().add(t1);
	JLabel l2 = new JLabel("Enter Name:");
	l2.setBounds(30,120,100,30);
	getContentPane().add(l2);
	JTextField t2 = new JTextField();
	t2.setBounds(150,120,100,30);
	getContentPane().add(t2);
	JLabel l3 = new JLabel("Cuisine Id:");
	l3.setBounds(30, 180, 100, 30);
	getContentPane().add(l3);
	String choices[]={"101","102","103","104","105"};        
    JComboBox cb=new JComboBox(choices);
	cb.setBounds(150, 180, 100, 30);
	getContentPane().add(cb);
	int id=Integer.parseInt(cb.getItemAt(cb.getSelectedIndex()).toString());  
	JLabel l4 = new JLabel("Phone Number:");
	l4.setBounds(30, 230, 150, 30);
	getContentPane().add(l4);
	JTextField t4 = new JTextField();
	t4.setBounds(200, 230, 150, 30);
	getContentPane().add(t4);
	JLabel l5 = new JLabel("City");
	String cities[]={"VIJAYAWADA","HYDERABAD","KOLLAM","VISAKHAPATNAM","WARANGAL"};        
    JComboBox cb2=new JComboBox(cities);
	l5.setBounds(30,280,100,30);
	getContentPane().add(cb2);
	cb2.setBounds(150,280,100,30);
	getContentPane().add(l5);
	String city = cb2.getItemAt(cb.getSelectedIndex()).toString();
	JButton b = new JButton("Add");
	b.setBounds(50,330,80,30);
	getContentPane().add(b);
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {

		try {

		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st = connection.prepareStatement("INSERT INTO CHEF VALUES(?,?,?,?,?,?,?)");
		    st.setString(1, t1.getText());
		    st.setString(2, t2.getText());
		    st.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
		    st.setInt(4, id);
		    st.setLong(5, Long.parseLong((t4.getText())));
		    st.setString(6,city );
		    st.setInt(7, m.getHotel_id());
		    st.executeUpdate();
		    dispose();
		    Manager_dashboard mdb= new Manager_dashboard();
		    mdb.init(m);
		    
		} catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		}
	    }
	});
	getContentPane().setLayout(null);
	setVisible(true);
	setSize(800, 400);
    }
}
