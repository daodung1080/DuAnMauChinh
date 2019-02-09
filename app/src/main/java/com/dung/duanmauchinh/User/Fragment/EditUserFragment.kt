package com.dung.duanmauchinh.User.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dung.duanmauchinh.DAO.UserDAO
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.dialog_edit_user.view.*
import kotlinx.android.synthetic.main.fragment_edit_user.view.*

class EditUserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview: View = inflater.inflate(R.layout.fragment_edit_user,container,false)

        var edtNewPassword = rootview.edtPassMoi
        var edtRepeatPassword = rootview.edtNhapLaiPassMoi
        var btnDangKy = rootview.btnDangKy

        var sharedPreferences = context!!.getSharedPreferences("USER_REMEMBER",Context.MODE_PRIVATE)
        var username = sharedPreferences.getString("username",null)
        var password = sharedPreferences.getString("password",null)

        if(username == "admin"){
            edtNewPassword.isEnabled = false
            edtRepeatPassword.isEnabled = false
            btnDangKy.setOnClickListener { showMessage("Bạn không thể chỉnh sửa tài khoản admin",false,context) }
        }else {

            btnDangKy.setOnClickListener {
                var pass = edtNewPassword.text.toString()
                var newpass = edtRepeatPassword.text.toString()
                if(pass.isEmpty() || newpass.isEmpty()){
                    showMessage("Vui lòng không để trống",false,context)
                }
                else if(!newpass.equals(pass)){
                    showMessage("Vui lòng nhập lại đúng với ô trên",false,context)
                }
                else if(!pass.equals(password)){
                    showMessage("Bạn đã nhập sai password",false,context)
                }
                else{
                    var userDAO = UserDAO(context)
                    var list: ArrayList<User> = ArrayList()
                    list = userDAO.getAllUserByUsername(username)
                    var user = list.get(0)

                    var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)

                    alertDialog.setTitle("Chỉnh sửa thông tin người dùng")
                    alertDialog.setIcon(R.drawable.img_nguoidung_1)

                    var view = layoutInflater.inflate(R.layout.dialog_edit_user,null)
                    var edtEditFullname = view.edtEditFullname
                    var edtEditPhone = view.edtEditPhone
                    var edtEditPassword = view.edtEditPassword

                    edtEditFullname.setText(user.fullname)
                    edtEditPhone.setText(user.phone)
                    edtEditPassword.setText(user.password)

                    alertDialog.setView(view)

                    alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        var fullname = edtEditFullname.text.toString()
                        var phone = edtEditPhone.text.toString()
                        var pass = edtEditPassword.text.toString()

                        if(fullname.isEmpty()){
                            showMessage("Vui lòng không để trống họ tên",true,context)
                        }
                        else if(phone.isEmpty()){
                            showMessage("Vui lòng không để trống số điện thoại",true,context)
                        }
                        else if(pass.length < 5){
                            showMessage("Password phải nhập hơn 5 ký tự",true,context)
                        }
                        else if(userDAO.editUser(username, User(null,pass,phone,fullname)) > 0){
                            showMessage("Chỉnh sửa người dùng $username thành công",true,context)
                        }
                        else if(userDAO.editUser(username, User(null,pass,phone,fullname)) <= 0){
                            showMessage("Chỉnh sửa người dùng thất bại",true,context)
                        }
                    })
                    alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                    var dialog = alertDialog.create()
                    dialog.show()
                }
            }

        }
        return rootview
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