package com.dung.duanmauchinh

import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BestSale.BestSaleActivity
import com.dung.duanmauchinh.Invoice.InvoiceActivity
import com.dung.duanmauchinh.User.UserActivity
import com.dung.duanmauchinh.Book.BookActivity
import com.dung.duanmauchinh.Category.CategoryActivity
import com.dung.duanmauchinh.Statistic.StatisticActivity
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        activityAnim()
        switchIntent()

    }

    fun switchIntent(){
        cvNguoiDung.setOnClickListener {
            var intent = Intent(this@MainScreenActivity,UserActivity::class.java)
            startActivity(intent)
            activityAnim()
        }
        cvTheLoaiSach.setOnClickListener {
            startActivity(Intent(this@MainScreenActivity,CategoryActivity::class.java))
            activityAnim()
        }
        cvSach.setOnClickListener {
            startActivity(Intent(this@MainScreenActivity,BookActivity::class.java))
            activityAnim()
        }
        cvHoaDon.setOnClickListener {
            startActivity(Intent(this@MainScreenActivity, InvoiceActivity::class.java))
            activityAnim()
        }
        cvBanChay.setOnClickListener {
            startActivity(Intent(this@MainScreenActivity, BestSaleActivity::class.java))
            activityAnim()
        }
        cvThongKe.setOnClickListener {
            startActivity(Intent(this@MainScreenActivity,StatisticActivity::class.java))
            activityAnim()
        }
    }

}
