package mx.iteso.sergio.emt_ad;

/**
 * Created by Sergio on 19/04/2016.
 */
public class Donator {
    private String UserName;
    protected String Password;
    private String Name;
    private String SurName;
    private String SurName2;



    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getSurName2() {
        return SurName2;
    }

    public void setSurName2(String surName2) {
        SurName2 = surName2;
    }
}
