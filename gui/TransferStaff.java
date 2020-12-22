package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TransferStaff extends JFrame {
    JPanel p;

    TransferStaff() {

	p = new JPanel();
	add(p);
	JLabel l1 = new JLabel("SSN:");
	p.add(l1);
	JTextField t1 = new JTextField();
	t1.setColumns(10);
	p.add(t1);
	/*
	 * JLabel l2 = new JLabel("start date:"); p.add(l2); JTextField t2 = new
	 * JTextField(); p.add(t2); t2.setColumns(10); t2.setBounds(200, 30, 100, 200);
	 */
	JLabel l4 = new JLabel("Hotel_Id:");
	// l4.setBounds(20, 50, 100, 20);
	p.add(l4);
	JTextField t4 = new JTextField();
	p.add(t4);
	t4.setColumns(10);
	JButton b = new JButton("transfer");
	p.add(b);
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {

		try {
		    int id = Integer.parseInt(t4.getText());
		    String SSN = t1.getText();
		    // String City =

		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st2;
		    if (SSN.charAt(0) == 'C') {
			st2 = connection.prepareStatement("UPDATE CHEF SET HOTEL_ID=? WHERE SSN=?");
		    } else {
			st2 = connection.prepareStatement("UPDATE Manager SET HOTEL_ID=? WHERE SSN=?");
		    }
		    st2.setInt(1, id);
		    st2.setString(2, SSN);
		    st2.executeUpdate();
		    dispose();
		} catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		}
	    }
	});
	add(p);
	setVisible(true);
	setSize(1200, 400);
    }

}
