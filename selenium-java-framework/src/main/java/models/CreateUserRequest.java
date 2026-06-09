package models;

public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String fname){
        this.firstName = fname;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lname){
        this.lastName = lname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String mail){
        this.email = mail;
    }


}
