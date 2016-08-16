package com.mc.ashu16012.quiz_assignfirst;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class QuizActivity extends AppCompatActivity {

    private int number=0;
    private int score=0;
    private static final String TAG="MainActivity";

    private int generate_randnum(){
        Random r;
        r=new Random();
        return r.nextInt((999 - 2) + 1) + 2;
    }

    private Boolean isprime(int num){
        for(int i=2;i<num/2;i++){
            if((num%i)==0){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        number=generate_randnum();
        Log.d(TAG,"inside onCreate");
        Button truebtn=(Button)findViewById(R.id.truebtn);
        Button falsebtn=(Button)findViewById(R.id.falsebtn);
        Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
        Button resetbtn=(Button)findViewById(R.id.resetbtn);
        Button scorebtn=(Button)findViewById(R.id.scorebtn);
        TextView questxt=(TextView) findViewById(R.id.questxt);

        String qtxt=String.valueOf(number).concat(" is a prime??");
        questxt.setText(qtxt);

        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if(isprime(number)){
                    score++;
                    text="Correct";
                }
                else{
                    text="InCorrect";
                }
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                nxtbtn.performClick();
            }
        });

        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if(!isprime(number)){
                    score++;
                    text="Correct";
                }
                else{
                    text="InCorrect";
                }
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                nxtbtn.performClick();
            }
        });


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                Toast.makeText(getApplicationContext(), "reseted you can start allover again", Toast.LENGTH_SHORT).show();
                nxtbtn.performClick();
            }
        });


        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=generate_randnum();
                String qtxt=String.valueOf(number).concat(" is a prime??");
                TextView questxt=(TextView)findViewById(R.id.questxt);
                questxt.setText(qtxt);
            }
        });

        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text="Your score is ".concat(String.valueOf(score));
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("score_key",score);
        savedInstanceState.putInt("number_key",number);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score=savedInstanceState.getInt("score_key");
        Log.i(TAG, "Inside onRestoreInstanceState"+Integer.toString(number));
        number=savedInstanceState.getInt("number_key");
        Log.i(TAG, "Inside onRestoreInstanceState"+Integer.toString(number));
        TextView questxt=(TextView) findViewById(R.id.questxt);
        String qtxt=String.valueOf(number).concat(" is a prime??");
        questxt.setText(qtxt);
        Log.i(TAG, "Inside onRestoreInstanceState");
}

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnSTop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}
