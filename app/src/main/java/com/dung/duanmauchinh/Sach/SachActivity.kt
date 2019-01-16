package com.dung.duanmauchinh.Sach

import android.os.Bundle
import android.widget.ArrayAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_sach.*

class SachActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sach)
        testSpinner()
    }

    fun testSpinner(){
        var list: List<String> = listOf("Thông thái","Đào Dũng","Long Sim")
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        spSach.adapter = arrayAdapter
    }

}
