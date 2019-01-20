package com.dung.duanmauchinh

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.textclassifier.TextClassifier
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dung.duanmauchinh.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun activityAnim(){
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
    }

    fun toastDone(loinhan: String){
        var view = layoutInflater.inflate(R.layout.custom_toast,findViewById(R.id.llCustomToast))
        var txtCustomToast: TextView = view.findViewById(R.id.txtCustomToast)
        var imgCustomToast: ImageView = view.findViewById(R.id.imgCustomToast)
        var llCustomToast: LinearLayout = view.findViewById(R.id.llCustomToast)
        imgCustomToast.setImageResource(R.drawable.ic_done)
        llCustomToast.setBackgroundResource(R.drawable.bg_done_toast)
        txtCustomToast.text = loinhan

        var toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view
        toast.show()
    }

    fun toastCancel(loinhan: String){
        var view = layoutInflater.inflate(R.layout.custom_toast,findViewById(R.id.llCustomToast))
        var txtCustomToast: TextView = view.findViewById(R.id.txtCustomToast)
        txtCustomToast.text = loinhan

        var toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view
        toast.show()
    }

}