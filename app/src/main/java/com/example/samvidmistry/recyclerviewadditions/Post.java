package com.example.samvidmistry.recyclerviewadditions;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class Post extends SugarRecord {
    @SerializedName("userId")
    private int mUserId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("body")
    private String mBody;

    public int getUserId() {
        return mUserId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    @Override
    public String toString() {
        return mUserId + " " + mTitle;
    }
}
