package com.dung.duanmauchinh.NguoiDung

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_nguoi_dung.*
import kotlinx.android.synthetic.main.app_bar_nguoi_dung.*

class NguoiDungActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nguoi_dung)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navDanhSachND -> {
                // Handle the camera action
            }
            R.id.navThemND -> {

            }
            R.id.navChinhSuaND -> {

            }
            R.id.navGioiThieuND -> {

            }
            R.id.navDangXuatND -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
