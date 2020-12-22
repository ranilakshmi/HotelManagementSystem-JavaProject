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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewBranches extends JPanel {
    int id;
    double rev, exp;
    String city;
    long ph;
    Date start;
    public DefaultTableModel model;

    ViewBranches() {
	model = new DefaultTableModel();

	JTable table = new JTable();
	String columnNames[] = { "HOTEL_ID", "CITY", "START_DATE", "EXPENDITURE_EXPECTED", "REVENUE_PLANNED", "PH_NO" };
	model.setColumnIdentifiers(columnNames);
	table.setModel(model);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	setBackground(Color.orange);
	
	scroll.getViewport().setViewPosition(new Point(100, 100));

	add(scroll);
	JButton b = new JButton("Add new Branch");
	add(b);
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {
		newBranch frame = new newBranch();
		frame.setVisible(true);
		frame.setSize(600,600);
		//dispose();
	    }
	});

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement("Select * from HOTEL;");

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		id = rs.getInt("HOTEL_ID");
		city = rs.getString("CITY");
		start = rs.getDate("start_date");
		exp = rs.getDouble("expenditure_expected");
		rev = rs.getDouble("revenue_planned");
		ph = rs.getLong("phone_number");

		model.addRow(new Object[] { id, city, start, exp, rev, ph });
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
		String eve = table.getModel().getValueAt(row, 0).toString();
		String delRow = "delete from hotel where hotel_id=" + eve;
		try {
		    Connection connection = DriverManager.getConnection(
			    "jdbc:postgresql://localhost:5432/Hotel Management", "postgres", "chetluru01");
		    PreparedStatement st = connection.prepareStatement(delRow);
		    st.executeUpdate();
		    JOptionPane.showMessageDialog(null, "You removed a branch details");
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, e.getMessage());
		}
		model.removeRow(table.getSelectedRow());
	    }
	});
    }
}
