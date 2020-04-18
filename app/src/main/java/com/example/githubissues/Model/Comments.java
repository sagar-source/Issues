package com.example.githubissues.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("author_association")
    @Expose
    private String author;


    public String getBody() {
        return body;
    }

    public String getAuthorName() {
        return author;
    }
}
