package com.dung.duanmauchinh

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_toast.view.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun activityAnim(){
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
    }

    fun fragmentNullException(fragment: Fragment, idLayout: Int){
        if(fragment != null){
            var fragmentManager = supportFragmentManager
            var fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(idLayout,fragment)
            fragmentTransaction.commit()
        }
    }

    fun showMessage(message: String, switch: Boolean){
        var view = layoutInflater.inflate(R.layout.custom_toast,null)
        var txtCustomToast: TextView = view.txtCustomToast
        var imgCustomToast: ImageView = view.imgCustomToast
        var llCustomToast: LinearLayout = view.llCustomToast
        if(switch == true){
            imgCustomToast.setImageResource(R.drawable.ic_check)
            llCustomToast.setBackgroundResource(R.drawable.bg_done_toast)
            txtCustomToast.text = message

            var toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
        if(switch == false){
            txtCustomToast.text = message

            var toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
    }

}