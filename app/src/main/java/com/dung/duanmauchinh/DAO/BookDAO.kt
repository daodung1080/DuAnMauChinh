package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class BookDAO(context: Context?) {

    var database = Database(context)
    ///// Insert, Update and Remove
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

    fun editBook(bookid: String,book: Book): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().book_bookname,book.tensach)
        contentValues.put(Constants().book_author,book.tacgia)
        contentValues.put(Constants().book_price,book.giabia)
        contentValues.put(Constants().book_amount,book.soluong)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(Constants().book_table,contentValues,
            Constants().book_idbook+"=?", arrayOf(bookid)).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun editBookAmount(bookID: String,book: Book): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().book_amount,book.soluong)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(Constants().book_table, contentValues,
            Constants().book_idbook+"=?", arrayOf(bookID)).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun removeBook(bookid: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().book_table,
            Constants().book_idbook+"=?", arrayOf(bookid)).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun removeBookCategory(categoryID: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().book_table,
            Constants().book_idcategory+"=?", arrayOf(categoryID)).toLong()
        sqLiteDatabase.close()

        return result
    }
    //////////// Get
    fun getBookAmount(bookID: String): Int{
        var amount = 0

        var bookTable = Constants().book_table
        var bookId = Constants().book_idbook
        var query = "select * from $bookTable where $bookId = '$bookID' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    amount = cursor.getInt(cursor.getColumnIndex(Constants().book_amount))
                }
                sqLiteDatabase.close()
                cursor.close()
            }
        }

        return amount
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


    fun getIDBookByCategory(categoryID: String): ArrayList<String>{
        var list: ArrayList<String> = ArrayList()

        var bookTable = Constants().book_table
        var bookIdCategory = Constants().book_idcategory

        var query = "select * from $bookTable where $bookIdCategory like '$categoryID' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var masach = cursor.getString(cursor.getColumnIndex(Constants().book_idbook))
                    list.add(masach)
                }
            }
        }

        return list
    }

    fun getIDBook(): ArrayList<String>{
        var list: ArrayList<String> = ArrayList()

        var query = "select * from "+Constants().book_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var masach = cursor.getString(cursor.getColumnIndex(Constants().book_idbook))
                    list.add(masach)
                }
            }
        }

        return list
    }
}