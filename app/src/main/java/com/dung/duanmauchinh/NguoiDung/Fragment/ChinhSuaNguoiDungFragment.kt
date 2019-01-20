package com.dung.duanmauchinh.NguoiDung.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.R

class ChinhSuaNguoiDungFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview: View = inflater.inflate(R.layout.fragment_chinh_sua_nguoi_dung,container,false)
        return rootview
    }

}