package com.dung.duanmauchinh.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.R
import com.dung.duanmauchinh.Book.BookListActivity
import kotlinx.android.synthetic.main.list_item_book.view.*

class BookAdapter(var context: BookListActivity, var bookList: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.BookHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.list_item_book,p0,false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookHolder, p1: Int) {
        var book = bookList.get(p1)
        holder.txtTenSach.text = book.tensach
        holder.txtSoLuongSach.text = book.soluong.toString()
        holder.imgCloseSach.setOnClickListener { context.removeBook(book.masach!!,p1) }
        holder.imgEditSach.setOnClickListener { context.editBook(book.masach!!,p1) }
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTenSach = itemView.txtTenSach
        var txtSoLuongSach = itemView.txtSoLuongSach
        var imgCloseSach = itemView.imgCloseSach
        var imgEditSach = itemView.imgEditSach
    }

}