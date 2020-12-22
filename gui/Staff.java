package gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Staff extends JPanel {

    Staff() {
	DefaultTableModel model = new DefaultTableModel();

	JTable table = new JTable();
	String columnNames[] = { "SSN", "NAME", "CITY", "HOTEL_ID" };
	model.setColumnIdentifiers(columnNames);
	table.setModel(model);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	setBackground(Color.YELLOW);
	scroll.getViewport().setViewPosition(new Point(100, 100));

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement(
		    "Select SSN,NAME,CITY,HOTEL_ID from CHEF  UNION SELECT SSN,NAME,CITY,HOTEL_ID FROM MANAGER;");

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		String ssn = rs.getString("SSN");
		String name = rs.getString("Name");
		String city = rs.getString("City");
		int hotel_id = rs.getInt("hotel_id");

		model.addRow(new Object[] { ssn, name, city, hotel_id });
	    }
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
	JButton b = new JButton("Transfer Staff");
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {
		new TransferStaff();
	    }
	});
	add(table);
	add(b);
	setSize(1200, 400);
	setVisible(true);
    }
}