package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class viewMenu extends JPanel {
    JTable table1;
    DefaultTableModel model1;
    JLabel label1;
    JLabel label2;
    JButton btn;

    public viewMenu() {
	setBounds(200, 200, 800, 700);
	table1 = new JTable();
	add(table1);
	JScrollPane scroll = new JScrollPane(table1);
	scroll.setBounds(70,50,200,200);
	scroll.getViewport().setViewPosition(new Point(100, 100));
	add(scroll);

	model1 = new DefaultTableModel() {
	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		    return String.class;
		case 1:
		    return String.class;
		case 2:
		    return String.class;

		default:
		    return String.class;
		}
	    }
	};
	String columnNames[] = { "CUISINE", "ITEMS", "PRICE" };
	model1.setColumnIdentifiers(columnNames);
	table1.setModel(model1);
	table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table1.setFillsViewportHeight(true);
	table1.setVisible(true);
	label1 = new JLabel("<html><span style='color: black;'>MENU</span></html>");
	label1.setBounds(300, 10, 50, 30);
	add(label1, BorderLayout.PAGE_START);
	setBackground(Color.lightGray);

	String cuisine_name = "";
	String item_name = "";
	String price = "";

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement(
		    "select cuisine_name,item_name,price from food_item inner join cuisine on cuisine.cuisine_id = food_item.cuisine_id order by cuisine_name");

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		cuisine_name = rs.getString("cuisine_name");
		item_name = rs.getString("item_name");
		price = rs.getString("price");
		model1.addRow(new Object[] { cuisine_name, item_name, price });
	    }
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}

    }
}
