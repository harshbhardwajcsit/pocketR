package com.harsh.techinnova.rpocket;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LiveNotification extends Activity {
    TextView textView;
    Firebase root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ntab);
        Firebase.setAndroidContext(this);
        //live notification data base link
        root=new Firebase("https://pocketr-15434.firebaseio.com/");

        textView=(TextView)findViewById(R.id.textview);


    }
    @Override
    public void onStart(){
        super.onStart();

        //link to the message in the cloud server
        Firebase nofication=root.child("Message");
        nofication.addValueEventListener(new ValueEventListener() {
            @Override

            //on reading data change
            public void onDataChange(DataSnapshot dataSnapshot) {

                textView.setText((CharSequence) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
