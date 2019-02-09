package com.dung.duanmauchinh.Book

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.BookDAO
import com.dung.duanmauchinh.DAO.CategoryDAO
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_category.*

class BookActivity : BaseActivity() {

    var list: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        
        this.switchActivity()
        this.categorySpinner()
        this.clickButton()


    }

    fun clickButton(){
        btnHuyBoSach.setOnClickListener {
            edtMaSach.setText("")
            edtMaTheLoai.setText("")
            edtTenSach.setText("")
            edtTacGiaSach.setText("")
            edtGiaBia.setText("")
            edtSoLuongSach.setText("")
        }

        btnThemSach.setOnClickListener {
            this.validateBook()
        }

    }

    fun validateBook(){
        var bookDAO = BookDAO(this)
        try{
            var masach = edtMaSach.text.toString()
            var matheloai = list?.get(spSach.selectedItemPosition).toString()
            var tensach = edtTenSach.text.toString()
            var tacgia = edtTacGiaSach.text.toString()
            var giabia = (edtGiaBia.text.toString()).toDouble()
            var soluong = (edtSoLuongSach.text.toString()).toInt()
            if(masach.isEmpty() || masach.length != 5){
                showMessage("Mã sách phải nhập 5 ký tự",false)
                return
            }
            if(tensach.isEmpty() || tensach.length < 5){
                showMessage("Tên sách phải nhập hơn 5 ký tự",false)
                return
            }
            if(tacgia.isEmpty()){
                showMessage("Vui lòng không để trống tác giả",false)
                return
            }
            if(giabia < 10000){
                showMessage("Vui lòng nhập giá tiền lớn hơn 10.000VND",false)
                return
            }
            if(soluong < 0){
                showMessage("Vui lòng nhập số lượng lớn hơn 0",false)
                return
            }
            if (spSach.selectedItemPosition == null){
                showMessage("Vui lòng điền mã sách",false)
                return
            }

            var result: Long = bookDAO.insertBook(Book(masach,matheloai,tensach,tacgia,giabia,soluong))
            if(result > 0){
                showMessage("Thêm sách thành công",true)
                this.clearEditText()
            }
            else{
                showMessage("Thêm sách thất bại do nhập trùng mã sách",false)
            }
        }
        catch(e: NumberFormatException){
            showMessage("Vui lòng nhập số lượng và đơn giá là số",false)
        }
        catch (e2: ArrayIndexOutOfBoundsException){
            showMessage("Vui lòng nhập thể loại trước",false)
        }
    }

    fun categorySpinner(){
        var categoryDAO = CategoryDAO(this)
        list = ArrayList()
        list = categoryDAO.getCategoryID()
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_item_for_all,list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spSach.adapter = arrayAdapter
    }

    fun switchActivity(){
        btnDanhSachSach.setOnClickListener { startActivity(Intent(this@BookActivity,BookListActivity::class.java)) }
        activityAnim() }

    fun clearEditText(){
        edtMaSach.setText("")
        edtTenSach.setText("")
        edtTacGiaSach.setText("")
        edtGiaBia.setText("")
        edtSoLuongSach.setText("")
    }

}
