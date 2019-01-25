package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.Invoice
import com.dung.duanmauchinh.Model.InvoiceDetail
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class InvoiceDAO(context: Context?) {

    var database = Database(context)

    fun insertInvoice(invoice: Invoice): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().invoice_table, null, contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun getAllInvoice(): ArrayList<InvoiceDetail>{
        var list: ArrayList<InvoiceDetail> = ArrayList()

        return list
    }

}