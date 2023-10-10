package com.acabes.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val user = "Admin"
    val pass = "Admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = findViewById<EditText>(R.id.userName)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            val enteredUser = userName.text.toString()
            val enteredPass = password.text.toString()
            if (enteredUser == user && enteredPass == pass){

                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}