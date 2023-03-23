package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignInActivity.KEY2)
        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val userId = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        val mailText = findViewById<TextView>(R.id.btnMail)
        val idText = findViewById<TextView>(R.id.btnUniqueId)

        welcomeText.text = "welcome $name"
        mailText.text = "mail Id : $mail"
        idText.text = "User Id : $userId"

        val addbutton =findViewById<Button>(R.id.btnAdd)

        addbutton.setOnClickListener{
            intent = Intent(applicationContext,ContactManagerScreen :: class.java)
            startActivity(intent)
        }

    }
}