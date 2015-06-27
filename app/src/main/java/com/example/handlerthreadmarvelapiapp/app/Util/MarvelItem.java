package com.example.handlerthreadmarvelapiapp.app.Util;

import android.graphics.Bitmap;

/**
 * Created by milton on 27/06/15.
 */
public class MarvelItem {

    private String title;
    private String description;
    private Bitmap image;

    public MarvelItem(String title, String description, Bitmap image)
    {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


}
