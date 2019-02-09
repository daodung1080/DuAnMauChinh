package com.dung.duanmauchinh.Book

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dung.duanmauchinh.Adapter.BookAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.BookDAO
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.dialog_edit_book.view.*

class BookListActivity : BaseActivity() {

    var list: ArrayList<Book>? = null
    var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        var bookDAO = BookDAO(this)

        list = bookDAO.getAllBook()
        adapter = BookAdapter(this,list!!)
        rvSach.layoutManager = LinearLayoutManager(this)
        rvSach.adapter = adapter

    }

    fun removeBook(bookid: String, position: Int){
        var bookDAO = BookDAO(this)
        var invoiceDetailDAO = InvoiceDetailDAO(this)

        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Xóa thông tin sách")
        alertDialog.setIcon(R.drawable.img_sach)
        alertDialog.setMessage("Bạn có chắc muốn xóa sách mã $bookid? \n\n" +
                "Xóa sách sẽ dẫn đến việc xóa hết hóa đơn liên quan")

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            if(bookDAO.removeBook(bookid) > 0) {
                invoiceDetailDAO.removeInvoiceDetailByBookID(bookid)
                list!!.removeAt(position)
                adapter?.notifyDataSetChanged()
                showMessage("Xóa sách mã $bookid thành công", true)
            }
            else if(bookDAO.removeBook(bookid) <= 0){
                showMessage("Xóa sách thất bại", false)
            }
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()

    }

    fun editBook(bookid: String,position: Int){
        var bookDAO = BookDAO(this)

        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Chỉnh sửa sách")
        alertDialog.setIcon(R.drawable.img_sach)
        var view = layoutInflater.inflate(R.layout.dialog_edit_book,null)
        var edtEditBookName = view.edtEditBookName
        var edtEditBookAuthor = view.edtEditBookAuthor
        var edtEditBookPrice = view.edtEditBookPrice
        var edtEditBookAmount = view.edtEditBookAmount

        var book = list!!.get(position)
        edtEditBookName.setText(book.tensach)
        edtEditBookAuthor.setText(book.tacgia)
        edtEditBookPrice.setText(book.giabia.toString())
        edtEditBookAmount.setText(book.soluong.toString())
        alertDialog.setView(view)

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            try{
                var bookname = edtEditBookName.text.toString()
                var author = edtEditBookAuthor.text.toString()
                var price = (edtEditBookPrice.text.toString()).toDouble()
                var amount = (edtEditBookAmount.text.toString()).toInt()
                var bookEdit = Book(null,null,bookname,author,price,amount)

                if(bookname.length < 5){
                    showMessage("Tên sách phải nhập hơn 5 ký tự",false)
                }
                else if(author.isEmpty()){
                    showMessage("Vui lòng không để trống tác giả",false)
                }
                else if(price < 10000){
                    showMessage("Vui lòng nhập giá tiền lớn hơn 10.000VND",false)
                }
                else if(amount < 0){
                    showMessage("Vui lòng nhập số lượng lớn hơn 0",false)
                }
                else if(bookDAO.editBook(bookid,bookEdit) > 0){
                    showMessage("Chỉnh sửa sách thành công",true)
                    list!!.set(position,Book(book.masach,book.matheloai,bookname,author,price,amount))
                    adapter!!.notifyDataSetChanged()
                }
                else if(bookDAO.editBook(bookid,bookEdit) <= 0){
                    showMessage("Chỉnh sửa sách thất bại",false)
                }
            }
            catch (e: NumberFormatException){
                showMessage("Vui lòng nhập số lượng và giá bìa là số",false)
            }
        })

        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()
    }

}
