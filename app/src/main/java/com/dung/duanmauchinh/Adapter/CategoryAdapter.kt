package com.dung.duanmauchinh.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dung.duanmauchinh.Model.Category
import com.dung.duanmauchinh.R
import com.dung.duanmauchinh.Category.CategoryListActivity
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoryAdapter(var context: CategoryListActivity, var categoryList: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.list_item_category,p0,false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, p1: Int) {

        var category = categoryList.get(p1)
        holder.txtIdTheLoai.text = category.matheloai
        holder.txtTenTheLoai.text = category.tentheloai
        holder.imgCloseTheLoai.setOnClickListener {
            context.removeCategory(category.matheloai!!,p1)
        }
        holder.imgEditTheLoai.setOnClickListener {
            context.editCategory(category.matheloai!!,p1)
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtIdTheLoai: TextView = itemView.txtIDTheLoai
        var txtTenTheLoai: TextView = itemView.txtTenTheLoai
        var imgCloseTheLoai: ImageView = itemView.imgCloseTheLoai
        var imgEditTheLoai: ImageView = itemView.imgEditTheLoai
    }
}