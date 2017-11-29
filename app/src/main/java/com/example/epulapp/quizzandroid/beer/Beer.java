package com.example.epulapp.quizzandroid.beer;

public class Beer {
    public int id;
    public String name;
    public String image_url;
    public String description;
    public int tauxAlcool; // abv

    public Beer(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTauxAlcool() {
        return tauxAlcool;
    }

    public void setTauxAlcool(int tauxAlcool) {
        this.tauxAlcool = tauxAlcool;
    }

    @Override
    public String toString() {
        return name;
    }
}
