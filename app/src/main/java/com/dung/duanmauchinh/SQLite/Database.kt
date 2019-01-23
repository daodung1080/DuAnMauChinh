package com.dung.duanmauchinh.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.Model.User

class Database(context: Context?): SQLiteOpenHelper(context,"duanmaudung2.sql",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants().create_table_user)
        db?.execSQL(Constants().create_table_category)
        db?.execSQL(Constants().create_table_book)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists "+Constants().user_table)
        db?.execSQL("drop table if exists "+Constants().category_table)
        db?.execSQL("drop table if exists "+Constants().book_table)
        onCreate(db)
    }

    fun insertUser(user: User): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().user_username, user.username)
        contentValues.put(Constants().user_password, user.password)
        contentValues.put(Constants().user_phone, user.phone)
        contentValues.put(Constants().user_fullname, user.fullname)

        var sqLiteDatabase = this.writableDatabase
        result = sqLiteDatabase.insert(Constants().user_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun getAllUser(): ArrayList<User>{
        var list: ArrayList<User> = ArrayList()

        var query = "select * from "+Constants().user_table
        var sqLiteDatabase = this.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor != null){
            if(cursor.count > 0){
                cursor.moveToFirst()
                while (!cursor.isAfterLast){

                    var user_username = cursor.getString(cursor.getColumnIndex(Constants().user_username))
                    var user_password = cursor.getString(cursor.getColumnIndex(Constants().user_password))
                    var user_phone = cursor.getString(cursor.getColumnIndex(Constants().user_phone))
                    var user_fullname = cursor.getString(cursor.getColumnIndex(Constants().user_fullname))

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

    fun insertCategory(category: Category): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().category_idcategory, category.matheloai)
        contentValues.put(Constants().category_categoryname, category.tentheloai)
        contentValues.put(Constants().category_position, category.vitri)
        contentValues.put(Constants().category_describe, category.mota)

        var sqLiteDatabase = this.writableDatabase
        result = sqLiteDatabase.insert(Constants().category_table, null ,contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun getAllCategory(): ArrayList<Category>{
        var list: ArrayList<Category> = ArrayList()

        var query = "select * from "+Constants().category_table
        var sqLiteDatabase = this.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var matheloai = cursor.getString(cursor.getColumnIndex(Constants().category_idcategory))
                    var tentheloai = cursor.getString(cursor.getColumnIndex(Constants().category_idcategory))
                    var vitri = cursor.getInt(cursor.getColumnIndex(Constants().category_position))
                    var mota = cursor.getString(cursor.getColumnIndex(Constants().category_idcategory))

                    var category = Category(matheloai,tentheloai,vitri,mota)
                    list.add(category)
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun insertBook(book: Book): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().book_idbook, book.masach)
        contentValues.put(Constants().book_idcategory, book.matheloai)
        contentValues.put(Constants().book_bookname, book.tensach)
        contentValues.put(Constants().book_author, book.tacgia)
        contentValues.put(Constants().book_price, book.giabia)
        contentValues.put(Constants().book_amount, book.soluong)

        var sqLiteDatabase = this.writableDatabase
        result = sqLiteDatabase.insert(Constants().book_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun getAllBook(): ArrayList<Book>{
        var list: ArrayList<Book> = ArrayList()

        var query = "select * from "+Constants().book_table
        var sqLiteDatabase = readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var bookid = cursor.getString(cursor.getColumnIndex(Constants().book_idbook))
                    var categoryid = cursor.getString(cursor.getColumnIndex(Constants().book_idcategory))
                    var bookname = cursor.getString(cursor.getColumnIndex(Constants().book_bookname))
                    var author = cursor.getString(cursor.getColumnIndex(Constants().book_author))
                    var price = cursor.getDouble(cursor.getColumnIndex(Constants().book_price))
                    var amount = cursor.getInt(cursor.getColumnIndex(Constants().book_amount))

                    var book = Book(bookid,categoryid,bookname,author,price,amount)
                    list.add(book)

                }
                sqLiteDatabase.close()
                cursor.close()
            }
        }

        return list
    }

}