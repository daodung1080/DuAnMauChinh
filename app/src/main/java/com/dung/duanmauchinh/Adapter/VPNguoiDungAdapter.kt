package com.dung.duanmauchinh.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dung.duanmauchinh.NguoiDung.Fragment.ChinhSuaNguoiDungFragment
import com.dung.duanmauchinh.NguoiDung.Fragment.DanhSachNguoiDungFragment
import com.dung.duanmauchinh.NguoiDung.Fragment.GioiThieuNguoiDungFragment

class VPNguoiDungAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        var fragment: Fragment? = null
        when(p0){
            0 -> fragment = DanhSachNguoiDungFragment()
            1 -> fragment = ChinhSuaNguoiDungFragment()
            2 -> fragment = GioiThieuNguoiDungFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 3
    }
}