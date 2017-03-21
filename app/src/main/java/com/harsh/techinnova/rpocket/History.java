package com.harsh.techinnova.rpocket;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class History extends Activity {
    TextView textView;
    Firebase root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Firebase.setAndroidContext(this);
        //live notification data base link
        root = new Firebase("https://pocketr-15434.firebaseio.com/");

        textView = (TextView) findViewById(R.id.textView);


    }

    @Override
    public void onStart() {
        super.onStart();

        //link to the message in the cloud server
        Firebase nofication = root.child("History");
        nofication.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LicenceModel post = postSnapshot.getValue(LicenceModel.class);
                    textView.setText(textView.getText() + post.Request);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
