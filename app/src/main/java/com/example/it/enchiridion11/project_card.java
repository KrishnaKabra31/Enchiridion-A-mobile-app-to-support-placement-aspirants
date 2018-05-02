package com.example.it.enchiridion11;

/**
 * Created by Krishna on 4/16/2017.
 */

public class project_card {
    private String Title;
    private String Description;
    private String Reference;

    private String Image;

    public project_card()
    {

    }
    public project_card(String title, String description, String reference, String image) {
        Title = title;
        Description = description;
        Reference = reference;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }



}
