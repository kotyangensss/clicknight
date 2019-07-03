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

public class Victory extends AppCompatActivity {
    float x,y;
    Button b_menu,b_prodolzhenie,b_victory;
    int widthButton, heightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        setFullScreen();
        menu();
        setListener();
        setVictory();
    }
    private void setFullScreen(){
        V.scrHeight=getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        V.scrWidth=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
    private void menu(){
        int widthButton, heightButton;
        widthButton = (V.scrWidth>V.scrHeight?V.scrWidth:V.scrHeight)/3;
        heightButton = (int)(widthButton/2.8);
        x = V.scrWidth/2-widthButton/2;
        y = V.scrHeight/4*3+heightButton/2;
        b_menu= new Button(this, x, y, widthButton, heightButton, "Menu");
        if(V.lvl<4) {
            y = V.scrHeight / 4 * 3 - heightButton / 2;
            b_prodolzhenie = new Button(this, x, y, widthButton, heightButton, "Nextlvl");
        }
    }
    private void setListener(){
        b_menu.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    b_menu.pressed();
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    startActivity(new Intent(Victory.this, Intro.class));
                    finish();
                    b_menu.unpressed();
                    V.lvl=0;
                }

                return true;
            }
        });
        if(V.lvl<4) {
        b_prodolzhenie.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    b_prodolzhenie.pressed();;
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    startActivity(new Intent(Victory.this, MainActivity.class));
                    finish();
                    V.lvl+=1;
                    b_prodolzhenie.unpressed();

                }

                return true;
            }
        });}
    }
    private void setVictory(){
        widthButton = V.scrWidth/3;
        heightButton = widthButton;
        x = V.scrWidth / 2 - widthButton / 2;
        y = V.scrHeight / 4;
        if (V.lvl<4){
        b_victory = new Button(this, x, y, widthButton, heightButton, "Next");}
        if (V.lvl==4){b_victory = new Button(this, x, y, widthButton, heightButton, "Victory");}
    }
}
