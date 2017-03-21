package com.harsh.techinnova.rpocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by samsung on 16-Mar-17.
 */
public class Second extends Activity implements View.OnClickListener{
    CardView noti,docs,electricity,licence,payments,complaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        noti = (CardView) findViewById(R.id.noti);
        docs = (CardView) findViewById(R.id.docs);
        electricity = (CardView) findViewById(R.id.electricity);
        licence= (CardView) findViewById(R.id.licence);
        complaints = (CardView) findViewById(R.id.complaints);
        payments= (CardView) findViewById(R.id.payments);



        noti.setOnClickListener(this);
        docs.setOnClickListener(this);
        electricity.setOnClickListener(this);
        licence.setOnClickListener(this);
        payments.setOnClickListener(this);
        complaints.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.noti:
                startActivity(new Intent(Second.this,LiveNotification.class));
                break;
            case R.id.docs:
                startActivity(new Intent(Second.this,Complaints.class));
                break;
            case R.id.electricity:
                startActivity(new Intent(Second.this,History.class));
                break;
            case R.id.licence:
                startActivity(new Intent(Second.this,Licence.class));
                break;
            case R.id.payments:
                startActivity(new Intent(Second.this,MakePayment.class));
                break;
            case R.id.complaints:
                startActivity(new Intent(Second.this,ComplaintsForm.class));
                break;

        }
    }



}
