package com.example.it.enchiridion11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        TextView ed=(TextView)findViewById(R.id.tv);
        ed.setText(Forum.returnKey());
    }
}
