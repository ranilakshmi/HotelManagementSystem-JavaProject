package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Menu extends JFrame {
    JFrame frame1;
    JTable table1;
    JTable table2;
    DefaultTableModel model1;
    DefaultTableModel model2;
    JLabel label1;
    JLabel label2;
    JButton btn;
    JScrollPane scroll1;
    JScrollPane scroll2;
    public double totalPrice = 0;
    public int selected_hotel;

    public Menu() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(200, 200, 800, 700);
	setTitle("Menu");
	getContentPane().setLayout(null);
	scroll1 = new JScrollPane();
	scroll1.setBounds(70, 80, 600, 200);
	getContentPane().add(scroll1);
	getContentPane().setBackground(Color.PINK);
	table1 = new JTable();
	scroll1.setViewportView(table1);
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
		case 3:
		    return Boolean.class;
		default:
		    return String.class;
		}
	    }
	};
	String columnNames[] = { "CUISINE", "ITEMS", "PRICE", "SELECT" };
	model1.setColumnIdentifiers(columnNames);
	table1.setModel(model1);
	table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table1.setFillsViewportHeight(true);
	table1.setVisible(true);
	label1 = new JLabel("<html><span style='color: black;'>MENU</span></html>");
	label1.setBounds(300, 10, 50, 30);
	getContentPane().add(label1, BorderLayout.PAGE_START);
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
	for (int i = 0; i < table1.getRowCount(); i++) {
	    model1.setValueAt(false, i, 3);
	}

	model2 = new DefaultTableModel() {

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		    return String.class;
		case 1:
		    return String.class;
		case 2:
		    return Boolean.class;
		default:
		    return String.class;
		}
	    }
	};

	label2 = new JLabel("Select a hotel");
	label2.setBounds(70, 350, 100, 30);
	getContentPane().add(label2);
	String columns[] = { "HOTEL_ID", "CITY", "SELECT" };
	table2 = new JTable();
	table2.setModel(model2);
	model2.setColumnIdentifiers(columns);
	table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	table2.setFillsViewportHeight(true);
	table2.setVisible(true);
	scroll2 = new JScrollPane();
	scroll2.setBounds(70, 400, 600, 120);
	scroll2.setViewportView(table2);
	getContentPane().add(scroll2);
	String hId = "";
	String city = "";
	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");

	    PreparedStatement st = connection.prepareStatement("select hotel_id,city from hotel");

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
		hId = rs.getString("hotel_id");
		city = rs.getString("city");
		model2.addRow(new Object[] { hId, city });
	    }
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
	for (int i = 0; i < table2.getRowCount(); i++) {
	    model2.setValueAt(false, i, 2);
	}

	btn = new JButton("Get Selected");
	btn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < table1.getRowCount(); i++) {
		    Boolean checked = Boolean.valueOf(table1.getValueAt(i, 3).toString());
		    // DISPLAY
		    if (checked) {
			double price_item = Double.parseDouble(table1.getValueAt(i, 2).toString());
			totalPrice = totalPrice + price_item;
		    }
		}
		// System.out.println(totalPrice);
		JOptionPane.showMessageDialog(null, "selected items");

		for (int i = 0; i < table2.getRowCount(); i++) {
		    Boolean checked = Boolean.valueOf(table2.getValueAt(i, 2).toString());
		    if (checked) {
			selected_hotel = Integer.parseInt(table2.getValueAt(i, 0).toString());
			// System.out.println(selected_hotel);
		    }
		}
		JOptionPane.showMessageDialog(null, "selected hotel");
		customer customer_pay = new customer(totalPrice, selected_hotel);
		customer_pay.setVisible(true);
		dispose();
	    }
	});

	// ADD BUTTON TO FORM
	btn.setBounds(200, 600, 200, 30);
	getContentPane().add(btn);
    }

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Menu frame = new Menu();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}