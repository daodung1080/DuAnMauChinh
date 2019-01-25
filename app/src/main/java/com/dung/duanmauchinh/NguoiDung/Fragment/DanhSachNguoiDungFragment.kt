package com.dung.duanmauchinh.NguoiDung.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.duanmauchinh.Adapter.UserAdapter
import com.dung.duanmauchinh.DAO.UserDAO
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.fragment_danh_sach_nguoi_dung.view.*

class DanhSachNguoiDungFragment : Fragment() {

    var list: ArrayList<User> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_danh_sach_nguoi_dung, container, false)
        var rvNguoiDung = rootview.rvNguoiDung

        var userDAO = UserDAO(context)

        for (i in 0..5){
            var username = "username$i"
            var password = "password$i"
            var phone = "phone$i"
            var fullname = "fullname$i"
            var user = User(username, password, phone, fullname)
            userDAO.insertUser(user)
        }

        list = userDAO.getAllUser()
        var adapter = UserAdapter(context,this,list)
        rvNguoiDung.layoutManager = LinearLayoutManager(context)
        rvNguoiDung.adapter = adapter

        return rootview
    }

    fun removeUser(username :String){
        var userDAO = UserDAO(context)
        userDAO.deleteUser(username)
        list = userDAO.getAllUser()
        Toast.makeText(context,"Xóa người dùng $username thành công", Toast.LENGTH_LONG).show()
    }

}