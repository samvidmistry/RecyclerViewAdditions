package com.example.samvidmistry.recyclerviewadditions;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class Photo extends SugarRecord {
    @SerializedName("albumId")
    private int mAlbumId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("thumbnailUrl")
    private String mThumbUrl;

    public int getAlbumId() {
        return mAlbumId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbUrl() {
        return mThumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        mThumbUrl = thumbUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
