package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
    @Autowired
    private Dbservice DB;
    private String email;
    private String password;
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}
