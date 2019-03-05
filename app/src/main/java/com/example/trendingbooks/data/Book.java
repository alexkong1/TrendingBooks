package com.example.trendingbooks.data;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Book {

    private String id;
    private String title;
    @SerializedName("author_firstname")
    private String authorFirstname;
    @SerializedName("author_lastname")
    private String authorLastname;
    @Nullable
    @SerializedName("marketing_message")
    private String marketingMessage;
    private String synopsis;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public String getMarketingMessage() {
        return marketingMessage;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
