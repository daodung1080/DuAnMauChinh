package com.dung.duanmauchinh.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dung.duanmauchinh.Model.User

class Database : SQLiteOpenHelper {

    constructor(context: Context?) : super(
        context,
        "duanmau1.sqlite",
        null,
        1
    )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DAO().CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var user_table = DAO().USER_TABLE
        db?.execSQL("drop table if exists $user_table")
        onCreate(db)
    }

    fun insertTable(user: User): Long{

        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(DAO().USER_USER_NAME,user.USER_USER_NAME)
        contentValues.put(DAO().USER_PASSWORD,user.USER_PASSWORD)
        contentValues.put(DAO().USER_PHONE,user.USER_PHONE)
        contentValues.put(DAO().USER_FULL_NAME,user.USER_FULL_NAME)

        var sqLiteDatabase = writableDatabase

        result = sqLiteDatabase.insert(DAO().USER_TABLE, null, contentValues )

        return result
    }

    fun getAllUser(): ArrayList<User>{

        var arrayList: ArrayList<User> = ArrayList()
        var QUERY = "SELECT * FROM "+ DAO().USER_TABLE

        var sqLiteDatabase = readableDatabase
        var cursor = sqLiteDatabase.rawQuery(QUERY,null)

        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                while (cursor.isAfterLast){
                    var USER_USER_NAME_ = cursor.getString(cursor.getColumnIndex(DAO().USER_USER_NAME))
                    var USER_PASSWORD_ = cursor.getString(cursor.getColumnIndex(DAO().USER_PASSWORD))
                    var USER_PHONE_ = cursor.getString(cursor.getColumnIndex(DAO().USER_PHONE))
                    var USER_HOTEN_ = cursor.getString(cursor.getColumnIndex(DAO().USER_FULL_NAME))
                    var user = User(USER_USER_NAME_,USER_PASSWORD_,USER_PHONE_,USER_HOTEN_)
                    arrayList.add(user)
                    cursor.moveToNext()
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return arrayList
    }

    fun updateUser(user: User): Int{
        var contentValues = ContentValues()
        contentValues.put(DAO().USER_USER_NAME, user.USER_USER_NAME)
        contentValues.put(DAO().USER_PASSWORD, user.USER_PASSWORD)
        contentValues.put(DAO().USER_PHONE, user.USER_PHONE)
        contentValues.put(DAO().USER_FULL_NAME, user.USER_FULL_NAME)
        var sqLiteDatabase = writableDatabase
        var username = DAO().USER_USER_NAME

        var result = sqLiteDatabase.update(DAO().USER_TABLE,contentValues,"$username = ?",
            arrayOf(user.USER_USER_NAME))
        if(result == 0){
            return -1
        }
        return 1
    }

    fun deleteNguoiDung(username: String): Int{
        var sqLiteDatabase = writableDatabase
        var username1 = DAO().USER_USER_NAME
        var result = sqLiteDatabase.delete(DAO().USER_TABLE,"$username1 = ?", arrayOf(username))
        if(result == 0){
            return -1
        }
        return 1
    }

}