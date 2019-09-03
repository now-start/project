package com.example.studyproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://nowstart.iptime.org/~smart/UserRegister.php";
    private Map<String,String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userMajor, String userEmail,String userName, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userGender",userGender);
        parameters.put("userMajor",userMajor);
        parameters.put("userEmail",userEmail);
        parameters.put("userName",userName);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
