package com.example.it.enchiridion11;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Krishna on 4/18/2017.
 */
@IgnoreExtraProperties

public class Blog {
    private String Title;
   private String Desc;
    private String Email;
    private String Key;
   private String Comment;

   public String getComment() {
        return Comment;
    }

   public void setComment(String comment) {
        Comment = comment;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Blog(String title, String desc, String email, String key,String comment) {
        Title = title;
        Desc = desc;
        Email = email;
        Key=key;
        Comment=comment;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Blog(){

    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
