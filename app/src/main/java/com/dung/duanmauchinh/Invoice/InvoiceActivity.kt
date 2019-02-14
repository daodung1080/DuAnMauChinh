package com.dung.duanmauchinh.Invoice

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.Invoice.Fragment.FragmentInvoiceAddition
import com.dung.duanmauchinh.Invoice.Fragment.FragmentInvoiceDetail
import com.dung.duanmauchinh.Invoice.Fragment.FragmentInvoiceList
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_invoice.*

class InvoiceActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = FragmentInvoiceAddition()
            }
            R.id.navigation_dashboard -> {
                fragment = FragmentInvoiceDetail()
            }
            R.id.navigation_notifications -> {
                fragment = FragmentInvoiceList()
            }
        }
        this.fragmentNullException(fragment!! , R.id.flHoaDon)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        fragmentNullException(FragmentInvoiceAddition() , R.id.flHoaDon)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
