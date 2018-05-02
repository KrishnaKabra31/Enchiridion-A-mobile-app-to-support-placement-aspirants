package com.example.it.enchiridion11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText email,password;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //private DatabaseReference mDatabaseUsers;


    //FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity.this,nav.class));
                }
            }
        };
        b1=(Button)findViewById(R.id.sign_in);
        b2=(Button)findViewById(R.id.sign_up);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        progressDialog=new ProgressDialog(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(MainActivity.this,Sign_Up.class)));
            }
        });

    }
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void startSignIn(){
        String Email=email.getText().toString();

        String pwd=password.getText().toString();
        progressDialog.setMessage("Signing In...");
        progressDialog.show();

         if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(pwd))
         {
             Toast.makeText(MainActivity.this,"Please enter all Fields",Toast.LENGTH_LONG).show();
             progressDialog.dismiss();
         }
        else
         {


             if(Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
        mAuth.signInWithEmailAndPassword(Email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Incorrect E-mail or password",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,nav.class));
                    progressDialog.dismiss();
                }
            }
        });}
             else
             {
                 Toast.makeText(MainActivity.this,"Enter valid Email",Toast.LENGTH_LONG).show();
                 email.requestFocus();
             }

         }


    }
}
