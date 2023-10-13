package com.acabes.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val greetingMessageTextView = findViewById<TextView>(R.id.greetingMessage)
        val master =findViewById<Button>(R.id.master)
        val visa =findViewById<Button>(R.id.visa)
        val visaCard = VisaCard()
        val fragmentCard = CardFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameFragment, visaCard)
            commit()
        }


        master.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameFragment, visaCard)
                commit()
            }
        }
        visa.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameFragment, fragmentCard)
                commit()
            }
        }

        val username = intent.getStringExtra("username")
        greetingMessageTextView.text = "Hi, $username"
    }
}
