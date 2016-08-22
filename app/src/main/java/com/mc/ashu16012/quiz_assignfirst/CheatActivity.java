package com.mc.ashu16012.quiz_assignfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Boolean takencheat=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        relativeLayout=(RelativeLayout)findViewById(R.id.relhint);
        Button showcheatbtn = (Button)findViewById(R.id.showcheatbtn);

        showcheatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takencheat=true;
                String str=null;
                Boolean isprime=false;
                Intent intent=getIntent();
                isprime=intent.getBooleanExtra("Cheat_ans",false);

                str = !isprime ? "The given number is not a prime. Proof: it is divisible by:" + Integer.toString(intent.getIntExtra("Cheat_sol", 1)) : "Yeaaahhh Given number is prime";

                TextView showcheattxt = (TextView)findViewById(R.id.showcheattxt);
                showcheattxt.setText(str);
            }
        });
    }

    @Override
    public void finish(){
        Intent intent=new Intent();
        intent.putExtra("Quiz_cheat",takencheat);
        setResult(RESULT_OK,intent);
        super.finish();
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("takencheat",takencheat);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        takencheat=savedInstanceState.getBoolean("takencheat");
    }
}
