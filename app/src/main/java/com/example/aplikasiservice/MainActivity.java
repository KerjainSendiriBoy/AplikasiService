package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg1;
    public int mChangeTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rg1 = findViewById(R.id.rg);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mDisable = new Intent(MainActivity.this, WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRbID = rg1.getCheckedRadioButtonId();
                if(rb1.getId()==mRbID){mChangeTime=60;}
                else if(rb2.getId()==mRbID){mChangeTime=5*60;}
                else if(rb3.getId()==mRbID){mChangeTime=30*60;}
                else if(rb4.getId()==mRbID){mChangeTime=60*60;}
                Intent mSrv = new Intent(MainActivity.this, WallpaperChangeService.class);
                Bundle bTime = new Bundle();
                bTime.putInt("durasi", mChangeTime);
                mSrv.putExtras(bTime);
                startService(mSrv);
                finish();
            }
        });
    }
}
