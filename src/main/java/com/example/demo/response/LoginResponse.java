package com.example.demo.response;

import com.example.demo.model.Jwt;

public class LoginResponse extends AbstractResponse{

    Jwt jwt;

    public Jwt getJwt() {
        return jwt;
    }
    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

}
