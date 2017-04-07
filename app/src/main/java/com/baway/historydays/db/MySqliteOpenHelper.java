package com.baway.historydays.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/27 22:38
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    public MySqliteOpenHelper(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table detauils ( _id integer primary key autoincrement,title varchar(100),content varchar(100),url varchar(100),date varchar(100),flag integer)");
//        db.execSQL("create table student ( _id integer primary key autoincrement, name varchar(20),age integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
