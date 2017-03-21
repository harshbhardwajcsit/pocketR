package com.harsh.techinnova.rpocket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Formatter;

public class MakePayment extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener  {

    Button reg;
    EditText name,contact,eid;
    protected String my_app_id;
    protected String my_api_key;
    protected String my_host;
    protected Context my_context;


    public void setAuthentication(Context ctx, String app_id, String api_key) {
        my_context = ctx;
        my_app_id = app_id;
        my_api_key = api_key;
        my_host = "http://emitrauat.rajasthan.gov.in/payments/v1/init";

    }

    private Spinner spinner1;
    private static final String[] paths = {"Bonafied", "Driving Licence", "Commercial licence", "Passport"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_types);
        Firebase.setAndroidContext(this);



        spinner1 = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MakePayment.this,
                android.R.layout.simple_spinner_item, paths);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        name = (EditText) findViewById(R.id.name);
        eid = (EditText) findViewById(R.id.eid);
        contact = (EditText) findViewById(R.id.contact);
        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);

    }


    public String checksum(final String toBeEncryptString){
        if (toBeEncryptString == null) {
            throw new IllegalArgumentException("To be encrypt string must not be null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toBeEncryptString.getBytes());
            byte byteData[] = md.digest();
            return byteArray2Hex(byteData);
        } catch (Exception ex) {
            return ex.getLocalizedMessage();
        }
    }

    public String byteArray2Hex(byte[] bytes) {
        Formatter formatter = null;
        try {
            formatter = new Formatter();
            byte[] arrayOfByte = bytes;
            int j = bytes.length;
            for (int i = 0; i < j; i++) {
                byte b = arrayOfByte[i];
                formatter.format("%02x", new Object[] { Byte.valueOf(b) });
            }
            return formatter.toString();
        } finally {
            if(formatter!=null){
                 formatter.close();

            }
        }
    }





    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
                String PRN = "AT56AU8799102W";
                String MERCHANTCODE="HACKATHON2017";
                String REQTIMESTAMP="20160623132359958";
                String AMOUNT="2000";
                String SUCCESSURL="https://console.firebase.google.com/";
                String FAILUREURL="https://console.firebase.google.com/";
                String CANCELURL="https://console.firebase.google.com/";
                String Aname=name.getText().toString();
                String Acontact=contact.getText().toString();
                String Aeid=eid.getText().toString();
                String text1=spinner1.getSelectedItem().toString();
                String CHECKSUMKEY = "#2&[W<nJ*K\"xO_z";
                String s=MERCHANTCODE+"|"+PRN+"|"+AMOUNT+"|"+CHECKSUMKEY;


                try {
                    recognize(MERCHANTCODE,PRN,REQTIMESTAMP,AMOUNT,SUCCESSURL,FAILUREURL,CANCELURL,Aname,Acontact,Aeid,text1,checksum(s));

                } catch (JSONException e){}
                catch (Exception e){}
        }
    }


    public void recognize(String Mcode,
                          String prn,
                          String reqtimestamp,
                          String amount,
                          String successurl,
                          String failureurl,
                          String cancelurl,
                          String Aname,
                          String Acontact,
                          String Aeid,
                          String text,
                          String csum


    )  throws JSONException, UnsupportedEncodingException {

        AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Firebase ref = new Firebase("https://pocketr-15434.firebaseio.com/");

                String responseString = new String(response);
                String responseCode = String.valueOf(statusCode);
                ref.setValue(responseString);
                Toast.makeText(getBaseContext(),"success", Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), responseString, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), responseCode, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                String responseString = new String(errorResponse);
                String responseCode=String.valueOf(statusCode);
                Toast.makeText(getBaseContext(),"False", Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),responseString, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),responseCode, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }

        };

        JSONObject jsonParams = new JSONObject();
        RequestParams params=new RequestParams();

        params.put("MERCHANTCODE", Mcode);
        params.put("PRN", prn);
        params.put("REQTIMESTAMP", reqtimestamp);
        params.put("AMOUNT", amount);
        params.put("SUCCESSURL", successurl);
        params.put("FAILUREURL", failureurl);
        params.put("CANCELURL", cancelurl);
        params.put("USERNAME",Aname );
        params.put("USERMOBILE", Acontact);
        params.put("USEREMAIL", Aeid);
        params.put("PURPOSE", text);
        params.put("CHECKSUM",csum);

        StringEntity entity = new StringEntity(jsonParams.toString());
        client.post(my_context, "http://emitrauat.rajasthan.gov.in/payments/v1/init", (HttpEntity) params,"application/json", responseHandler);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
