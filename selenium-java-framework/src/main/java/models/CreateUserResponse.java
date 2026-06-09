package models;

public class CreateUserResponse {
    private String first_name;
    private String last_name;
    private int id;

    public void setFirstName(String fname){
        this.first_name = fname;
    }

    public String getFirstName(){
        return first_name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setLastName(String lname){
        this.last_name = lname;
    }

    public String getLastName(){
        return last_name;
    }
}
