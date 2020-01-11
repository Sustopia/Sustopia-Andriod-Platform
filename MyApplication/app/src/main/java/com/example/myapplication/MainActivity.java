package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fenjuly.library.ArrowDownloadButton;
import com.unstoppable.submitbuttonview.SubmitButton;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public EditText mEdtSid, mEdtPsw;
    public SubmitButton mBtnNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //按照界面的的顺序对应到相应的对象
        mEdtSid = (EditText) findViewById(R.id.EdtSid);
        mEdtPsw = (EditText) findViewById(R.id.EdtPsw);
        mBtnNext = (SubmitButton) findViewById(R.id.BtnNext);
        //连接监听器
        mBtnNext.setOnClickListener(BtnNextOnClick);
    }

    protected void printText() {
        int iSid = Integer.parseInt(mEdtSid.getText().toString());   //用户有输入
        String strPsw = mEdtPsw.getText().toString();
        //验证学号和密码
        if(iSid==0&&strPsw.equals("0")){   //登录成功
            mBtnNext.doResult(true);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Handler mainHandler=new Handler(Looper.getMainLooper());
                            //进入第二个界面“欢迎”
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent it = new Intent();
                                    it.setClass(MainActivity.this, SecondActivity.class);
                                    startActivity(it);
                                }
                            });
                            //0.5s后重置按钮
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mainHandler=new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBtnNext.reset();
                                }
                            });
                        }
                    }
            ).start();
        }
        else{
            //登录失败,弹出对话框
            try {
                mBtnNext.doResult(false);
                //登录失败后，播放动画后，重置按钮
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Handler mainHandler=new Handler(Looper.getMainLooper());
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mBtnNext.reset();
                                    }
                                });
                            }
                        }
                ).start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //设置监听器,获取学号和密码
    private View.OnClickListener BtnNextOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //如果输入为空，不做操作
            if (mEdtSid.getText().toString().equals("") || mEdtPsw.getText().toString().equals("")) {
                return;
            }
            //启动动画
            mBtnNext.startAnimating();
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            Handler mainHandler=new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    printText();
                                }
                            });
                        }
                    }
            ).start();
        }
    };
}
