package com.dung.duanmauchinh.User.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dung.duanmauchinh.Adapter.UserAdapter
import com.dung.duanmauchinh.DAO.UserDAO
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_user_list.view.*

class UserListFragment : Fragment() {

    var list: ArrayList<User>? = null
    var adapter: UserAdapter? = null
    var mode_private = Context.MODE_PRIVATE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_user_list, container, false)
        var rvNguoiDung = rootview.rvNguoiDung
        list = ArrayList()
        var userDAO = UserDAO(context)

        list = userDAO.getAllUser()
        adapter = UserAdapter(context,this,list!!)
        rvNguoiDung.layoutManager = LinearLayoutManager(context)
        rvNguoiDung.adapter = adapter

        return rootview
    }

    fun removeUser(username :String,position: Int){
        var sharedPreferences = context!!.getSharedPreferences("USER_REMEMBER",mode_private)
        var usernameRemember = sharedPreferences.getString("username",null)

        var userDAO = UserDAO(context)

        if(username != usernameRemember){
            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle("Xóa thông tin người dùng")
            alertDialog.setIcon(R.drawable.img_nguoidung_1)
            alertDialog.setMessage("Bạn có chắc muốn xóa người dùng $username? ")

            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                if(userDAO.deleteUser(username) > 0){
                    list!!.removeAt(position)
                    adapter?.notifyDataSetChanged()
                    this.showMessage("Xóa thành công người dùng $username",false,context)
                }
                else if(userDAO.deleteUser(username) <= 0){
                    showMessage("Xóa người dùng thất bại",false,context)
                }
            })
            alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            var dialog = alertDialog.create()
            dialog.show()

        }
        else{
            showMessage("Bạn không thể xóa tài khoản của chính mình",false,context)
        }
    }

    fun showMessage(message: String, switch: Boolean,context: Context?){
        var view = layoutInflater.inflate(R.layout.custom_toast,null)
        var txtCustomToast: TextView = view.txtCustomToast
        var imgCustomToast: ImageView = view.imgCustomToast
        var llCustomToast: LinearLayout = view.llCustomToast
        if(switch == true){
            imgCustomToast.setImageResource(R.drawable.ic_check)
            llCustomToast.setBackgroundResource(R.drawable.bg_done_toast)
            txtCustomToast.text = message

            var toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
        if(switch == false){
            var view = layoutInflater.inflate(R.layout.custom_toast,null)
            var txtCustomToast: TextView = view.txtCustomToast
            txtCustomToast.text = message

            var toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
    }

}
