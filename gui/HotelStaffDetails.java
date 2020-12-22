package gui;

import java.awt.Color;
import java.awt.Point;
import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class HotelStaffDetails extends JPanel {
    HotelStaffDetails(Manager m) {
	DefaultTableModel model = new DefaultTableModel();

	JTable table = new JTable();
	String columnNames[] = { "SSN", "NAME", "CITY" };
	model.setColumnIdentifiers(columnNames);
	table.setModel(model);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	setBackground(Color.yellow);
	scroll.getViewport().setViewPosition(new Point(100, 100));

	add(scroll);
	JButton b = new JButton("Add new staff");
	add(b);
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {
		new newStaff(m);
	    }
	});
	String ssn, name, city;

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement(
		    "Select SSN,NAME,CITY from CHEF WHERE HOTEL_ID = ? UNION SELECT SSN,NAME,CITY FROM MANAGER WHERE HOTEL_ID = ?;");

	    st.setInt(1, m.getHotel_id());
	    st.setInt(2, m.getHotel_id());
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		ssn = rs.getString("SSN");
		name = rs.getString("Name");
		city = rs.getString("City");

		model.addRow(new Object[] { ssn, name, city });
	    }
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
	setSize(400, 400);
	setVisible(true);
	table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	JButton remove = new JButton("Remove");
	add(remove);
	remove.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {
		int row = table.getSelectedRow();
		String eve = table.getModel().getValueAt(row, 0).toString().toUpperCase();
		System.out.println(eve);
		// String delRow = "delete from CHEF where SSN=" + eve;

		try {
		    Connection connection = DriverManager
			    .getConnection("jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st = connection.prepareStatement("DELETE FROM CHEF WHERE SSN = ?");
		    st.setString(1, eve);
		    st.executeUpdate();
		    JOptionPane.showMessageDialog(null, "Congrats");
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, e.getMessage());
		}
		model.removeRow(table.getSelectedRow());
	    }
	});
    }

}
