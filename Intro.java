package com.example.roma.clickkhight;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.roma.myapplication.R;

public class Intro extends AppCompatActivity {
    Button b_start, b_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setFullScreen();
        setupButtons();
        setButtonListeners();
    }
    private void setFullScreen(){
        V.scrHeight=getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        V.scrWidth=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    private void setupButtons(){
        float x, y;
        int widthButton, heightButton;
        widthButton = (V.scrWidth>V.scrHeight?V.scrWidth:V.scrHeight)/3;
        heightButton = (int)(widthButton/2.8);
        x = V.scrWidth/2-widthButton/2;
        y =  V.scrHeight/2+heightButton/2;
        b_start = new Button(this, x, y, widthButton, heightButton, "Start");
        y = V.scrHeight/2+heightButton*1.5f;
        b_exit = new Button(this, x, y, widthButton, heightButton, "Exit");
    }
    private void setButtonListeners(){
        b_exit.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    b_exit.pressed();
                }
                if(event.getAction()==MotionEvent.ACTION_UP) {
                    b_exit.unpressed();
                    System.exit(0);
                }
                return true;
            }
        });
        b_start.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    b_start.pressed();
                }
                if(event.getAction()==MotionEvent.ACTION_UP) {
                    b_start.unpressed();
                    startActivity(new Intent(Intro.this, MainActivity.class));
                    finish();
                }
                return true;
            }
        });
    }
}
