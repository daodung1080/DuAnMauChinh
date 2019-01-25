package com.dung.duanmauchinh.HoaDon

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.HoaDon.Fragment.FragmentDanhSachHoaDon
import com.dung.duanmauchinh.HoaDon.Fragment.FragmentHoaDonChiTiet
import com.dung.duanmauchinh.HoaDon.Fragment.FragmentThemHoaDon
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_hoa_don.*

class HoaDonActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = FragmentThemHoaDon()
            }
            R.id.navigation_dashboard -> {
                fragment = FragmentHoaDonChiTiet()
            }
            R.id.navigation_notifications -> {
                fragment = FragmentDanhSachHoaDon()
            }
        }
        this.fragmentNullException(fragment!! , R.id.flHoaDon)
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoa_don)

        var fragment = FragmentDanhSachHoaDon()
        fragmentNullException(fragment!! , R.id.flHoaDon)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
