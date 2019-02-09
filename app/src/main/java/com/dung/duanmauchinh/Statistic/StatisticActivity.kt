package com.dung.duanmauchinh.Statistic

import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_statistic.*
import java.text.SimpleDateFormat
import java.util.*

class StatisticActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        this.thongKe()

    }

    fun thongKe(){
        pbHomNay.isEnabled = false
        pbHomNay.max = 300000
        pbHomNay.progress = 100000
        txtTienHomNay.text = pbHomNay.progress.toString() +" VND"

        pbThangNay.isEnabled = false
        pbThangNay.max = 2000000
        pbThangNay.progress = 500000
        txtTienThangNay.text = pbThangNay.progress.toString()+" VND"

        pbNamNay.isEnabled = false
        pbNamNay.max = 5000000
        pbNamNay.progress = 3400000
        txtTienNamNay.text = pbNamNay.progress.toString()+" VND"

        var invoiceDetailDAO = InvoiceDetailDAO(this)
        var tienHomNay = invoiceDetailDAO.getSaleAmount(this.ngayHomNay(),this.thangNay(),this.namNay(),0)
        txtTienHomNay.text = tienHomNay

        var tienThangNay = invoiceDetailDAO.getSaleAmount(this.ngayHomNay(),this.thangNay(),this.namNay(),1)
        txtTienThangNay.text = tienThangNay

        var tienNamNay = invoiceDetailDAO.getSaleAmount(this.ngayHomNay(),this.thangNay(),this.namNay(),2)
        txtTienNamNay.text = tienNamNay

    }

    fun ngayHomNay(): String{
        var simpleDateFormat = SimpleDateFormat("dd")
        return simpleDateFormat.format(Calendar.getInstance().time)
    }

    fun thangNay(): String{
        var simpleDateFormat = SimpleDateFormat("MM")
        return simpleDateFormat.format(Calendar.getInstance().time)
    }

    fun namNay(): String{
        var simpleDateFormat = SimpleDateFormat("yyyy")
        return simpleDateFormat.format(Calendar.getInstance().time)
    }

}
