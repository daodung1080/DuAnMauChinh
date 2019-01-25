package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class BookDAO(context: Context) {

    var database = Database(context)

    fun insertBook(book: Book): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().book_idbook, book.masach)
        contentValues.put(Constants().book_idcategory, book.matheloai)
        contentValues.put(Constants().book_bookname, book.tensach)
        contentValues.put(Constants().book_author, book.tacgia)
        contentValues.put(Constants().book_price, book.giabia)
        contentValues.put(Constants().book_amount, book.soluong)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().book_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun getAllBook(): ArrayList<Book>{
        var list: ArrayList<Book> = ArrayList()

        var query = "select * from "+ Constants().book_table
        var sqLiteDatabase = database.readableDatabase
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