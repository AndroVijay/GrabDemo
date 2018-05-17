package com.example.rationalheads.vijay.grabdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 13-04-2018.
 */

public class Deals {

    @SerializedName("login")
    String login;
    @SerializedName("avatar_url")
    String avatar_url;

    public Deals(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }



    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}

