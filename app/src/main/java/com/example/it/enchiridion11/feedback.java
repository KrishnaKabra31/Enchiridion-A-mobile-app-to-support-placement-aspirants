package com.example.it.enchiridion11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        editText=(EditText)findViewById(R.id.emailfeed);
        button=(Button)findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kanha31@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                i.putExtra(Intent.EXTRA_TEXT   , editText.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
