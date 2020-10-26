package com.example.demo.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AbstractResponse {

    private int errorCode;

    private String errorMsg;

//    private String msgId;

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
//    public String getMsgId() {
//        return msgId;
//    }
//    public void setMsgId(String msgId) {
//        this.msgId = msgId;
//    }
    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
