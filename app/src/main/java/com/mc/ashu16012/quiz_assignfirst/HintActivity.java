package com.mc.ashu16012.quiz_assignfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Boolean takenhint =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        relativeLayout=(RelativeLayout)findViewById(R.id.relhint);
        Button showhintbtn = (Button)findViewById(R.id.showhintbtn);

        showhintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takenhint = true;
                Intent hintintent = getIntent();
                TextView showhinttxt = (TextView)findViewById(R.id.showhinttxt);
                showhinttxt.setText(hintintent.getStringExtra("Hint_ans"));
            }
        });
    }

    @Override
    public void finish(){
        Intent intent=new Intent();
        intent.putExtra("Quiz_hint",takenhint);
        setResult(RESULT_OK,intent);
        super.finish();
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("takenhint",takenhint);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        takenhint=savedInstanceState.getBoolean("takenhint");
    }

}
