package com.example.roma.clickkhight;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.roma.myapplication.R;

public class Plastinka {
    float x,y;
    int size;
    int s;
    ImageView image;
    Plastinka(final Activity activity, float x, float y, int size,int s){
        this.x=x;
        this.y=y;
        this.size=size;
        this.s=s;
        image=new ImageView(activity);
       if (s==2){ image.setImageResource(R.drawable.shield);}
       if (s==1){image.setImageResource(R.drawable.sword);}
        activity.addContentView(image, new RelativeLayout.LayoutParams(size, size));
        image.setImageAlpha(100);
        outXY();
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if(image.getImageAlpha()==255) {
                        pressed();
                        V.check+=1;
                    }
                }
                    return true;
            }
        });
    }
    private void outXY() {
        image.setX(x - size / 2);
        image.setY(y - size / 2);
    }
    public void pressed(){
        image.setImageAlpha(100);
    }
    public void unpressed(){
        image.setImageAlpha(255);
    }

}
