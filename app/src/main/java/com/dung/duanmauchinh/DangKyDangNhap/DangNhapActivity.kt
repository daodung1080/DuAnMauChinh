package com.dung.duanmauchinh.DangKyDangNhap

import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.ManHinhChinhActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_dang_nhap.*

class DangNhapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)

        activityAnim()

        txtDangKy.setOnClickListener {
            startActivity(Intent(this@DangNhapActivity,DangKyActivity::class.java))
            activityAnim()
        }
        btnDangNhap.setOnClickListener {
            startActivity(Intent(this@DangNhapActivity,ManHinhChinhActivity::class.java))
            activityAnim()
        }

    }
}
