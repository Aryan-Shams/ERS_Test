package com.shopnosoft.www.ers_v001;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etfullnamereg = (EditText) findViewById(R.id.etnamereg);
        final EditText etmailreg = (EditText) findViewById(R.id.etemailreg);
        final EditText etphonenoreg = (EditText) findViewById(R.id.etphnnoreg);
        final EditText etusernamereg = (EditText) findViewById(R.id.etusrnamereg);
        final EditText etpasswordreg = (EditText) findViewById(R.id.etpasswordreg);

        final Button bRegisterinreg = (Button) findViewById(R.id.bRegisterreg);


        bRegisterinreg.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                //RegisterActivity.this.startActivity(registerIntent);


                final String name=etfullnamereg.getText().toString();
                final String email=etmailreg.getText().toString();
                final String mobileno=etphonenoreg.getText().toString();
                final String username=etusernamereg.getText().toString();
                final String password=etpasswordreg.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

////////////////////////Storing USer Data Locally/////////////////////////////////////////////
                           /*     User registeredData = new User (name,email,username,password,mobileno);
                                userLocalStore.storeUserData(registeredData);
                                userLocalStore.setUserLoggedIn(true);
                                */
//////////////////////Storing USer Data Locally/ ends////////////////////////////////////

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("User Already Exists!!! Resistration  Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(name,email,username,password,mobileno,responseListener );
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

//
                    /*for checking
                    Context context = getApplicationContext();
                    CharSequence text = name+""+email+""+mobileno+""+username+""+password;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    */
//

            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//   public void onBackPressed() {
   //     super.onBackPressed();
     //   this.finish();
    //}
}
