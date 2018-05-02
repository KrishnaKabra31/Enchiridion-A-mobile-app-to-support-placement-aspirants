package com.example.it.enchiridion11;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Quiz_activity extends AppCompatActivity {
    private RecyclerView quizlist;
    private DatabaseReference databaseReference;

   private RadioGroup r1;
    static String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activity);
        quizlist=(RecyclerView)findViewById(R.id.quizview);
        quizlist.setHasFixedSize(true);
        quizlist.setLayoutManager(new LinearLayoutManager(this));
      //  r1=(RadioGroup)findViewById(R.id.Radiogrp);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Quiz").child(Compsel.returnS());


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Quiz_card,QuizHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Quiz_card, QuizHolder>(
                Quiz_card.class,
                R.layout.quiz_card_row,
                QuizHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(QuizHolder viewHolder, final Quiz_card model, int position) {
             viewHolder.setQuestion(model.getQ());
                viewHolder.seta(model.getA().toString());
                viewHolder.setb(model.getB().toString());
                viewHolder.setc(model.getC().toString());
                viewHolder.setd(model.getD().toString());
                viewHolder.setAns(model.getAns().toString());
                viewHolder.b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog alertDialog = new AlertDialog.Builder(Quiz_activity.this).create();
                        RadioGroup ra=(RadioGroup)findViewById(R.id.Radiogrp);
                        RadioButton radio=(RadioButton)findViewById(ra.getCheckedRadioButtonId());
                        if(radio.getText().toString().equals(model.getAns().toString()))
                        {  alertDialog.setTitle("Correct!");
                        alertDialog.setMessage("your answer is correct");}
                        else{
                            alertDialog.setTitle("Incorrect");
                            alertDialog.setMessage("Correct Answer\n"+model.getAns().toString());
                        }
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });
                viewHolder.b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Quiz_activity.this).create();
                        alertDialog.setTitle("Answer");
                        alertDialog.setMessage(model.getAns().toString());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });


            }
        };
        quizlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class QuizHolder extends RecyclerView.ViewHolder{
        View mView;
        Button b1,b2;
        public QuizHolder(View itemView) {
            super(itemView);
            mView=itemView;
             b1=(Button)mView.findViewById(R.id.submit);
            b2=(Button)mView.findViewById(R.id.show);
        }
        public void setQuestion(String q){
            TextView que=(TextView)mView.findViewById(R.id.idque);
            que.setText(q);

        }
        public void seta(String a){
            RadioButton r=(RadioButton)mView.findViewById(R.id.a);
            r.setText(a);

        }
        public void setb(String b){
            RadioButton r=(RadioButton)mView.findViewById(R.id.b);
            r.setText(b);
        }
        public void setc(String c){
            RadioButton r=(RadioButton)mView.findViewById(R.id.c);
            r.setText(c);
        }
        public void setd(String d){
            RadioButton r=(RadioButton)mView.findViewById(R.id.d);
            r.setText(d);
        }
        public void setAns(String ans)
        {
            s=ans;
        }
    }
}
