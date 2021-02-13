package com.toan.chatdemo.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private Long userId;
    private String username;
    private String token;
    private String type = "Bearer";

    public JwtResponse(Long id, String username, String token) {
        this.userId = id;
        this.username = username;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
