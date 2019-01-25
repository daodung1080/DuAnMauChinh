package com.dung.duanmauchinh.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?): SQLiteOpenHelper(context,"duanmaudung5.sql",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants().create_table_user)
        db?.execSQL(Constants().create_table_category)
        db?.execSQL(Constants().create_table_book)
        db?.execSQL(Constants().create_table_invoice)
        db?.execSQL(Constants().create_table_invoice_detail)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists "+Constants().user_table)
        db?.execSQL("drop table if exists "+Constants().category_table)
        db?.execSQL("drop table if exists "+Constants().book_table)
        db?.execSQL("drop table if exists "+Constants().invoice_table)
        db?.execSQL("drop table if exists "+Constants().invoice_detail_table)
        onCreate(db)
    }

}