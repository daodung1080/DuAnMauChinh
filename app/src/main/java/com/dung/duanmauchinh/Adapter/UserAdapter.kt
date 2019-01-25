package com.dung.duanmauchinh.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.NguoiDung.Fragment.DanhSachNguoiDungFragment
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.list_item_nguoi_dung.view.*

class UserAdapter(var context: Context?,var fragment: DanhSachNguoiDungFragment, var list: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserAdapter.UserHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_nguoi_dung,p0,false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(userholder: UserAdapter.UserHolder, p1: Int) {

        var user: User = list.get(p1)
        userholder.txtUsernameNguoiDung.text = user.username

        userholder.imgCloseNguoiDung.setOnClickListener {
            fragment.removeUser(user.username)
        }

    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtUsernameNguoiDung = itemView.txtUsernameNguoiDung
        var imgCloseNguoiDung = itemView.imgCloseNguoiDung
    }


}