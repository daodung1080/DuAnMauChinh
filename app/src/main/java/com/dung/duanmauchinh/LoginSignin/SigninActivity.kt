package com.dung.duanmauchinh.LoginSignin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.UserDAO
import com.dung.duanmauchinh.Model.User
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.dialog_sign_in_information.view.*

class SigninActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnDangKy.setOnClickListener { validateSignUp() }

    }

    fun validateSignUp(){
        var userDAO = UserDAO(this)
        var fullname = edtHoTenMoi.text.toString()
        if (fullname.isEmpty()){
            showMessage("Vui lòng không để trống họ tên",false)
            return
        }
        var username = edtUsername.text.toString()
        if(username.isEmpty() || username.length < 5){
            showMessage("Username phải nhập hơn 5 ký tự",false)
            return
        }
        var phone = edtPhoneMoi.text.toString()
        if(phone.isEmpty()){
            showMessage("Vui lòng không để trống sdt",false)
            return
        }
        var pass = edtPassMoi.text.toString()
        if(pass.isEmpty() || pass.length < 5){
            showMessage("Password phải nhập hơn 5 ký tự",false)
            return
        }
        var nhaplaipass = edtNhapLaiPassMoi.text.toString()
        if(!nhaplaipass.equals(pass)){
            showMessage("Vui lòng nhập trùng khớp",false)
            return
        }

        var result: Long = userDAO.insertUser(User(username,pass,phone,fullname))
        if(result > 0){
            showMessage("Thêm người dùng thành công",true)
            this.dialogInfomation(fullname,username,pass,phone)
        }
        else{
            showMessage("Thêm người dùng thất bại do nhập trùng Username",false)
        }

    }

    fun dialogInfomation(fullname: String,username: String,password: String,phone: String){
        var alertDialog = AlertDialog.Builder(this)
        var view = layoutInflater.inflate(R.layout.dialog_sign_in_information,null)

        alertDialog.setTitle("Thông tin đăng ký")
        alertDialog.setIcon(R.drawable.img_nguoidung_1)

        var txtThongTinHoTen = view.txtThongTinHoTen
        var txtThongTinUsername = view.txtThongTinUsername
        var txtThongtinPassword = view.txtThongTinPassword
        var txtThongTinSdt = view.txtThongTinSdt

        txtThongTinHoTen.setText(fullname)
        txtThongTinUsername.setText(username)
        txtThongtinPassword.setText(password)
        txtThongTinSdt.setText(phone)

        alertDialog.setOnCancelListener {
            startActivity(Intent(this@SigninActivity,LoginActivity::class.java))
            this.finish() }
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
            startActivity(Intent(this@SigninActivity,LoginActivity::class.java))
            this.finish()
        })
        alertDialog.setView(view)

        var dialog = alertDialog.create()
        dialog.show()
    }

}
