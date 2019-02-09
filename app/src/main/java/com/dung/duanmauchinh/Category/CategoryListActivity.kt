package com.dung.duanmauchinh.Category

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dung.duanmauchinh.Adapter.CategoryAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.BookDAO
import com.dung.duanmauchinh.DAO.CategoryDAO
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.dialog_edit_category.view.*

class CategoryListActivity : BaseActivity() {

    var list: ArrayList<Category>? = null
    var adapter: CategoryAdapter? = null
    var categoryDAO: CategoryDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        list = ArrayList()
        categoryDAO = CategoryDAO(this)

        list = categoryDAO!!.getAllCategory()
        adapter = CategoryAdapter(this,list!!)
        rvTheLoai.layoutManager = LinearLayoutManager(this)
        rvTheLoai.adapter = adapter

    }

    fun removeCategory(matheloai: String, position: Int){
        var bookDAO = BookDAO(this)
        var invoiceDetailDAO = InvoiceDetailDAO(this)

        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Xóa thông tin thể loại")
        alertDialog.setIcon(R.drawable.img_theloai)
        alertDialog.setMessage("Bạn có chắc muốn xóa thể loại $matheloai? \n\n" +
                "Xóa thể loại sẽ dẫn đến việc xóa hết sách và hóa đơn liên quan đến sách")

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            if(categoryDAO!!.removeCategory(matheloai) > 0){
                bookDAO.removeBookCategory(matheloai)
                var bookList: ArrayList<String> = ArrayList()
                bookList = bookDAO.getIDBookByCategory(matheloai)

                for(i in 0..bookList.size-1){
                    invoiceDetailDAO.removeInvoiceDetailByBookID(bookList.get(i))
                }

                list!!.removeAt(position)
                adapter?.notifyDataSetChanged()
                showMessage("Xóa thành công thể loại mã $matheloai",true)
            }
            else if(categoryDAO!!.removeCategory(matheloai) <= 0){
                showMessage("Xóa thể loại thất bại",false)
            }
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()
    }

    fun editCategory(matheloai: String, position: Int){

        var categoryDAO = CategoryDAO(this)
        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Chỉnh sửa thể loại sách")
        alertDialog.setIcon(R.drawable.img_theloai)
        var view = layoutInflater.inflate(R.layout.dialog_edit_category,null)
        var category: Category = list!!.get(position)
        var edtEditCategoryName = view.edtEditCategoryName
        var edtEditCategoryPosition = view.edtEditCategoryPosition
        var edtEditCategoryDescribe = view.edtEditCategoryDescribe

        edtEditCategoryName.setText(category.tentheloai)
        edtEditCategoryPosition.setText(category.vitri.toString())
        edtEditCategoryDescribe.setText(category.mota)

        alertDialog.setView(view)

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            try {
                var categoryName = edtEditCategoryName.text.toString()
                var categoryPosition = (edtEditCategoryPosition.text.toString()).toInt()
                var categoryDescribe = edtEditCategoryDescribe.text.toString()
                var categoryEdit = Category(null,categoryName,categoryPosition,categoryDescribe)
                if(categoryName.length < 5){
                    showMessage("Tên thể loại phải nhập hơn 5 ký tự",false)
                }
                else if(categoryPosition <= 0){
                    showMessage("Vui lòng nhập vị trí lớn hơn 0",false)
                }
                else if(categoryDAO.editCategory(matheloai,categoryEdit) > 0){
                    showMessage("Chỉnh sửa thể loại thành công",true)
                    list!!.set(position, Category(matheloai,categoryName,categoryPosition,categoryDescribe))
                    adapter!!.notifyDataSetChanged()
                }
                else if(categoryDAO.editCategory(matheloai,categoryEdit) <= 0){
                    showMessage("Chỉnh sửa thể loại thất bại",true)
                }
            }
            catch (e: NumberFormatException){
                showMessage("Vui lòng nhập vị trí là số",false)
            }
        })

        alertDialog.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()
    }

}
