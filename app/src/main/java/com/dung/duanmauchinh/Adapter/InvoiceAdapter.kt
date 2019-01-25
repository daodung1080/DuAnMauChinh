package com.dung.duanmauchinh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.duanmauchinh.Model.InvoiceDetail
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.list_item_hoa_don.view.*

class InvoiceAdapter(var context: Context?, var invoiceDetailList: ArrayList<InvoiceDetail>): RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): InvoiceHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.list_item_hoa_don,p0,false)
        return InvoiceHolder(view)
    }

    override fun getItemCount(): Int {
        return invoiceDetailList.size
    }

    override fun onBindViewHolder(holder: InvoiceHolder, p1: Int) {
        var invoicedetail = invoiceDetailList.get(p1)
        holder.imgCLoseHoaDon.setOnClickListener { Toast.makeText(context,"xin chào các bạn",Toast.LENGTH_LONG).show() }
    }

    class InvoiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMaSachHD = itemView.txtMaSachHD
        var txtSoLuongHD = itemView.txtSoLuongHD
        var txtGiaBiaHD = itemView.txtGiaBiaHD
        var txtThanhTienHD = itemView.txtThanhTienHD
        var imgCLoseHoaDon = itemView.imgCloseHoaDon
    }
}