package com.example.demo.response;

import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserResponse extends AbstractResponse {

    User user;

    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
