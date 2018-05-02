package com.example.it.enchiridion11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Comment;

import java.util.Random;

public class Forum extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference,databaseReference2;
    private static String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        recyclerView=(RecyclerView)findViewById(R.id.forumview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Blog");
        //databaseReference=FirebaseDatabase.getInstance().getReference().child("Blog");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogHolder>(
            Blog.class,
                R.layout.forum_row,
                BlogHolder.class,
                databaseReference

        ) {
            @Override
            protected void populateViewHolder(final BlogHolder viewHolder, final Blog model, int position) {
                    viewHolder.setTitle(model.getTitle());
                    viewHolder.setDesc(model.getDesc());
                    viewHolder.b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        // viewHolder.editText.setText(random());
                            if(!viewHolder.editText.getText().toString().equals(""))
                            {String s= viewHolder.editText.getText().toString();
                            if(model.getComment()==null)
                          databaseReference.child(model.getKey()).child("Comment").setValue("\n\n"+s);
                            else
                                databaseReference.child(model.getKey()).child("Comment").setValue(model.getComment()+"\n\n"+s);
                            st=model.getComment()+"\n\n"+s;
                            startActivity(new Intent(Forum.this, com.example.it.enchiridion11.Comment.class));}

                        }
                    });
                    viewHolder.b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            st=model.getComment();
                            startActivity(new Intent(Forum.this, com.example.it.enchiridion11.Comment.class));
                        }
                    });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogHolder extends RecyclerView.ViewHolder{
    View view;
        Button b1;
        Button b2;
        EditText editText;
        public BlogHolder(View itemView) {
            super(itemView);
            view=itemView;
            b1=(Button) view.findViewById(R.id.Comment);
            b2=(Button)view.findViewById(R.id.showc);
            editText=(EditText)view.findViewById(R.id.ask);
        }
        public void setTitle(String Title){
            TextView post_title=(TextView)view.findViewById(R.id.postTitle);
            post_title.setText(Title);
        }
        public void setDesc(String Desc){
            TextView post_Desc=(TextView)view.findViewById(R.id.PostDescription);
            post_Desc.setText(Desc);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add)
        {
            startActivity(new Intent(Forum.this,Postfr.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
    public static String returnKey()
    {
        return st;
    }
}
