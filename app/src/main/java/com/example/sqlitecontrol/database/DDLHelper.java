package com.example.sqlitecontrol.database;

public class DDLHelper {
    public static final String USER = "CREATE TABLE SQLC_USER ("
            + " userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + " userName TEXT, "
            + " isLogin INTEGER DEFAULT (1), "
            + " createdDate DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + " loginDate DATETIME DEFAULT CURRENT_TIMESTAMP "
            + " ); ";
}
