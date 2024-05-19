package com.tarun.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.tarun.quizapp.databinding.ActivitySignuppageBinding

class signuppage : AppCompatActivity() {

    lateinit var signuppageBinding: ActivitySignuppageBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signuppageBinding = ActivitySignuppageBinding.inflate(layoutInflater)
        setContentView(signuppageBinding.root)

        signuppageBinding.button.setOnClickListener {
            val email = signuppageBinding.registermail.text.toString()
            val password = signuppageBinding.registerpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    signuppageBinding.progressBar.visibility = View.VISIBLE

                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                        signuppageBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "User added successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@signuppage, loginpage::class.java)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                        signuppageBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "Sign up failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Invalid email format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
