package com.shopnosoft.www.ers_v001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogoutActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;

    EditText etUsernameview,etemialview;
    TextView tvWelcomeMessage;

    Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);




//Fields
        etUsernameview = (EditText) findViewById(R.id.etUsernameview);
        etemialview = (EditText) findViewById(R.id.etemialview);
        tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        logoutButton = (Button) findViewById(R.id.btLogout);

        userLocalStore = new UserLocalStore(this);

        displayUserDetailspfl();
        //////////////////////
        logoutButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                userLocalStore.clearUserData();
                                                userLocalStore.setUserLoggedIn(false);
                                                Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                                                LogoutActivity.this.startActivity(intent);
finish();
                                            }
                                        });





    }


/////////////////////////////

    private void displayUserDetailspfl(){
        User user = userLocalStore.getUserLoggedIn();
        etUsernameview.setText(user.username);
        etemialview.setText(user.email);
        String message = user.name + " Welcome to your user area";
        tvWelcomeMessage.setText(message);
    }
    //////////////////////////////


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
        LogoutActivity.this.startActivity(intent);
        finish();
    }

}
