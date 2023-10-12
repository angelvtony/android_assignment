package com.acabes.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val greetingMessageTextView = findViewById<TextView>(R.id.greetingMessage)


        val username = intent.getStringExtra("username")


        greetingMessageTextView.text = "Hi, $username"
    }
}