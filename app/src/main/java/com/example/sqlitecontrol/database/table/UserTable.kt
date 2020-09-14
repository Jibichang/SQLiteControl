package com.example.sqlitecontrol.database.table

import android.content.ContentValues
import android.content.Context
import android.util.Log

open class UserTable constructor(context: Context?) : BaseTable(context) {
    fun create(userName: String?): Long {
        val cv = ContentValues()
        cv.put(COLM_USER_NAME, userName)
        return db.insert(TABLE_NAME, null, cv)
    }

    fun getUser(userName: String): String {
        var name = ""
        val query = "SELECT userName FROM $TABLE_NAME WHERE userName like ?;"
        val cs = db.rawQuery(query, arrayOf(userName))
        if (cs.moveToFirst()) {
            name = cs.getString(cs.getColumnIndex("userName"))
        }
        cs.close()
        Log.i("SQLiteDatabase", cs.count.toString() + " --")
        return name
    }

    fun removeAll() {
        db.delete(TABLE_NAME, null, null)
    }

    companion object {
        const val TABLE_NAME = "SQLC_USER"
    }
}