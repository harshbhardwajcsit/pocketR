package com.harsh.techinnova.rpocket;


public class LicenceModel {

    public String Name;
    public String contact;
    public String Adhaar;
    public String Request;
    public String Status;
    public String TimeStamp;

    public LicenceModel() {
      /*Blank default constructor essential for Firebase*/
    }
    // setters


    public void setName(String Name) {this.Name=Name;}
    public void setContact(String contact) {this.contact=contact;}
    public void setAdhaar(String Adhaar) {this.Adhaar=Adhaar;}
    public void setRequest(String Request) {this.Request=Request;}
    public void setDate(String Date) {this.TimeStamp=Date;}
    public void setStatus(String Status){this.Status=Status;}

}
