package com.dung.duanmauchinh.NguoiDung.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Adapter.UserAdapter
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.R
import com.dung.duanmauchinh.SQLite.Database
import kotlinx.android.synthetic.main.fragment_danh_sach_nguoi_dung.view.*

class DanhSachNguoiDungFragment : Fragment() {

    var list: ArrayList<User> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootview: View = inflater.inflate(R.layout.fragment_danh_sach_nguoi_dung,container,false)

        var rvNguoiDung = rootview.rvNguoiDung
        var database = Database(context)
        for(i in 0..10){
            var username = "username $i"
            var password = "password $i"
            var phone = "phone $i"
            var fullname = "fullname $i"
            var user = User(username, password, phone, fullname)
            database.insertUser(user)
        }
        list = database.getAllUser()

        rvNguoiDung.layoutManager = LinearLayoutManager(context)
        rvNguoiDung.adapter = UserAdapter(context, list)

        return rootview
    }

}