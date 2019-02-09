package com.dung.duanmauchinh.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.duanmauchinh.Model.BestSale
import com.dung.duanmauchinh.Model.BillDetail
import com.dung.duanmauchinh.Model.InvoiceDetail
import com.dung.duanmauchinh.SQLite.Constants
import com.dung.duanmauchinh.SQLite.Database

class InvoiceDetailDAO(context: Context?) {

    var database = Database(context)
    ///// Insert, Update and Remove
    fun insertInvoiceDetail(invoiceDetail: InvoiceDetail): Long{
        var result: Long = -1

        var contentValues= ContentValues()
        contentValues.put(Constants().invoice_detail_id,invoiceDetail.mahoadonct)
        contentValues.put(Constants().invoice_detail_invoiceid,invoiceDetail.mahoadon)
        contentValues.put(Constants().invoice_detail_idbook,invoiceDetail.masach)
        contentValues.put(Constants().invoice_detail_buyamount,invoiceDetail.soluongmua)
        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().invoice_detail_table,null,contentValues)

        sqLiteDatabase.close()

        return result
    }

    fun removeInvoiceDetailByBookID(bookID: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().invoice_detail_table,
            Constants().invoice_detail_idbook+"=?", arrayOf(bookID)).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun removeInvoiceDetail(invoiceID: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().invoice_detail_table,
            Constants().invoice_detail_invoiceid+"=?", arrayOf(invoiceID)).toLong()
        sqLiteDatabase.close()

        return result
    }

    //////////// Get
    fun getallInvoiceDetail(invoiceID: String): ArrayList<BillDetail>{
        var list: ArrayList<BillDetail> = ArrayList()
        var hoadonchitiettable = Constants().invoice_detail_table
        var sachtable = Constants().book_table
        var masach = Constants().book_idbook
        var soluong = Constants().invoice_detail_buyamount
        var giabia = Constants().book_price
        var mahoadon= Constants().invoice_detail_invoiceid

        var query = "select $sachtable.$masach, $sachtable.$giabia," +
                " $hoadonchitiettable.$soluong, $hoadonchitiettable.$mahoadon" +
                " from $sachtable join $hoadonchitiettable on $sachtable.$masach = $hoadonchitiettable.$masach " +
                "where $hoadonchitiettable.$mahoadon like '$invoiceID' "

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var masach = cursor.getString(0)
                    var giabia = cursor.getDouble(1)
                    var soluong = cursor.getInt(2)
                    var thanhtien = giabia * soluong
                    var billDetail = BillDetail(masach,soluong,giabia,thanhtien)
                    list.add(billDetail)
                }
            }
        }

        return list
    }

    fun getBestSaleInvoice(month: String): ArrayList<BestSale>{
        var list: ArrayList<BestSale> = ArrayList()

        var hoadonchitiettable = Constants().invoice_detail_table
        var hoadontable = Constants().invoice_table
        var soluong = Constants().invoice_detail_buyamount
        var masach = Constants().invoice_detail_idbook
        var ngaymua = Constants().invoice_date
        var mahoadon = Constants().invoice_id

        var query = "select $hoadonchitiettable.$masach, $hoadonchitiettable.$soluong," +
                "$hoadontable.$ngaymua from $hoadonchitiettable " +
                "join $hoadontable on $hoadonchitiettable.$mahoadon = $hoadontable.$mahoadon " +
                "where strftime('%m', $hoadontable.$ngaymua) = '$month' " +
                "order by $hoadonchitiettable.$soluong desc"

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var masach = cursor.getString(0)
                    var soluongmua = cursor.getInt(1)
                    var bestSale = BestSale(masach, soluongmua)
                    list.add(bestSale)
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun getSaleAmount(day: String, month: String, year: String, function: Int): String {

        var gia = "Không có thu nhập"

        var hoadonTable = Constants().invoice_table
        var hoadonctTable = Constants().invoice_detail_table
        var sachTable = Constants().book_table
        var giabia = Constants().book_price
        var soluongmua = Constants().invoice_detail_buyamount
        var ngaymua = Constants().invoice_date
        var masach = Constants().book_idbook
        var mahoadon = Constants().invoice_id
        var query = ""

        when(function){
            0 -> {
                var query = "select $sachTable.$giabia , $hoadonTable.$ngaymua, $hoadonctTable.$soluongmua " +
                        "from $sachTable join $hoadonctTable on $sachTable.$masach = $hoadonctTable.$masach " +
                        "join $hoadonTable on $hoadonctTable.$mahoadon = $hoadonTable.$mahoadon " +
                        "where strftime('%d',$hoadonTable.$ngaymua) = '$day' and strftime('%m',$hoadonTable.$ngaymua) = '$month' and " +
                        "strftime('%Y',$hoadonTable.$ngaymua) = '$year' "
            }
            1 -> {
                var query = "select $sachTable.$giabia , $hoadonTable.$ngaymua, $hoadonctTable.$soluongmua " +
                        "from $sachTable join $hoadonctTable on $sachTable.$masach = $hoadonctTable.$masach " +
                        "join $hoadonTable on $hoadonctTable.$mahoadon = $hoadonTable.$mahoadon " +
                        "where strftime('%m',$hoadonTable.$ngaymua) = '$month' and strftime('%Y',$hoadonTable.$ngaymua) = '$year'"
            }
            2 -> {
                var query = "select $sachTable.$giabia , $hoadonTable.$ngaymua, $hoadonctTable.$soluongmua " +
                        "from $sachTable join $hoadonctTable on $sachTable.$masach = $hoadonctTable.$masach " +
                        "join $hoadonTable on $hoadonctTable.$mahoadon = $hoadonTable.$mahoadon " +
                        "where strftime('%Y',$hoadonTable.$ngaymua) = '$year' "
            }
        }

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                var thanhtien: Int = 0
                while (cursor.moveToNext()){
                    var giabia = cursor.getDouble(0)
                    var soluong = cursor.getInt(2)
                    thanhtien = (thanhtien+(giabia * soluong)).toInt()
                    if(thanhtien == 0){ gia = "Chưa có thu nhập"}
                    else{gia = thanhtien.toString()+" VND"}
                }
            }
        }
        return gia
    }

}