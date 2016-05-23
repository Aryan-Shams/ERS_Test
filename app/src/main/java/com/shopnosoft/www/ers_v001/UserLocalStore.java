package com.shopnosoft.www.ers_v001;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Areyan on 5/16/2016.
 */
public class UserLocalStore {
    public static  final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context contex){
        userLocalDatabase = contex.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("email",user.email);
        spEditor.putString("username",user.username);
        spEditor.putString("password",user.password);
        spEditor.putString("mobileno",user.mobileno);
        spEditor.commit();
    }

    //////Get the user login status, if login LoggedIn valuse is true from loginactivityt else initially false
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedInstatus", loggedIn);
        spEditor.commit();
    }


    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();

    }

    public User getUserLoggedIn(){
        if( userLocalDatabase.getBoolean("loggedInstatus", false) == false){
            return null;
        }

        String svdname = userLocalDatabase.getString("name","");
        String svdemail = userLocalDatabase.getString("email","");
        String svdusername = userLocalDatabase.getString("username","");
        String svdpassword = userLocalDatabase.getString("password","");
        String svdmobileno = userLocalDatabase.getString("mobileno","");

        return new User (svdname,svdemail,svdusername,svdpassword,svdmobileno);
    }




}
