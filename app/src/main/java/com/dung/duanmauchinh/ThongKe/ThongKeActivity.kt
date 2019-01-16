package com.dung.duanmauchinh.ThongKe

import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_thong_ke.*

class ThongKeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_ke)

        testThongKe()

    }

    fun testThongKe(){
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
    }

}
