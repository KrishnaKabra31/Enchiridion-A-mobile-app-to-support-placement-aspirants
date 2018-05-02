package com.example.it.enchiridion11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Postfr extends AppCompatActivity{

    Button mPost;
    DatabaseReference mDataBase;
    EditText mPostDescription;
    EditText mPostTitle;
    ProgressDialog mProgressBar;
    FirebaseUser firebaseUser;
    String string;
    String s;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfr);

        mPostTitle=(EditText)findViewById(R.id.Title);
        mPostDescription = (EditText)findViewById(R.id.Description);
        mPost = (Button)findViewById(R.id.Post);
        mProgressBar=new ProgressDialog(this);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        string=firebaseUser.getEmail().toString();

        // final StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();
        mDataBase= FirebaseDatabase.getInstance().getReference().child("Blog");

        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setMessage("Posting...");
                mProgressBar.show();
                String title = mPostTitle.getText().toString().trim();
                String describe=mPostDescription.getText().toString().trim();
                if(!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(describe)){
                    DatabaseReference newPost=mDataBase.push();
                    s=newPost.getKey();
                    newPost.child("Title").setValue(title);
                    newPost.child("Desc").setValue(describe);
                    newPost.child("Email").setValue(string);
                    newPost.child("Key").setValue(s);
                  //  newPost.child("Comment").setValue("");
                    mProgressBar.dismiss();
                }

               startActivity(new Intent(Postfr.this,Forum.class));
            }
        });
    }



}
