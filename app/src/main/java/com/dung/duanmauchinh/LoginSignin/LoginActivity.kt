package com.dung.duanmauchinh.LoginSignin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.UserDAO
import com.dung.duanmauchinh.MainScreenActivity
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.fillAllUserInformation()
        this.activityAnim()
        this.switchActivity()

    }

    fun fillAllUserInformation(){
        var sharedPreferences = getSharedPreferences("USER_REMEMBER_LAST_LOGIN", Context.MODE_PRIVATE)
        var username = sharedPreferences.getString("username",null)
        var password = sharedPreferences.getString("password",null)
        var remember = sharedPreferences.getBoolean("remember",false)

        edtUsername.setText(username)
        edtPassMoi.setText(password)
        cbNhoMatKhau.isChecked = remember
    }

    fun validateUser(){
        var userDAO = UserDAO(this)
        var username = edtUsername.text.toString()
        var password = edtPassMoi.text.toString()
        var isLogin = userDAO.checkLogin(username,password)
        var remember = cbNhoMatKhau.isChecked
        if(username.isEmpty() || password.isEmpty()){
            showMessage("Vui lòng không để trống username hoặc password",false)
        }
        else if(username == "admin" && password == "admin"){
            showMessage("Đăng nhập thành công tài khoản local",true)
            this.rememberUser(username,password)
            this.rememberUserForLastTimeLogin(username,password,false)
            this.switchActivityToMain()
        }
        else if(isLogin == true){
            showMessage("Đăng nhập thành công tài khoản $username",true)
            this.rememberUser(username,password)

            if(remember == true){
                this.rememberUserForLastTimeLogin(username,password,true)
            }
            else if(remember == false){
                this.rememberUserForLastTimeLogin(username,password,false)
            }

            this.switchActivityToMain()
        }
        else if(isLogin == false){
            showMessage("Đăng nhập thất bại do tài khoản không tồn tại",false)
        }
    }

    fun rememberUser(username: String, password: String){
        var sharedPreferences = getSharedPreferences("USER_REMEMBER", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
            editor.putString("username",username)
            editor.putString("password",password)
        editor.apply()
    }

    fun rememberUserForLastTimeLogin(username: String, password: String,remember: Boolean){
        var sharedPreferences = getSharedPreferences("USER_REMEMBER_LAST_LOGIN", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        if(remember == false){
            editor.clear()
        }else {
            editor.putString("username", username)
            editor.putString("password", password)
            editor.putBoolean("remember",remember)
        }
        editor.apply()
    }

    fun switchActivity(){

        txtDangKy.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SigninActivity::class.java))
            activityAnim()
        }
        btnDangNhap.setOnClickListener {
            this.validateUser()
        }

    }

    fun switchActivityToMain(){
        startActivity(Intent(this@LoginActivity, MainScreenActivity::class.java))
        activityAnim()
        this.finish()
    }

}
