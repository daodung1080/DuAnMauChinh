package com.dung.duanmauchinh.TheLoai

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dung.duanmauchinh.Adapter.CategoryAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.CategoryDAO
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_danh_sach_the_loai.*

class DanhSachTheLoaiActivity : BaseActivity() {

    var list: ArrayList<Category> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_the_loai)

        var categoryDAO = CategoryDAO(this)

        for(i in 0..10){
            var matheloai = "mã thể loại $i"
            var tentheloai = "tên thể loại $i"
            var vitri = i
            var mota = "mô tả $i"
            var category = Category(matheloai,tentheloai,vitri, mota)
            categoryDAO.insertCategory(category)
        }

        list = categoryDAO.getAllCategory()
        rvTheLoai.layoutManager = LinearLayoutManager(this)
        rvTheLoai.adapter = CategoryAdapter(this,list)

    }
}
