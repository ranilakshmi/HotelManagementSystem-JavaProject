package entity;

import java.sql.Date;

public class Employee {
    public String SSN;
    protected String Name;
    protected String city;
    protected int Hotel_Id;
    protected long Phone_number;
    protected Date Date_of_join;

    public void setCity(String city) {
	this.city = city;
    }

    public String getCity() {
	return city;
    }

    public void setSSN(String ssn) {
	this.SSN = ssn;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public void setHotel_id(int id) {
	this.Hotel_Id = id;
    }

    public void setPhoneno(long phoneno) {
	this.Phone_number = phoneno;
    }

    public String getName() {
	return this.Name;
    }

    public int getHotel_id() {
	return this.Hotel_Id;
    }

    public long getPhoneno() {
	return this.Phone_number;
    }

    public Employee() {
    }

    public Employee(String SSN) {
	this.SSN = SSN;
    }

    public Employee(String SSN, String Name) {
	this.SSN = SSN;
	this.Name = Name;
    }

    public Employee(String SSN, String Name, int Hotel_id, long Phone_no) {
	this.SSN = SSN;
	this.Name = Name;
	this.Hotel_Id = Hotel_id;
	this.Phone_number = Phone_no;
    }
}
