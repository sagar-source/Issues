package com.example.githubissues.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issues  {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("body")
    @Expose
    private String body;

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }
}
