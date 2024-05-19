package com.tarun.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tarun.quizapp.databinding.ActivityLoginpageBinding

class loginpage : AppCompatActivity() {
    val auth:FirebaseAuth=FirebaseAuth.getInstance()
    lateinit var loginpageBinding: ActivityLoginpageBinding
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginpageBinding=ActivityLoginpageBinding.inflate(layoutInflater)
        setContentView(loginpageBinding.root)
        val googlebutton= loginpageBinding.googlebutton.getChildAt(0) as TextView
        googlebutton.setText("Continue with google")
        googlebutton.setTextColor(Color.BLACK)
        googlebutton.textSize = 18F
        registerresultlauncher()
        loginpageBinding.signin.setOnClickListener {
            val mail:String=loginpageBinding.mailid.text.toString()
            val password:String=loginpageBinding.password.text.toString()
            println(mail)
            println(password)
            verifyandsignin(mail,password)
        }

        loginpageBinding.forgotpasswordtext.setOnClickListener {
            val intent=Intent(this@loginpage,resetpassword::class.java)
            startActivity(intent)
        }
        loginpageBinding.googlebutton.setOnClickListener{
            signingoogle()
        }
    }

    fun verifyandsignin(mail:String,password:String){


        auth.signInWithEmailAndPassword(mail,password).addOnSuccessListener {
            Toast.makeText(applicationContext,"Welcome back User",Toast.LENGTH_SHORT).show()
            val intent=Intent(this@loginpage, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Sign up failed: ${it.message}",Toast.LENGTH_SHORT).show()
        }
    }

    fun signingoogle(){

        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken("452939284188-g4n6une6om9a9qsrifosbsdfaj703p1c.apps.googleusercontent.com")
            .build()

        googleSignInClient= GoogleSignIn.getClient(this@loginpage,gso)

        signin()
    }

    fun signin(){
        val signInintent: Intent= googleSignInClient.signInIntent
        activityResultLauncher.launch(signInintent)
    }

    fun registerresultlauncher(){
        activityResultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {result ->
                val code= result.resultCode
                val data=result.data
                if(code== RESULT_OK && data!=null){
                    val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                    firebasesigninwithgoogle(task)
                }

            }
        )
    }

    fun firebasesigninwithgoogle(task:Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            Toast.makeText(applicationContext,"Welcome user",Toast.LENGTH_SHORT).
                    show()
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            firebasegoogleacount(account)
        }catch (e: ApiException){
            Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    fun firebasegoogleacount(account: GoogleSignInAccount){
        val authcred= GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(authcred)
    }
}