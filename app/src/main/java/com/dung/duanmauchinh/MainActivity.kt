package com.dung.duanmauchinh

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.dung.duanmauchinh.LoginSignin.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAnimation()
        timerSwitch()

    }

    fun timerSwitch(){
        var timer = Timer()
        timer.schedule(4100){
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            this@MainActivity.finish()
        }
    }

    fun initAnimation(){
        var anim_loading: Animation = AnimationUtils.loadAnimation(this,R.anim.anim_loading)
        imgLoading.startAnimation(anim_loading)
    }

}

