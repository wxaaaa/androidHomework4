package com.example.wxaaaa.homework3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wxaaaa.homework3.R;

public class MainActivity extends AppCompatActivity {

    public AlertDialog.Builder builder;
    public Button classNumber;
    public Button update;
    public Button modify;
    public ProgressBar progressBar;
    public Handler handler;


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


        // TODO
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
