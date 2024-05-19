package com.tarun.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.tarun.quizapp.databinding.ActivityResetpasswordBinding

class resetpassword : AppCompatActivity() {
    lateinit var activitySignuppageBinding: ActivityResetpasswordBinding
    val auth:FirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignuppageBinding=ActivityResetpasswordBinding.inflate(layoutInflater)
        setContentView(activitySignuppageBinding.root)
        supportActionBar?.title="Forget Password"
        activitySignuppageBinding.button3.setOnClickListener {

            val mail=activitySignuppageBinding.registermail.text.toString()
            auth.sendPasswordResetEmail(mail).addOnSuccessListener {
                Toast.makeText(applicationContext,"Resent link send to your E-mail ID",Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}