package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Chef;

@SuppressWarnings("serial")
public class StaffDetails extends JPanel {

    StaffDetails(Chef c) {
	JLabel name = new JLabel(c.getName());
	name.setBounds(30,30,150,30);
	JLabel ssn = new JLabel(c.SSN);
	JLabel hotelid = new JLabel("Hotel ID:" + Integer.toString(c.getHotel_id()));
	JLabel cuisineid = new JLabel("Cuisine ID:" + Integer.toString(c.getCuisineid()));
	setBackground(Color.yellow);
	
	setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
      //reset to default  
    gbc.weighty = 1.0;   //request any extra vertical space  
//bottom of space  
    //gbc.insets = new Insets(10,0,0,0);  //top padding  
    
	add(name,gbc);
	add(ssn,gbc);
	add(hotelid,gbc);
	add(cuisineid,gbc);

	
    }

}
