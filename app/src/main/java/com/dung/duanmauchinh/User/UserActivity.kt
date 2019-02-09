package com.dung.duanmauchinh.User

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.TextView
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.LoginSignin.LoginActivity
import com.dung.duanmauchinh.User.Fragment.EditUserFragment
import com.dung.duanmauchinh.User.Fragment.UserListFragment
import com.dung.duanmauchinh.User.Fragment.PresentUserFragment
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.app_bar_user.*
import kotlinx.android.synthetic.main.nav_header_user.view.*

open class UserActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragment: Fragment? = null
    var txtTenNguoiDungDraw: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)

        fragment = UserListFragment()
        this.fragmentNullException(fragment!!, R.id.flNguoiDung)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var headerview = nav_view.getHeaderView(0)
        txtTenNguoiDungDraw = headerview.txtTenNguoiDungDraw
        this.getUsername()
    }

    fun getUsername(){
        var sharedPreferences = getSharedPreferences("USER_REMEMBER", Context.MODE_PRIVATE)
        var username = sharedPreferences.getString("username","Administrator")
        if(username == "admin"){
            txtTenNguoiDungDraw?.text = "Administrator"
        }else {
            txtTenNguoiDungDraw?.text = username
        }
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
            R.id.navDanhSachND -> fragment = UserListFragment()
            R.id.navChinhSuaND -> fragment = EditUserFragment()
            R.id.navGioiThieuND -> fragment = PresentUserFragment()
            R.id.navDangXuatND ->{
                var intent = Intent(this@UserActivity,LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                this.finish()
            }
        }

        this.fragmentNullException(fragment!!, R.id.flNguoiDung)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
