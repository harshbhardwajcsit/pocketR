package com.harsh.techinnova.rpocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Licence extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    EditText name, adhaar, contact;
    private Spinner spinner1;
    Button reg;
    private static final String[] paths = {"Bonafied", "Driving Licence", "Commercial licence", "Passport"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authnticate);
        Firebase.setAndroidContext(this);


        spinner1 = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Licence.this,
                android.R.layout.simple_spinner_item, paths);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        adhaar = (EditText) findViewById(R.id.adhaar);
        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);
        String text1 = spinner1.getSelectedItem().toString();

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
                String text1=spinner1.getSelectedItem().toString();
                String Student_name=name.getText().toString();
                String Contact=contact.getText().toString();
                String Adhaar=adhaar.getText().toString();
                String status="False";
                LicenceModel lm=new LicenceModel();
                lm.setName(Student_name);
                lm.setContact(Contact);
                lm.setAdhaar(Adhaar);
                lm.setRequest(text1);
                lm.setStatus(status);
                Firebase ref = new Firebase("https://pocketr-15434.firebaseio.com/");//firebase storage Api
                ref.child("Licences").child(Adhaar).setValue(lm);

                Toast.makeText(getBaseContext(), "Your Request is Registered", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Licence.this,Second.class);
                startActivity(intent);

        }
    }

}