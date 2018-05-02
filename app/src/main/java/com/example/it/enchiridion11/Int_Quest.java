package com.example.it.enchiridion11;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Int_Quest extends AppCompatActivity {

    private RecyclerView mInterviewQueList;

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int__quest);
        mInterviewQueList=(RecyclerView)findViewById(R.id.idint_quest);
        mInterviewQueList.setHasFixedSize(true);
        mInterviewQueList.setLayoutManager(new LinearLayoutManager(this));


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Interview Questions");
        progressDialog=new ProgressDialog(this);


    }

    @Override
    protected void onStart() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        super.onStart();

        FirebaseRecyclerAdapter<Questions,QuestionViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Questions, QuestionViewHolder>(
                Questions.class,
                R.layout.int_que_row,
                QuestionViewHolder.class,
                mDatabase



        ) {
            @Override
            protected void populateViewHolder(QuestionViewHolder viewHolder, Questions model, int position) {

                viewHolder.setQuestion(model.getQ());
                viewHolder.setAns(model.getAns());
                progressDialog.dismiss();

            }
        };
        mInterviewQueList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

        }
        public void setQuestion(String q)
        {
            TextView postQuestion=(TextView)mView.findViewById(R.id.idquestion);
            postQuestion.setText(q);

        }
        public void setAns(String Ans)
        {
            TextView postAns=(TextView)mView.findViewById(R.id.idans);
            postAns.setText(Ans);
        }


    }

}
