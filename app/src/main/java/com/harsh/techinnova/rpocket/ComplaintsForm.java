package com.harsh.techinnova.rpocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ComplaintsForm extends Activity implements View.OnClickListener{
    EditText name,location,contact,adhaar,complain;
    Button reg;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_form);
        Firebase.setAndroidContext(this);

        name = (EditText) findViewById(R.id.name);
        location = (EditText) findViewById(R.id.location);
        contact = (EditText) findViewById(R.id.contact);
        adhaar = (EditText) findViewById(R.id.adhaar);
        complain = (EditText) findViewById(R.id.complain);
        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
                String Uname=name.getText().toString();
                String Ulocation=location.getText().toString();
                String Ucontact=contact.getText().toString();
                String Uadhaar=adhaar.getText().toString();
                String Ucomplain=complain.getText().toString();
                Firebase ref = new Firebase("https://pocketr-15434.firebaseio.com/");//firebase storage Api

                ComplainModel Uc = new ComplainModel();
                Uc.setName(Uname);
                Uc.setLocation(Ulocation);
                Uc.setContact(Ucontact);
                Uc.setAdhaar(Uadhaar);
                Uc.setComplaint(Ucomplain);
               // ref.child(Uadhaar).setValue(Uc);
                ref.child("Complaints").child(Uadhaar).setValue(Uc);
                ref.child("History").child(Uadhaar).setValue(Uc);

                Toast.makeText(getBaseContext(), "Your Complaint is Registered", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(ComplaintsForm.this,Second.class);
                startActivity(intent);


        }

    }








}
