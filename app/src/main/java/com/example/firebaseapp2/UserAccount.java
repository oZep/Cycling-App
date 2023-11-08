package com.example.firebaseapp2;

import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    protected String username;
    protected String email;
    protected int _id;
    protected String password;
    public UserAccount(String u, String e, String p) {
        username = u;
        email = e;
        password = p;
    }

    public UserAccount(String e, String p) {
        email = e;
        password = p;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("username", username);
        result.put("email", email);
        result.put("password", password);
        return result;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
