package com.harsh.techinnova.rpocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EmailId;
    EditText Password;
    TextView Register;
    Button Login;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

        EmailId = (EditText) findViewById(R.id.LoginEmailId);
        Password = (EditText) findViewById(R.id.Loginpassword);
        Register = (TextView) findViewById(R.id.RegisterTextView);
        Login = (Button) findViewById(R.id.LoginButton);

        Login.setOnClickListener(this);
        Register.setOnClickListener(this);

    }

    public void LoginUser(){

        final String emailid = EmailId.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)) {
            Toast.makeText(this, "Please enter Email-id", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(emailid) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Can't proceed", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
        mdatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot usersDataSnapshort: dataSnapshot.getChildren()){
                    //User user=usegetValue(User.class);
                    User user=usersDataSnapshort.getValue(User.class);
                    if(emailid.equals(user.email)&& password.equals(user.password))
                    {
                        // finish();
                        startActivity(new Intent(LoginActivity.this,Second.class));
                        break;

                    }
                    Log.d(user.email,user.password);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == Login) {
            LoginUser();
        }
        if (view == Register) {
            finish();
            startActivity(new Intent(this,RegisterActivity.class));
        }

    }
}
