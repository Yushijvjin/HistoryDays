package com.baway.historydays.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.baway.historydays.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class NavigationActivity extends AutoLayoutActivity {

    private Handler  handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                Intent intent = new Intent(NavigationActivity.this,MainActivity.class);
                startActivity(intent);
                NavigationActivity.this.finish();

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getSupportActionBar().hide();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);

            }
        }.start();
    }
}
