package com.example.studyproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    final static private String URL = "http://nowstart.iptime.org/~smart/BookDelete.php";
    private Map<String,String> parameters;

    public DeleteRequest(String userID, String bookID, Response.Listener<String> listener){
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
