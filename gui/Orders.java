package gui;

import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Orders extends JPanel {
    Orders(entity.Manager m) {
	DefaultTableModel model = new DefaultTableModel();

	JTable table = new JTable();
	String columnNames[] = { "ORDER NO", "ITEMS" };
	model.setColumnIdentifiers(columnNames);
	table.setModel(model);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table.setFillsViewportHeight(true);
	JScrollPane scroll = new JScrollPane(table);
	setBackground(Color.ORANGE);
	scroll.getViewport().setViewPosition(new Point(100, 100));

	add(scroll);
	String order_no = "";
	String item_name = "";

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement(
		    "Select Order_no,Item_Name from FOOD_ITEM NATURAL JOIN MANAGER NATURAL JOIN ORDER_DETAILS WHERE MANAGER.SSN = ? AND ORDER_DETAILS.HOTEL_ID = ? ORDER BY ORDER_DATE DESC");

	    st.setString(1, m.SSN);
	    st.setInt(2, m.getHotel_id());
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		order_no = Integer.toString(rs.getInt("order_no"));
		item_name = rs.getString("item_name");

		model.addRow(new Object[] { order_no, item_name });
	    }
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
	setSize(400, 400);
	setVisible(true);
    }
}