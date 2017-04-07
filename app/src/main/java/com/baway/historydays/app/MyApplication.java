package com.baway.historydays.app;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/21 11:24
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AutoLayoutConifg.getInstance().useDeviceSize();



    }
}
