package com.example.it.enchiridion11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class projectshare extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST=1;


    private EditText title;
    private EditText discription;
    private EditText reference;

    private Button share;

    private Uri mImageUri=null;

    private StorageReference mStorage;

    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectshare2);

        mSelectImage=(ImageButton)findViewById(R.id.idselectimage);

        title=(EditText)findViewById(R.id.idtitle);
        discription=(EditText)findViewById(R.id.iddescription);
        reference=(EditText)findViewById(R.id.idreference);

        share=(Button)findViewById(R.id.idshare);

        mStorage= FirebaseStorage.getInstance().getReference();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Project");


        mProgress=new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();

            }
        });
    }

    private void startPosting() {
        mProgress.setMessage("Sharing to Projects....");
        mProgress.show();
        final String title_val=title.getText().toString().trim();
        final String descri_val=discription.getText().toString().trim();
        final String reference_val=reference.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(descri_val)&&!TextUtils.isEmpty(reference_val)&& mImageUri!=null)
        {
            StorageReference filepath=mStorage.child("Project_Image").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl=taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost=mDatabase.push();

                    newPost.child("Title").setValue(title_val);
                    newPost.child("Description").setValue(descri_val);
                    newPost.child("Reference").setValue(reference_val);
                    newPost.child("Image").setValue(downloadUrl.toString());
                    mProgress.dismiss();
                    startActivity(new Intent(projectshare.this,project.class));
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK);
        {
            try
            {
                mImageUri=data.getData();
            }
            catch (Exception e){}
            mSelectImage.setImageURI(mImageUri);
        }
    }


}
