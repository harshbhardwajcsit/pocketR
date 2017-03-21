package com.harsh.techinnova.rpocket;

/**
 * Created by Dell on 20-Mar-17.
 */
public class User {
    public String name;
    public String email;
    public String aadharId;
    public String contact;
    public String password;


    public User()
    {

    }
    public User(String name,String email,String password,String aadharId,String contact)
    {
        this.email=email;
        this.password=password;
        this.aadharId=aadharId;
        this.contact=contact;
        this.name=name;
    }
}
