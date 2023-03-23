package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.firebase.SignInActivity.mail"
        const val KEY2 = "com.example.firebase.SignInActivity.name"
        const val KEY3 = "com.example.firebase.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.usernameEditText)

        signInButton.setOnClickListener{
            // take ref till node User from firebase to get values of the users

            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            } else{
                Toast.makeText(this, "please enter User Name", Toast.LENGTH_SHORT).show()

            }

        }
    } // onCreate method is complete here

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(uniqueId).get().addOnSuccessListener {
            // if user exist in the DataBase or not
            if(it.exists()){
                //welcome user in your app, with intent and also pass
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                var intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, userId.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this,"User does not exis",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }
}