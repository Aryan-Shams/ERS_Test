package com.shopnosoft.www.ers_v001;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etusernamelgn = (EditText) findViewById(R.id.etUsernamelogin);
        final EditText etpasswordlgn = (EditText) findViewById(R.id.etPasswordlogin);
        final Button bLoginlgn = (Button) findViewById(R.id.bLoginlgn);
        final TextView registerlink = (TextView) findViewById(R.id.tvRegisterHere);

        userLocalStore = new UserLocalStore(this);
       // userLocalStore.setUserLoggedIn(false);

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                finish();

            }
        });

        bLoginlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etusernamelgn.getText().toString();
                final String password = etpasswordlgn.getText().toString();

                Response.Listener<String> responseListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");

                            if(success){
                                String lgnname = jsonresponse.getString("name");
                                String lgnemail = jsonresponse.getString("email");
                                String lgnusername = jsonresponse.getString("username");
                                String lgnpassword = jsonresponse.getString("password");
                                String lgnmobileno = jsonresponse.getString("password");

////////////////////////Storing USer Data Locally/////////////////////////////////////////////

                                User user = new User (lgnname,lgnemail,lgnusername,lgnpassword,lgnmobileno);
                                userLocalStore.storeUserData(user);
                                userLocalStore.setUserLoggedIn(true);

 //////////////////////Storing USer Data Locally/ ends////////////////////////////////////

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name",lgnname);
                                intent.putExtra("username",username);
                                intent.putExtra("email",lgnemail);
                                LoginActivity.this.startActivity(intent);
                                finish();


                            }
                            else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password,responseListner);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });




    }
    public void onBackPressed() {
        super.onBackPressed();
       finish();

    }

    ///stop back button
   /*
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

*/
}
