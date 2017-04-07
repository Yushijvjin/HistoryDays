package com.baway.historydays.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/28 9:11
 */

public class DetauilsDao {
    public static final String TAG = "DetauilsDao";
    private final MySqliteOpenHelper mySqliteOpenHelper;
    ArrayList<DetauilsBean> list = new ArrayList<DetauilsBean>();


    public DetauilsDao(Context context) {
        mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    //    private String url;
//    private String content;
//    private String title;
//    private boolean flag;
    public void add(String url, String content,String date, String title, int flag) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("url", url);
        values.put("content", content);
        values.put("title", title);
        values.put("date", date);
        values.put("flag", flag);
        db.insert("detauils", null, values);
        db.close();
    }

    public void delete(String title) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.delete("detauils", "title=?", new String[]{title});
        db.close();

    }

    public ArrayList<DetauilsBean> query() {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("detauils", null, null, null, null, null, null);
//        list.clear();
        while (cursor.moveToNext()) {

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
//            int flag = cursor.getInt(cursor.getColumnIndex("flag"));
            int flag = cursor.getInt(cursor.getColumnIndex("flag"));
            list.add(new DetauilsBean(content, flag, date, title, url));
        }

        return list;
    }


}
