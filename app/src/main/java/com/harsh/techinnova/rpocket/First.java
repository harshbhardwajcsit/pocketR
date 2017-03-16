package com.harsh.techinnova.rpocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class First extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);



                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    Intent intent = new Intent(First.this,Second.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

}
