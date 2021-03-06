package com.example.epulapp.quizzandroid.beer;

import android.graphics.Bitmap;

import java.util.Observable;

/**
 * A class to save <g>beer</g> properties
 */
public class Beer extends Observable {
    private int id;
    private String name;
    private String image_url;
    private String description;
    private String brewers_tips;
    private String first_brewed;
    private String contributed_by;
    private float abv; // Taux alcool
    private Bitmap image;

    public Beer() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public float getAbv() {
        return abv;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;

        this.setChanged();
        this.notifyObservers();
    }

    public String getBrewers_tips() {
        return brewers_tips;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public String getContributed_by() {
        return contributed_by;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Beer) {
            if (((Beer) obj).getId() == this.id) {
                return true;
            }
        }
        return super.equals(obj);
    }
}
