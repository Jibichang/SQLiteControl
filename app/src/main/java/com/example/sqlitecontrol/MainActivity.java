package com.example.sqlitecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sqlitecontrol.database.table.UserTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserTable userTable = new UserTable(getApplicationContext());
//        userTable.removeAll();
//        userTable.create("Simon");
        Log.i("SQLiteDatabase", "userTable " + userTable.getUser("Simon"));
    }
}