package com.example.wxaaaa.homework3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wxaaaa.homework3.R;

import java.lang.annotation.Annotation;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tyrantgit.widget.HeartLayout;

public class MainActivity extends AppCompatActivity{

    public AlertDialog.Builder builder;
    public Button classNumber;
    public Button update;
    public Button modify;
    public ProgressBar progressBar;
    public Handler handler;
    public HeartLayout mHeartLayout;
    public Timer mTimer = new Timer();
    public Random mRandom = new Random();


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        classNumber = findViewById(R.id.classNumber);
        update = findViewById(R.id.update);
        modify = findViewById(R.id.modifyClassName);
        progressBar = findViewById(R.id.progressBar);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1: builder.show();
                    default: break;
                }
            }
        };

        classNumber.setOnClickListener(new ButtonListener(ClassNumberActivity.class));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newClassName = findViewById(R.id.className);
                TextView textView = findViewById(R.id.textView);
                textView.setText(newClassName.getText() + "班管理系统demo");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Thread t = new Thread() {
                    public void run() {
                        int oldProgress = 0;
                        progressBar.setProgress(oldProgress);
                        while (progressBar.getProgress() < 100) {
                            try {
                                Thread.sleep(100);
                                progressBar.setProgress(oldProgress+1);
                                oldProgress += 1;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                t.start();
            }
        });

        // 爱心气泡
        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHeartLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mHeartLayout.addHeart(randomColor());
                    }
                });
            }
        }, 500, 400);

        AnimationSet setAnimation = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                // X轴的开始位置
                android.view.animation.Animation.RELATIVE_TO_PARENT, 0f,
                // X轴的结束位置
                android.view.animation.Animation.RELATIVE_TO_PARENT, 0.8f,
                // Y轴的开始位置
                android.view.animation.Animation.RELATIVE_TO_SELF, 0f,
                // Y轴的结束位置
                android.view.animation.Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(20);  //  设置动画重复次数

        translateAnimation.setRepeatMode(android.view.animation.Animation.REVERSE);

        setAnimation.addAnimation(translateAnimation);
        mHeartLayout.startAnimation(setAnimation);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }

    class ButtonListener implements View.OnClickListener {

        private Class clazz;

        ButtonListener(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, clazz);
            startActivity(intent);
        }
    }
}
