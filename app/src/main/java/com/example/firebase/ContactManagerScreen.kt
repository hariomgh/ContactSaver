package com.example.firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactManagerScreen : AppCompatActivity() {
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_manager_screen)

        val addbutton = findViewById<Button>(R.id.btnAddCont)
        val contactName = findViewById<TextInputEditText>(R.id.contName)
        val contactMail = findViewById<TextInputEditText>(R.id.contMail)

        addbutton.setOnClickListener{
            val name = contactName.text.toString()
            val contactmail = contactMail.text.toString()

            val contact = Contact(name, contactmail)
            database = FirebaseDatabase.getInstance().getReference("Contacts")

            database.child(name).setValue(contact).addOnSuccessListener{
                contactName.text?.clear()
                contactMail.text?.clear()

                Toast.makeText(this, "contact added to the database successfully", Toast.LENGTH_SHORT).show()
            }



        }
    }
}