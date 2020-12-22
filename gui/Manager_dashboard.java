package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Manager_dashboard extends JFrame {

    public void init(entity.Manager m) {
	setTitle("Manager Dashboard");
	JTabbedPane jtp = new JTabbedPane();
	jtp.addTab("View Menu", new viewMenu());
	jtp.addTab("Staff Details", new HotelStaffDetails(m));
	jtp.addTab("View Orders", new Orders(m));
	add(jtp);
	setVisible(true);
	setSize(1200, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
