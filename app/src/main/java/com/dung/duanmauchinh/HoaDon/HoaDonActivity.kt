package com.dung.duanmauchinh.HoaDon

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.R

class HoaDonActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoa_don)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hoa_don,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.mnDanhSachHoaDon -> toastDone("Danh sách hóa đơn")
            R.id.mnTimKiemHoaDon -> toastCancel("Danh sách bán chạy")
        }
        return super.onOptionsItemSelected(item)
    }
}
