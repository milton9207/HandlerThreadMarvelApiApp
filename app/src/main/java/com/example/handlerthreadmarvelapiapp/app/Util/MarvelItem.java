package com.example.handlerthreadmarvelapiapp.app.Util;

import android.graphics.Bitmap;

/**
 * Created by milton on 27/06/15.
 */
public class MarvelItem {

    private String title;
    private String description;
    private String imageURL;
    private Bitmap image;

    public MarvelItem(String title, String description, Bitmap image)
    {
        this.title = title;
        this.description = description;
        this.image = image;
    }
    public MarvelItem(String title, String description, String imageURL)
    {
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }
    public MarvelItem(String title, String description)
    {
        this.title = title;
        this.description = description;
        this.image = null;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }
}
