package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/5/005.
 * 数据库操作类，建表
 */

public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
    /**
     * 省份Province表建表
     */
    public static final String CREATE_PROVINCE  = "create table Province("
                    +"id integer primary key autoincrement,"
                    +"province_name txet,"
                    +"province_code text)";
    /**
     * 创建City表
     * province_id为关联Province表的外键
     */
    public static final String CREATE_CITY  = "create table City("
            +"id integer primary key autoincrement,"
            +"city_name txet,"
            +"city_code text,"
            +"province_id integer)";
    /**
     * 省份Province表建表
     * city_id为关联City表的外键
     */
    public static final String CREATE_COUNTRY  = "create table Country("
            +"id integer primary key autoincrement,"
            +"country_name txet,"
            +"country_code text,"
            +"city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
