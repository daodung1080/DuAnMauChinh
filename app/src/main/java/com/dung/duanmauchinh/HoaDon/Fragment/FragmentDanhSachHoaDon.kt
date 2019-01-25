package com.dung.duanmauchinh.HoaDon.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Adapter.InvoiceAdapter
import com.dung.duanmauchinh.Model.InvoiceDetail
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.fragment_danh_sach_hoa_don.view.*

class FragmentDanhSachHoaDon : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_danh_sach_hoa_don,container,false)

        var rvHoaDon = rootview.rvHoaDon

        var list: ArrayList<InvoiceDetail> = ArrayList()

        rvHoaDon.layoutManager = LinearLayoutManager(context)
        rvHoaDon.adapter = InvoiceAdapter(context,list)

        return rootview
    }
}
