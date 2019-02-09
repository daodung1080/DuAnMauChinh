package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class UserDAO(context: Context?) {

    var database = Database(context)
    ///// Insert, Update and Remove
    fun insertUser(user: User): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().user_username, user.username)
        contentValues.put(Constants().user_password, user.password)
        contentValues.put(Constants().user_phone, user.phone)
        contentValues.put(Constants().user_fullname, user.fullname)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().user_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun deleteUser(username: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().user_table, Constants().user_username+"=?", arrayOf(username)).toLong()

        return result
    }

    fun editUser(username: String, user: User): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().user_password,user.password)
        contentValues.put(Constants().user_phone,user.phone)
        contentValues.put(Constants().user_fullname,user.fullname)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(
            Constants().user_table,contentValues,
            Constants().user_username+"=?", arrayOf(username)).toLong()
        sqLiteDatabase.close()

        return result
    }

    //////////// Get
    fun getAllUser(): ArrayList<User>{
        var list: ArrayList<User> = ArrayList()

        var query = "select * from "+ Constants().user_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                while (!cursor.isAfterLast){

                    var user_username = cursor.getString(0)
                    var user_password = cursor.getString(1)
                    var user_phone = cursor.getString(2)
                    var user_fullname = cursor.getString(3)

                    var user = User(user_username,user_password,user_phone,user_fullname)
                    list.add(user)

                    cursor.moveToNext()
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }
        return list
    }

    fun getAllUserByUsername(username: String): ArrayList<User>{
        var list: ArrayList<User> = ArrayList()

        var userTable = Constants().user_table
        var userUsername = Constants().user_username

        var query = "select * from $userTable where $userUsername = '$username' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                while (!cursor.isAfterLast){

                    var user_username = cursor.getString(0)
                    var user_password = cursor.getString(1)
                    var user_phone = cursor.getString(2)
                    var user_fullname = cursor.getString(3)

                    var user = User(user_username,user_password,user_phone,user_fullname)
                    list.add(user)

                    cursor.moveToNext()
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }
        return list
    }

    fun checkLogin(username: String, password: String): Boolean{
        var result: Boolean = true

        var userTable = Constants().user_table
        var userName = Constants().user_username
        var passWord = Constants().user_password
        var query = "select * from $userTable where $userName = '$username' and $passWord = '$password' "

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query, null)
        if(cursor.count > 0){
            result = true
        }
        else{
            result = false
        }

        return result
    }

}