package com.example.network;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int id; //id поста

    @SerializedName("title")  //есть возможности
    private String postTitle;

    @SerializedName("body")
    private String postText;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostText() {
        return postText;
    }
}
