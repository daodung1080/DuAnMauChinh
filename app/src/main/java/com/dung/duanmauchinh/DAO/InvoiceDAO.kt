package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.Invoice
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database
import java.util.*

class InvoiceDAO(context: Context?) {

    var database = Database(context)
    ///// Insert, Update and Remove
    fun insertInvoice(invoice: Invoice): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().invoice_id,invoice.mahoadon)
        contentValues.put(Constants().invoice_date,invoice.ngaymua)
        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().invoice_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun editInvoice(invoiceID: String, invoice: Invoice): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().invoice_date,invoice.ngaymua)
        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(Constants().invoice_table, contentValues,
            Constants().invoice_id+"=?", arrayOf(invoiceID)).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun removeInvoice(invoiceID: String): Long{

        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().invoice_table,
            Constants().invoice_id+"=?", arrayOf(invoiceID)).toLong()
        sqLiteDatabase.close()

        return result
    }
    //////////// Get
    fun getIDInvoice(): ArrayList<String>{
        var list: ArrayList<String> = ArrayList()

        var query = "select * from "+Constants().invoice_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var mahoadon = cursor.getString(cursor.getColumnIndex(Constants().invoice_id))
                    list.add(mahoadon)
                }
            }
        }

        return list
    }

    fun getAllInvoice(): ArrayList<Invoice>{
        var list: ArrayList<Invoice> = ArrayList()
        var query = "select * from "+Constants().invoice_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var mahoadon = cursor.getString(cursor.getColumnIndex(Constants().invoice_id))
                    var ngaymua = cursor.getString(cursor.getColumnIndex(Constants().invoice_date))
                    var invoice = Invoice(mahoadon, ngaymua)
                    list.add(invoice)
                }
            }
        }
        return list
    }

}
