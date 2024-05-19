package com.tarun.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.tarun.quizapp.databinding.ActivitySplashscreenBinding

class splashscreen : AppCompatActivity() {
    lateinit var splashscreenBinding: ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashscreenBinding=ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(splashscreenBinding.root)
        val alphaanimation= AnimationUtils.loadAnimation(this,R.anim.animsplash)
        splashscreenBinding.textView1.startAnimation(alphaanimation)

        val handler= Handler(Looper.getMainLooper())
        handler.postDelayed(object: Runnable{
            override fun run() {
                val intent=Intent(this@splashscreen,loginpage::class.java)
                startActivity(intent)
                finish()
            }
        },5000)
    }
}