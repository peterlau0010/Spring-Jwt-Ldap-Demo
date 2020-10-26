package com.example.demo.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AbstractRequest {

    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
