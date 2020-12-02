class Employee{
    public int SSN;
    private String Name;
    private int Hotel_Id;
    private long Phone_number;

    void setName(String name){
        this.Name = name;
    }
    void setHotel_id(int id){
        this.Hotel_Id = id;
    }
    void setPhoneno(long phoneno){
        this.Phone_number = phoneno;
    }
    String getName(){
        return this.Name;
    }
    int getHotel_id(){
        return this.Hotel_Id;
    }
    long getPhoneno(){
        return this.Phone_number;
    }
}
