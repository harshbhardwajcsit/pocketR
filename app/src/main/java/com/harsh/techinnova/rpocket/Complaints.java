package com.harsh.techinnova.rpocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Complaints extends Activity implements View.OnClickListener {
    CardView c1,c2,c3,c4,c5,c6;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctab);



        c1 = (CardView) findViewById(R.id.c1);
        c2 = (CardView) findViewById(R.id.c2);
        c3 = (CardView) findViewById(R.id.c3);
        c4= (CardView) findViewById(R.id.c4);
        c5 = (CardView) findViewById(R.id.c5);
        c6= (CardView) findViewById(R.id.c6);



        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.c1:
                startActivity(new Intent(Complaints.this,ComplaintsForm.class));
                break;
            case R.id.c2:
                startActivity(new Intent(Complaints.this,ComplaintsForm.class));
                break;
            case R.id.c3:
                startActivity(new Intent(Complaints.this,ComplaintsForm.class));
                break;
            case R.id.c4:
                startActivity(new Intent(Complaints.this,ComplaintsForm.class));
                break;
            case R.id.c5:
                startActivity(new Intent(Complaints.this,ComplaintsForm .class));
                break;
            case R.id.c6:
                startActivity(new Intent(Complaints.this,ComplaintsForm.class));
                break;

        }
    }


}
