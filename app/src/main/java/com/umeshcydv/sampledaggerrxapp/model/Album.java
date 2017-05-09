package com.umeshcydv.sampledaggerrxapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 17790 on 26/04/17.
 */

public class Album implements Serializable{
    @Expose
    private String title;

    @Expose
    @SerializedName("artist")
    private String artistName;

    @Expose
    @SerializedName("url")
    private String shoppingSiteUrl;

    @Expose
    @SerializedName("image")
    private String imageUrl;

    @Expose
    @SerializedName("thumbnail_image")
    private String thumbnailImage;

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getShoppingSiteUrl() {
        return shoppingSiteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }
}
