package com.shopnosoft.www.ers_v001;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Areyan on 5/16/2016.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://androidlogin.shopnosoft.com/register.php";
   // private static final String REGISTER_REQUEST_URL = "http://192.168.100.2/androidlogin/register.php";

    private Map<String, String> params;

    public RegisterRequest(String name, String email, String username, String password, String mobileno, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("name",name);
        params.put("email",email);
        params.put("username",username);
        params.put("password",password);
        params.put("mobileno",mobileno);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
