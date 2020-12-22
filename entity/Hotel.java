package entity;

public class Hotel {
    public int Hotel_id;
    private double Expenditure_expected;
    private double Revenue_planned;
    private long Phone_Number;

    void setExpenditure(double e) {
	this.Expenditure_expected = e;
    }

    void setRevenue(double r) {
	this.Revenue_planned = r;
    }

    void setPhoneNumber(long p_no) {
	this.Phone_Number = p_no;
    }

    double getExpenditure() {
	return this.Expenditure_expected;
    }

    double getRevenue() {
	return this.Revenue_planned;
    }

    long getPhoneNo() {
	return this.Phone_Number;
    }

}
