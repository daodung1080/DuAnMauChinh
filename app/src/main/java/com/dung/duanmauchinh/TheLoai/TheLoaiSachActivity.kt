package com.dung.duanmauchinh.TheLoai

import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_the_loai_sach.*

class TheLoaiSachActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_loai_sach)

        btnDanhSachTheLoai.setOnClickListener { startActivity(Intent(this@TheLoaiSachActivity,DanhSachTheLoaiActivity::class.java)) }

    }
}
