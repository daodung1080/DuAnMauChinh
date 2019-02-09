package com.dung.duanmauchinh.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.dung.duanmauchinh.Invoice.Fragment.FragmentInvoiceList
import com.dung.duanmauchinh.Model.Invoice
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.list_item_invoice.view.*

class InvoiceAdapter
    (var context: Context?, var fragment: FragmentInvoiceList, var invoiceList: ArrayList<Invoice>): BaseAdapter() {

    class ViewHolder(view: View){
        var txtMaHoaDonHD: TextView
        var txtNgayMuaHD: TextView
        var imgCloseHoaDon: ImageView
        var imgEditHoaDon: ImageView

        init {
            txtMaHoaDonHD = view.txtMaHoaDonHD
            txtNgayMuaHD = view.txtNgayMuaHD
            imgCloseHoaDon = view.imgCloseHoaDon
            imgEditHoaDon =  view.imgEditHoaDon
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if(convertView == null){
            var layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.list_item_invoice,null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        var invoice = getItem(position) as Invoice
        viewHolder.txtMaHoaDonHD.text = invoice.mahoadon
        viewHolder.txtNgayMuaHD.text = invoice.ngaymua
        viewHolder.imgCloseHoaDon.setOnClickListener { fragment.removeInvoice(invoice.mahoadon!!,position) }
        viewHolder.imgEditHoaDon.setOnClickListener { fragment.editInvoice(invoice.mahoadon!!,position) }
        return view as View
    }

    override fun getItem(position: Int): Any {
        return invoiceList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return invoiceList.size
    }

}