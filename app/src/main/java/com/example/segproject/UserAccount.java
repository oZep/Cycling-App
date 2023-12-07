package com.example.segproject;

import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    protected String username;
    protected String password;

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("username", username);
        result.put("password", password);
        return result;
    }

    public String getUsername() {
        return username;
    }
    

    public String getPassword() {
        return password;
    }
}
