package com.example.demo.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeployRequest extends AbstractRequest{
    String fileFrom;
    String fileTo;

    public String getFileFrom() {
        return fileFrom;
    }
    public void setFileFrom(String fileFrom) {
        this.fileFrom = fileFrom;
    }
    public String getFileTo() {
        return fileTo;
    }
    public void setFileTo(String fileTo) {
        this.fileTo = fileTo;
    }

    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
