package com.example.myapplication;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class TextViewAnimation {

    private static TextView tv;
    private static String str;
    private static int length;
    private static int time;
    static int n = 0;
    private  static int nn;

    public TextViewAnimation(TextView tv, String str, int time){
        this.tv = tv;
        this.str = str;
        this.time = time;
        this.length = str.length();
        start(n);
    }

    public static void start(final int n){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            final String subst = str.substring(0,n/time);
                            tv.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(subst);
                                    tv.setTextColor(tv.getTextColors().withAlpha(255*n/(time*length)));
                                }
                            });

                            nn = n+1;
                            if(nn<=length*time){
                                Thread.sleep(0);
                                start(nn);
                            }
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}
