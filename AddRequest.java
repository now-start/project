package com.example.studyproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest {
    final static private String URL = "http://nowstart.iptime.org/~smart/BookAdd.php";
    private Map<String,String> parameters;

    public AddRequest(String userID, String bookID, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("bookID",bookID);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
