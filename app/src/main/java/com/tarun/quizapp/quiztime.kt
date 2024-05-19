package com.tarun.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tarun.quizapp.databinding.ActivityQuiztimeBinding

class quiztime : AppCompatActivity() {

    private lateinit var quiztimeBinding: ActivityQuiztimeBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        quiztimeBinding = ActivityQuiztimeBinding.inflate(layoutInflater)
        setContentView(quiztimeBinding.root)
        supportActionBar?.title = "Quiz"

        reference = database.reference.child("quizdatabase").child("History")
            .child("1").child("q")

        // Log the reference path

        // Set click listener on option2 button
        quiztimeBinding.option2.setOnClickListener {
            adddata()
            println("Done")
        }
    }

    // Function to add data listener
    private fun adddata() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("QuizTime", "onDataChange triggered")
                Toast.makeText(this@quiztime, "Data changed!", Toast.LENGTH_SHORT).show()
                // Check if snapshot exists
                if (snapshot.exists()) {
                    // Loop through children and get data
                    for (each in snapshot.children) {
                        val data = each.value.toString() // Get value of each child
                        // Update the question text on the UI
                        quiztimeBinding.question.text = data
                        // Log the retrieved question data
                        Log.d("Quiztime","Question data: $data")
                    }
                } else {
                    Log.d("QuizTime", "No data found in snapshot")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Log database error
                Log.e("QuizTime", "Database Error: ${error.message}")
            }
        })
    }
}
