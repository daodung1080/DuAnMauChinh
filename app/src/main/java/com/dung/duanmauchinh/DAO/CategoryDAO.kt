package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class CategoryDAO(context: Context?) {

    var database = Database(context)
    ///// Insert, Update and Remove
    fun insertCategory(category: Category): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().category_idcategory, category.matheloai)
        contentValues.put(Constants().category_categoryname, category.tentheloai)
        contentValues.put(Constants().category_position, category.vitri)
        contentValues.put(Constants().category_describe, category.mota)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().category_table, null ,contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun removeCategory(categoryid: String): Long{
        var result: Long = -1
        var sqLiteDatabase = database.writableDatabase

        result = sqLiteDatabase.delete(Constants().category_table,
            Constants().category_idcategory+"=?", arrayOf(categoryid))
            .toLong()

        return result
    }

    fun editCategory(categoryid: String,category: Category): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().category_categoryname,category.tentheloai)
        contentValues.put(Constants().category_position,category.vitri)
        contentValues.put(Constants().category_describe,category.mota)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(
            Constants().category_table,contentValues,
            Constants().category_idcategory+"=?", arrayOf(categoryid)).toLong()
        sqLiteDatabase.close()

        return result
    }
    //////////// Get
    fun getAllCategory(): ArrayList<Category>{
        var list: ArrayList<Category> = ArrayList()

        var query = "select * from "+ Constants().category_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var matheloai = cursor.getString(cursor.getColumnIndex(Constants().category_idcategory))
                    var tentheloai = cursor.getString(cursor.getColumnIndex(Constants().category_categoryname))
                    var vitri = cursor.getInt(cursor.getColumnIndex(Constants().category_position))
                    var mota = cursor.getString(cursor.getColumnIndex(Constants().category_describe))

                    var category = Category(matheloai,tentheloai,vitri,mota)
                    list.add(category)
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun getCategoryID(): ArrayList<String>{
        var list: ArrayList<String> = ArrayList()

        var query = "select * from "+Constants().category_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var a = cursor.getString(cursor.getColumnIndex(Constants().category_idcategory))
                    list.add(a)
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

}