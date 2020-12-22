package entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Employee {

    private Date Date_of_join;

    public Manager(String SSN, String Name, int Hotel_id, long Phone_no, int cuisine_id, Date date_of_join) {
	super(SSN, Name, Hotel_id, Phone_no);
	this.Date_of_join = date_of_join;

    }

    public Manager(String SSN, String Name) {
	super(SSN, Name);
    }

    public Date getdate_of_join() {
	return this.Date_of_join;
    }

    public Manager(String ssn) {
	super(ssn);

	try {
	    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel Management",
		    "postgres", "chetluru01");
	    PreparedStatement st1 = connection
		    .prepareStatement("Select NAME,CITY,HOTEL_ID from  MANAGER where SSN = ?;");
	    st1.setString(1, SSN);
	    ResultSet rs = st1.executeQuery();
	    while (rs.next()) {
		this.setName(rs.getString(1));
		this.setCity(rs.getString(2));
		this.setHotel_id(rs.getInt(3));

		System.out.println("Successful");
	    }

	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}

    }

    public Manager(String SSN, String Name, int hotelid) {
	super(SSN, Name);
	this.Hotel_Id = hotelid;
    }
}
