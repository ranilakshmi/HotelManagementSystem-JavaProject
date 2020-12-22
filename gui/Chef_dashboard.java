package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import entity.Chef;

@SuppressWarnings("serial")
public class Chef_dashboard extends JFrame {
    JTabbedPane jtp;

    public void init(Chef c) {
	this.setTitle("CHEF DASHBOARD");

	jtp = new JTabbedPane();
	// To display the name of the chef on the top of the window
	jtp.addTab("Chef Details", new StaffDetails(c));
	jtp.addTab("View Pending Orders", new PendingOrders(c));
	add(jtp);

	this.setVisible(true);
	setSize(800, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
