package com.example.demo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Jwt {

    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
