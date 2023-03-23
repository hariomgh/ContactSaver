package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSignup)
        val etname = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val Password = findViewById<TextInputEditText>(R.id.etPassword)
        val Username = findViewById<TextInputEditText>(R.id.etUsername)

        signButton.setOnClickListener{

            val name = etname.text.toString()
            val email = etEmail.text.toString()
            val uniqueId = Username.text.toString()
            val password = Password.text.toString()

            val user = User(name,email,password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user).addOnSuccessListener {

                etname.text?.clear()
                etEmail.text?.clear()
                Username.text?.clear()
                Password.text?.clear()

                Toast.makeText(this, "User-registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText( this, "Failed to register", Toast.LENGTH_SHORT).show()
            }

        }

        val signIntext = findViewById<TextView>(R.id.tvSignin)
        signIntext.setOnClickListener{
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}