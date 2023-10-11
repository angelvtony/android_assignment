package com.acabes.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {
    private val userList = ArrayList<User>()
    data class User(val id: String, val name: String, val email: String, val password: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            fun generateRandomUserId(): String {
                val charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                return (1..10)
                    .map { charset.random() }
                    .joinToString("")
            }
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val userId = generateRandomUserId()
                val user = User(userId, name, email, password)
                userList.add(user)
                Log.d("UserSignUp", "User registered with ID:  $userList ")
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}