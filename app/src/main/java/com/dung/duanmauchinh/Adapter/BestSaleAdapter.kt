package com.dung.duanmauchinh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Model.BestSale
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.list_item_best_sale.view.*

class BestSaleAdapter(var context: Context, var list: ArrayList<BestSale>)
    : RecyclerView.Adapter<BestSaleAdapter.BestSaleViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BestSaleViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_best_sale,p0,false)
        return BestSaleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BestSaleViewHolder, p1: Int) {
        var bestSale = list.get(p1)
        holder.txtMaSachBanChay.text = bestSale.masach
        holder.txtSoLuongMuaBanChay.text = bestSale.soluongmua.toString()
    }

    class BestSaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMaSachBanChay = itemView.txtMaSachBanChay
        var txtSoLuongMuaBanChay = itemView.txtSoLuongMuaBanChay
    }
}