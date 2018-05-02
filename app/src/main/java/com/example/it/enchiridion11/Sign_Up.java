package com.example.it.enchiridion11;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends Activity {
    private Button ca;
    EditText name,email,pwd,rpwd,mob;
    String Name,Email,Pwd,Rpwd,Mob;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference().child("Users");


        ca=(Button)findViewById(R.id.create);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        pwd=(EditText)findViewById(R.id.pwd);
        rpwd=(EditText)findViewById(R.id.rpwd);
        mob=(EditText)findViewById(R.id.mob);
        progress=new ProgressDialog(this);
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    private void startRegister() {
        Name=name.getText().toString().trim();
        Email=email.getText().toString().trim();
        Pwd=pwd.getText().toString().trim();
        Rpwd=rpwd.getText().toString().trim();
        Mob=mob.getText().toString().trim();
        if(!TextUtils.isEmpty(Name)&&!TextUtils.isEmpty(Email)&&!TextUtils.isEmpty(Pwd)&&!TextUtils.isEmpty(Rpwd)&&!TextUtils.isEmpty(Mob)&&Pwd.equals(Rpwd)){
           if(Patterns.EMAIL_ADDRESS.matcher(Email).matches()&&Pwd.length()>=8)
           { progress.setMessage("Signing Up ...");
            progress.show();
            auth.createUserWithEmailAndPassword(Email,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                            String userid=auth.getCurrentUser().getUid();

                            DatabaseReference currentuserdb = database.child(userid);

                            currentuserdb.child("name").setValue(Name);
                            currentuserdb.child("mob").setValue(Mob);
                            progress.dismiss();
                        Intent i= new Intent(Sign_Up.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }


                }
            });}
            else
           {
               if(Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                   Toast.makeText(Sign_Up.this,"Password must contain minimum 8 Characters",Toast.LENGTH_LONG).show();
                   pwd.requestFocus();
               }
               else
               {
                   Toast.makeText(Sign_Up.this,"Enter Valid Mail",Toast.LENGTH_LONG).show();
                   email.requestFocus();
               }
           }
        }
    }




}
