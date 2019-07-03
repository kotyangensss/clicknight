package com.example.roma.clickkhight;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.roma.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Plastinka> plastinka;
    RelativeLayout screen;
    Random rnd = new Random(System.currentTimeMillis());
    MyTimer timer;
    Button b_enemy,b_hero;
    float x1,y1;
    int w;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFullScreen();
        plastinka =new ArrayList<>();
        screen = findViewById(R.id.screen);
        startNewGame();
        timer = new MyTimer(5000000, 800);
        timer.start();
        chooseImage();
        chooseEnemy();
        setHero();
        if (V.lvl>=5){V.lvl=0;}
    }
    protected void onDestroy(){
        super.onDestroy();
        timer.cancel();
    }

    public void startNewGame() {
        for(int i=0;i<3;i++) {
            int size = V.scrWidth/3;
            float x = size/2+V.scrWidth/3*V.knopka;
            float y =V.scrHeight-size*3/2;
            V.knopka+=1;
            plastinka.add(new Plastinka(MainActivity.this, x, y, size,1));
        }
        V.knopka=0;
        for(int i=3;i<6;i++) {
            int size = V.scrWidth/3;
            float x = size/2+V.scrWidth/3*V.knopka ;
            float y = V.scrHeight-size/2;
            V.knopka+=1;
            plastinka.add(new Plastinka(MainActivity.this, x, y, size,2));
        }
        V.knopka=0;
    }

    private void setFullScreen(){
        V.scrHeight=getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        V.scrWidth=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    private void losing(){
        startActivity(new Intent(MainActivity.this, Lose.class));
        finish();
        V.check=0;
        V.lose=0;
    }

    private void nextLVL(){
        startActivity(new Intent(MainActivity.this,Victory.class));
        finish();
        V.check=0;
        V.lose=0;
    }

    private void update(){
        plastinka.get(V.pred).pressed();
        V.choose= 0 + rnd.nextInt(5 - 0 + 1);
        plastinka.get(V.choose).unpressed();
        V.pred=V.choose;
        V.lose+=1;
        if(V.check>=10+V.lvl){
            nextLVL();
            timer.cancel();
            for (int i=0;i<6;i++){
                plastinka.get(i).unpressed();
            }
        }
        if(V.lose-V.check>=6-V.lvl){
            losing();
            timer.cancel();
            for (int i=0;i<6;i++){
                plastinka.get(i).pressed();
            }
        }
    }

    class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }

        @Override
        public void onFinish() {
        }
    }
    private void chooseImage(){
        if(V.lvl==4){Toast. makeText(this, "Уровень 5", Toast.LENGTH_SHORT).show();}
        if(V.lvl==3){Toast. makeText(this, "Уровень 4", Toast.LENGTH_SHORT).show();}
        if(V.lvl==2){Toast. makeText(this, "Уровень 3", Toast.LENGTH_SHORT).show();}
        if(V.lvl==1){Toast. makeText(this, "Уровень 2", Toast.LENGTH_SHORT).show();}
        if(V.lvl==0){Toast. makeText(this, "Уровень 1", Toast.LENGTH_SHORT).show();}
    }
    private void setHero(){
        w=V.scrHeight/6;
        x1=V.scrWidth/2-w;
        y1=V.scrHeight/4;
        b_hero = new Button(this,x1,y1,w,w,"Hero");
    }
    private void chooseEnemy(){
       x1=V.scrWidth/2+w;
       y1=V.scrHeight/4;
       w=V.scrHeight/6;
        if(V.lvl==4){s1="Enemy5";}
        if(V.lvl==3){s1="Enemy4";}
        if(V.lvl==2){s1="Enemy3";}
        if(V.lvl==1){s1="Enemy2";}
        if(V.lvl==0){s1="Enemy1";}
        b_enemy = new Button(this,x1,y1,w,w,s1);
    }
}
