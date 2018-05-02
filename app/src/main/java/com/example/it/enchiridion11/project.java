package com.example.it.enchiridion11;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class project extends AppCompatActivity {

    private RecyclerView mProjectList;

    private DatabaseReference mDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_project);

        mProjectList=(RecyclerView)findViewById(R.id.project_list);
        mProjectList.setHasFixedSize(true);
        mProjectList.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(this);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Project");
    }

    @Override
    protected void onStart() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        super.onStart();

        FirebaseRecyclerAdapter<project_card,ProjectViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<project_card, ProjectViewHolder>(
                project_card.class,
                R.layout.project_row,
                ProjectViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ProjectViewHolder viewHolder, project_card model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setReference(model.getReference());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                progressDialog.dismiss();
            }
        };

        mProjectList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public ProjectViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

        }

        public void setTitle(String Title){
            TextView project_Title=(TextView)mView.findViewById(R.id.project_title);
            project_Title.setText(Title);
        }

        public void setDescription(String Description)
        {
            TextView project_Description=(TextView)mView.findViewById(R.id.project_descrip);
            project_Description.setText(Description);
        }

        public void setReference(String Reference)
        {
            TextView project_Reference=(TextView)mView.findViewById(R.id.project_ref);
            project_Reference.setText(Reference);
        }

        public void setImage(Context ctx, String Image)
        {
            ImageView project_Image=(ImageView)mView.findViewById(R.id.project_image);

            Picasso.with(ctx).load(Image).into(project_Image);
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
            startActivity(new Intent(project.this,projectshare.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(project.this,nav.class));
    }
}
