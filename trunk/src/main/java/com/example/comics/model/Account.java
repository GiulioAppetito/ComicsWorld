package com.example.comics.model;

import javafx.scene.image.Image;
import java.util.Date;

public abstract class Account extends AccountSubject {

    //profile personal
    private String firstName;
    private String lastName;
    private Date birthday;
    private String city;


    //profile basics
    private String username;
    private Image proPic;
    private String email;

    public abstract String getRole();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Image getProPic() {
        return proPic;
    }

    public void setProPic(Image proPic) {
        this.proPic = proPic;
        notifyObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void changeCredential(String credentialType, String newCredential){

        switch(credentialType){
            case "email":
                this.setEmail(newCredential);
                break;
            case "username":
                this.setUsername(newCredential);
                break;
            case "firstname":
                this.firstName = newCredential;
                break;
            case "lastname":
                this.setLastName(newCredential);
                break;
            case "city":
                this.setCity(newCredential);
                break;
            default:
                break;

        }
        notifyObservers();
    }

    public void changeProPic(Image proPic){
        this.proPic = proPic;
    }
}
