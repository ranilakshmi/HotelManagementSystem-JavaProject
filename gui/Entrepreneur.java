package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Entrepreneur extends JFrame {
    Entrepreneur() {
	JTabbedPane jtp = new JTabbedPane();
	jtp.addTab("View Branches", new ViewBranches());
	jtp.addTab("View Menu", new viewMenu());
	jtp.addTab("Staff Details", new Staff());
	add(jtp);
	//this.setVisible(true);
	setSize(1200, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	new Entrepreneur();
    }
}
