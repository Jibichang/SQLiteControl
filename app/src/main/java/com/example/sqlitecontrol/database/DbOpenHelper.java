package com.example.sqlitecontrol.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static DbOpenHelper dbOpenHelper;
    private static Context context;
    private String TAG = "SQLiteDatabase";

    private DbOpenHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Field[] fields = DDLHelper.class.getFields();
        for (Field field : fields) {
            try {
                String sql = (String) field.get(null);
                sqLiteDatabase.execSQL(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "onCreate(): Created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading db");
        switch (oldVersion) {
            case 1:
                sqLiteDatabase.execSQL(DDLHelper.USER);
            default:
                onCreate(sqLiteDatabase);
        }
    }

    public static DbOpenHelper getInstance(Context ctx) {
        context = ctx;
        if (dbOpenHelper == null) {
            dbOpenHelper = new DbOpenHelper(ctx, DbConfig.DEFAULT_DB_NAME, DbConfig.DB_VERSION);
        }
        return dbOpenHelper;
    }

    public static SQLiteDatabase getReadable(Context ctx) {
        return getInstance(ctx).getReadableDatabase();
    }

    public static SQLiteDatabase getWritable(Context ctx) {
        return getInstance(ctx).getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public List<String> listAllTableName() {
        List<String> tableNames = null;
        SQLiteDatabase db = this.getReadableDatabase();
        final String queryString = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            tableNames = new ArrayList<>(cursor.getCount());
            do {
                tableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        return tableNames;
    }
}
