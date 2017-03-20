package com.harsh.techinnova.rpocket;



/**
 * Created by samsung on 27-Jul-16.
 */
public class ComplainModel {



    public String Name;
    public String Location;
    public String contact;
    public String Adhaar;
    public String Complaint;


    public ComplainModel() {
      /*Blank default constructor essential for Firebase*/
    }
    // setters


    public void setName(String Name) {this.Name=Name;}
    public void setLocation(String Location) {this.Location=Location;}
    public void setContact(String contact) {this.contact=contact;}
    public void setAdhaar(String Adhaar) {this.Adhaar=Adhaar;}
    public void setComplaint(String Complaint) {this.Complaint=Complaint;}



}
