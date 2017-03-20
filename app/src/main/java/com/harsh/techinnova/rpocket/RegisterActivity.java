package com.harsh.techinnova.rpocket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EmailId, Name, Contact;
    EditText Password;
    Button Register;
    EditText Aadhar;
    ProgressDialog Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EmailId = (EditText) findViewById(R.id.RegEmailId);

        Password = (EditText) findViewById(R.id.Regpassword);
        Aadhar = (EditText) findViewById(R.id.aadharid);
        Register = (Button) findViewById(R.id.RegisterButton);
        Register.setOnClickListener(this);
        Name = (EditText) findViewById(R.id.name);
        Contact = (EditText) findViewById(R.id.contact);
        Loading=new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {
        if (v == Register) {
            register();
        }
    }

    /* Loading=new ProgressDialog(this);

     EmailId=(EditText)findViewById(R.id.RegEmailId);

     Password=(EditText)findViewById(R.id.Regpassword);

     Register=(Button)findViewById(R.id.RegisterButton);
     Register.setOnClickListener(this);

 }*/
    public void register() {
        String emailid = EmailId.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String aadhar = Aadhar.getText().toString().trim();
        String name = Name.getText().toString().trim();
        String contact = Contact.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)) {
            Toast.makeText(this, "Please enter Email-id", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contact)|| contact.length()!=10) {
            Toast.makeText(this, "Please enter Valid Contact", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aadhar)|| aadhar.length()!=12) {
            Toast.makeText(this, "Please enter Aadhar number", Toast.LENGTH_SHORT).show();
            return;
        }
        Loading.setMessage("Registering user");
        Loading.show();

        User user = new User(name, emailid, password, aadhar, contact);
        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference("users");
        String userId = mdatabase.push().getKey();
        mdatabase.child(userId).setValue(user);

        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}
