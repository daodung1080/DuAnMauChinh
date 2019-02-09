package com.dung.duanmauchinh.Category

import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.CategoryDAO
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        switchActivity()
        btnThemTheLoai.setOnClickListener { this.validateCategory() }
        btnHuyTheLoai.setOnClickListener { this.cancelAll() }

    }

    fun cancelAll(){ edtMaTheLoai.setText("")
        edtTenTheLoai.setText("")
        edtViTriTheLoai.setText("")
        edtMoTaTheLoai.setText("") }

    fun validateCategory(){ var categoryDAO = CategoryDAO(this)
        try {
            var matheloai = edtMaTheLoai.text.toString()
            var tentheloai = edtTenTheLoai.text.toString()
            var edtvitri = edtViTriTheLoai.text.toString()
            var vitri = edtvitri.toInt()
            var mota = edtMoTaTheLoai.text.toString()
            if(matheloai.isEmpty() || matheloai.length != 5){
                showMessage("Mã thể loại phải nhập 5 ký tự",false)
                return
            }
            if(tentheloai.isEmpty() || tentheloai.length < 5){
                showMessage("Tên thể loại phải nhập hơn 5 ký tự",false)
                return
            }
            if(vitri <= 0){
                showMessage("Vui lòng nhập vị trí lớn hơn 0",false)
                return
            }

            var result: Long = categoryDAO.insertCategory(Category(matheloai, tentheloai, vitri, mota))
            if(result > 0){
                showMessage("Thêm thể loại thành công",true)
                this.cancelAll()
            }
            else{
                showMessage("Thêm thể loại thất bại do nhập trùng mã thể loại",false)
            }
        }
        catch (e: NumberFormatException){
            showMessage("Vui lòng nhập vị trí là số",false)
        }
    }

    fun switchActivity(){ btnDanhSachTheLoai.setOnClickListener { startActivity(Intent(this@CategoryActivity,CategoryListActivity::class.java)) } }

}
