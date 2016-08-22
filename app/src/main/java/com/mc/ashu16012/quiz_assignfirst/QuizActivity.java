package com.mc.ashu16012.quiz_assignfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private int number=0;
    private int score=0;
    private static final String TAG="MainActivity";
    private static final int CHEATREQUESTCODE = 1;
    private static final int HINTREQUESTCODE = 2;
    private RelativeLayout relativeLayout;
    private Boolean takenhint = false;
    private Boolean takencheat = false;
    private int divisor = 1;

    private int generate_randnum(){
        Random r;
        r=new Random();
        return r.nextInt((999 - 2) + 1) + 2;
    }

    private Boolean isprime(int num){
        for(int i=2;i<num/2;i++){
            if((num%i)==0){
                divisor=i;
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
        final Button falsebtn=(Button)findViewById(R.id.falsebtn);
        Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
        Button resetbtn=(Button)findViewById(R.id.resetbtn);
        Button scorebtn=(Button)findViewById(R.id.scorebtn);
        final Button cheatbtn = (Button)findViewById(R.id.cheatbtn);
        Button hintbtn = (Button) findViewById(R.id.hintbtn);
        TextView questxt=(TextView) findViewById(R.id.questxt);
        relativeLayout=(RelativeLayout)findViewById(R.id.relquiz);

        String qtxt=String.valueOf(number).concat(" is a prime??");
        questxt.setText(qtxt);

        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if(isprime(number)&&!takencheat&&!takenhint){
                    score++;
                    text="Correct";
                }
                else if(takencheat){
                    text= "U have cheated....according to plagarism policy score is deducted";
                    if(score>0)
                        score--;
                    Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                }
                else if(takenhint){
                    text = "U have taken hint...marks for this question is evaluated..try to solve without hint";
                    Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                }
                else{
                    text="InCorrect";
                }
                Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                nxtbtn.performClick();
            }
        });

        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=null;
                if(!isprime(number)&&!takencheat&&!takenhint){
                    score++;
                    text="Correct";
                }
                else if(takencheat){
                    text= "U have cheated....according to plagarism policy score is deducted";
                    if(score>0)
                        score--;
                    Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                }
                else if(takenhint){
                    text = "U have taken hint...marks for this question is evaluated..try to solve without hint";
                    Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                }
                else{
                    text="InCorrect";
                }
                Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                nxtbtn.performClick();
            }
        });


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                Button nxtbtn=(Button)findViewById(R.id.nxtbtn);
                Snackbar.make(relativeLayout,"reseted you can start allover again",Snackbar.LENGTH_SHORT).show();
                nxtbtn.performClick();
            }
        });


        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takencheat=false;
                takenhint=false;
                number=generate_randnum();
                isprime(number);
                String qtxt=String.valueOf(number).concat(" is a prime??");
                TextView questxt=(TextView)findViewById(R.id.questxt);
                questxt.setText(qtxt);
            }
        });

        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text="Your score is ".concat(String.valueOf(score));
                Snackbar.make(relativeLayout,text,Snackbar.LENGTH_SHORT).show();;
            }
        });

        cheatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cheatintent = new Intent(QuizActivity.this,CheatActivity.class);
                cheatintent.putExtra("Cheat_ans",isprime(number));
                cheatintent.putExtra("Cheat_sol",divisor);
                startActivityForResult(cheatintent,CHEATREQUESTCODE);
            }
        });

        hintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isprime(number);
                Intent cheatintent = new Intent(QuizActivity.this,HintActivity.class);
                cheatintent.putExtra("Hint_ans","Divide "+Integer.toString(number)+" by "+Integer.toString(divisor));
                startActivityForResult(cheatintent,HINTREQUESTCODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent intent){

        if(resultcode==RESULT_OK&&requestcode==HINTREQUESTCODE){
            takenhint = intent.getBooleanExtra("Quiz_hint",true);
        }
        else if(resultcode==RESULT_OK&&requestcode==CHEATREQUESTCODE){
            takencheat = intent.getBooleanExtra("Quiz_cheat",true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("score_key",score);
        savedInstanceState.putInt("number_key",number);
        savedInstanceState.putBoolean("takenhint",takenhint);
        savedInstanceState.putBoolean("takencheat",takencheat);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score=savedInstanceState.getInt("score_key");
        takencheat=savedInstanceState.getBoolean("takencheat");
        takenhint=savedInstanceState.getBoolean("takenhint");
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
