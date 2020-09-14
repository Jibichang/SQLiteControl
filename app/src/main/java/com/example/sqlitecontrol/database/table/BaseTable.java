package com.example.sqlitecontrol.database.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitecontrol.database.DbOpenHelper;

public class BaseTable {
    public static final String COLM_USER_ID         = "userId";
    public static final String COLM_USER_NAME       = "userName";
    public static final String COLM_IS_LOG_IN       = "isLogin";
    public static final String COLM_LOG_IN_DATE     = "loginDate";
    public static final String COLM_CREATED_DATE    = "createdDate";

    protected SQLiteDatabase db;

    protected BaseTable(Context context) {
        db = DbOpenHelper.getWritable(context);
    }

    public void close() {
        if(null != db){
            db.close();
        }
    }
}
