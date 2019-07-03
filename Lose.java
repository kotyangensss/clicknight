package com.example.roma.clickkhight;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.roma.myapplication.R;

public class Lose extends AppCompatActivity {
    float x, y;
    Button b_menu1,b_lose;
    int widthButton, heightButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        setFullScreen();
        menu();
        setLose();

    }
    private void setFullScreen(){
        V.scrHeight=getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        V.scrWidth=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
    private void menu(){
    widthButton = (V.scrWidth > V.scrHeight ? V.scrWidth : V.scrHeight) / 3;
    heightButton = (int) (widthButton / 2.8);
        x = V.scrWidth/2-widthButton/2;
        y = V.scrHeight/4*3+heightButton/2;
    b_menu1 = new Button(this, x, y, widthButton, heightButton, "Menu");
        b_menu1.image.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                b_menu1.pressed();
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                startActivity(new Intent(Lose.this, Intro.class));
                finish();
                b_menu1.unpressed();
                V.lvl = 0;
            }

            return true;
        }
    });
    }
    private void setLose(){
        widthButton = V.scrWidth/3;
        heightButton = widthButton;
        x = V.scrWidth / 2 - widthButton / 2;
        y = V.scrHeight / 4;
        b_lose = new Button(this, x, y, widthButton, heightButton, "Gameover");
    }
}
