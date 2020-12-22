package entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chef extends Employee {
    int Cuisine_id;

    public Chef(String SSN, String Name, int Hotel_id, long Phone_no, int cuisine_id) {
	super(SSN, Name, Hotel_id, Phone_no);
	this.Cuisine_id = cuisine_id;

    }

    public Chef(String SSN, String Name) {
	super(SSN, Name);
    }

    public Chef(String ssn) {
	this.SSN = ssn;
	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");
	    PreparedStatement st1 = connection
		    .prepareStatement("Select NAME,CITY,HOTEL_ID,CUISINE_ID from CHEF where SSN = ?;");
	    st1.setString(1, ssn);
	    ResultSet rs = st1.executeQuery();
	    while (rs.next()) {
		this.setName(rs.getString(1));
		this.setCity(rs.getString(2));
		this.setHotelid(rs.getInt(3));
		this.setCuisineid(rs.getInt(4));
		// System.out.println("Successful");
	    }

	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
    }

    private void setHotelid(int int1) {
	this.Hotel_Id = int1;

    }

    public void setCuisineid(int id) {
	this.Cuisine_id = id;
    }

    public int getCuisineid() {
	return Cuisine_id;
    }

    public Date getDate_of_join() {
	return this.Date_of_join;
    }

}
