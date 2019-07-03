package com.example.roma.clickkhight;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.roma.myapplication.R;

public class Button {
    ImageView image;
    float x, y;
    int sizeX, sizeY;
    String s;
    Button(Activity activity,float x,float y,int sx,int sy,String s){
        image = new ImageView(activity);
        this.x = x;
        this.y = y;
        sizeX = sx;
        sizeY = sy;
        this.s = s;
        image.setImageResource(chooseImage(s));
        activity.addContentView(image, new RelativeLayout.LayoutParams(sizeX, sizeY));
        outXY();
    }
    private void outXY(){
        image.setX(x);
        image.setY(y);
    }
    private int chooseImage(String s){
        switch (s){
            case "Next":return R.drawable.win;
            case "Victory":return R.drawable.win;
            case "Nextlvl":return R.drawable.next;
            case "Enemy1":return R.drawable.bat;
            case "Enemy2":return R.drawable.slime;
            case "Enemy3":return R.drawable.pirate;
            case "Enemy4":return R.drawable.golem;
            case "Enemy5":return R.drawable.hater;
            case "Hero":return R.drawable.hero;
            case "Gameover":return R.drawable.gameover;
            case "Menu":return R.drawable.mainmenu;
            case "Start": return R.drawable.start;
            case "Exit": return R.drawable.exit;
            default: return R.mipmap.ic_launcher;
        }
    }
    public void pressed(){
        image.setX(x+4);
        image.setY(y+4);
        image.setImageAlpha(200);
    }
    public void unpressed(){
        outXY();
        image.setImageAlpha(255);
    }
}
