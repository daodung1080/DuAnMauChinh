package com.dung.duanmauchinh.Sach

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dung.duanmauchinh.Adapter.BookAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.BookDAO
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_danh_sach_sach.*

class DanhSachSachActivity : BaseActivity() {

    var list: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_sach)

        var bookDAO = BookDAO(this)

        for(i in 0..10){
            var masach = "mã sách: $i"
            var theloai = "thể loại: $i"
            var tensach = "tên sách: $i"
            var tacgia = "tác giả $i"
            var giabia: Double = 20.5
            var soluong = i
            var book = Book(masach,theloai,tensach,tacgia,giabia,soluong)
            bookDAO.insertBook(book)
        }

        list = bookDAO.getAllBook()
        rvSach.layoutManager = LinearLayoutManager(this)
        rvSach.adapter = BookAdapter(this,list)

    }
}
