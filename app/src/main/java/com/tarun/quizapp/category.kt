package com.tarun.quizapp

class Question(
    val q:String=" ",
    val a:String=" ",
    val b:String=" ",
    val c:String=" ",
    val d:String=" ",
    val ans:String=" "
) {
}


//package com.tarun.quizapp
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import com.google.firebase.FirebaseApp
//import com.google.firebase.database.*
//import com.tarun.quizapp.databinding.ActivityQuiztimeBinding
//
//class quiztime : AppCompatActivity() {
//
//    private lateinit var quiztimeBinding: ActivityQuiztimeBinding
//    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//    private lateinit var reference: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        quiztimeBinding = ActivityQuiztimeBinding.inflate(layoutInflater)
//        setContentView(quiztimeBinding.root)
//        supportActionBar?.title = "Quiz"
//
//        val selectedCategory = intent?.getStringExtra("selectedCategory")
//        if (selectedCategory != null) {
//            Log.d("History", "Selected category: $selectedCategory")
//            reference = database.reference.child(selectedCategory)
//        } else {
//            Log.e("History", "No category selected")
//            Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        quiztimeBinding.option1.setOnClickListener {
//            addQuestion()
//        }
//
//        quiztimeBinding.option2.setOnClickListener {
//            val q = "Which among the following Kavya of Sanskrit, deal with court intrigues & access to power of Chandragupta Maurya?"
//            val a = "Mrichhakatika"
//            val b = "Ritusamhara"
//            val c = "Kumarasambhava"
//            val d = "Mudrarakshahsa"
//            val ans = "d"
//            val question = Question(q, a, b, c, d, ans)
//            addTestQuestion(question)
//        }
//    }
//
//    private fun addQuestion() {
//        val q = "Which among the following Kavya of Sanskrit, deal with court intrigues & access to power of Chandragupta Maurya?"
//        val a = "Mrichhakatika"
//        val b = "Ritusamhara"
//        val c = "Kumarasambhava"
//        val d = "Mudrarakshahsa"
//        val ans = "d"
//        val question = Question(q, a, b, c, d, ans)
//
//        if (::reference.isInitialized) {
//            reference.child("2").setValue(question)
//                .addOnSuccessListener {
//                    Log.d("History", "Question added successfully")
//                    Toast.makeText(applicationContext, "Question added successfully", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { exception ->
//                    Log.e("History", "Failed to add question: ${exception.message}")
//                    Toast.makeText(applicationContext, "Failed to add question: ${exception.message}", Toast.LENGTH_SHORT).show()
//                }
//        } else {
//            Log.e("History", "Database reference is not initialized")
//            Toast.makeText(this, "Database reference is not initialized", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun addTestQuestion(question: Question) {
//        if (::reference.isInitialized) {
//            reference.child("test").child("testing").setValue(question)
//                .addOnSuccessListener {
//                    Log.d("History", "Test question added successfully")
//                    Toast.makeText(applicationContext, "Test question added successfully", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { exception ->
//                    Log.e("History", "Failed to add test question: ${exception.message}")
//                    Toast.makeText(applicationContext, "Failed to add test question: ${exception.message}", Toast.LENGTH_SHORT).show()
//                }
//        } else {
//            Log.e("History", "Database reference is not initialized")
//            Toast.makeText(this, "Database reference is not initialized", Toast.LENGTH_SHORT).show()
//        }
//    }
//}


