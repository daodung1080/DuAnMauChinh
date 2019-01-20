package com.dung.duanmauchinh

import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BanChay.BanChayActivity
import com.dung.duanmauchinh.HoaDon.HoaDonActivity
import com.dung.duanmauchinh.NguoiDung.NguoiDungActivity
import com.dung.duanmauchinh.Sach.SachActivity
import com.dung.duanmauchinh.TheLoai.TheLoaiSachActivity
import com.dung.duanmauchinh.ThongKe.ThongKeActivity
import kotlinx.android.synthetic.main.activity_man_hinh_chinh.*

class ManHinhChinhActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_hinh_chinh)

        activityAnim()
        switchIntent()

    }

    fun switchIntent(){
        cvNguoiDung.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity,NguoiDungActivity::class.java))
            activityAnim()
        }
        cvTheLoaiSach.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity,TheLoaiSachActivity::class.java))
            activityAnim()
        }
        cvSach.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity,SachActivity::class.java))
            activityAnim()
        }
        cvHoaDon.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity,HoaDonActivity::class.java))
            activityAnim()
        }
        cvBanChay.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity, BanChayActivity::class.java))
            activityAnim()
        }
        cvThongKe.setOnClickListener {
            startActivity(Intent(this@ManHinhChinhActivity,ThongKeActivity::class.java))
            activityAnim()
        }
    }
}
