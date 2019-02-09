package com.dung.duanmauchinh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Model.BillDetail
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.list_item_invoice_detail.view.*

class BillAdapter(var context: Context?, var list: ArrayList<BillDetail>): RecyclerView.Adapter<BillAdapter.BillViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BillViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.list_item_invoice_detail,p0,false)
        return BillViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BillViewHolder, p1: Int) {
        var billDetail = list.get(p1)
        holder.txtMaSachHDCT.text = billDetail.masach
        holder.txtSoLuongHDCT.text = billDetail.soluong.toString()
        holder.txtGiaBiaHDCT.text = billDetail.giabia.toString()
        holder.txtThanhTienHDCT.text = billDetail.thanhtien.toString()
    }

    class BillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMaSachHDCT = itemView.txtMaSachHDCT
        var txtSoLuongHDCT = itemView.txtSoLuongHDCT
        var txtGiaBiaHDCT = itemView.txtGiaBiaHDCT
        var txtThanhTienHDCT = itemView.txtThanhTienHDCT
    }
}