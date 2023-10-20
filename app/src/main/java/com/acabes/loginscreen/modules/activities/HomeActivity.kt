package com.acabes.loginscreen.modules.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.acabes.loginscreen.viewmodel.QuotesViewModel
import com.acabes.loginscreen.R
import com.acabes.loginscreen.modules.fragments.MasterCardFragment
import com.acabes.loginscreen.modules.fragments.VisaCardFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)



        val textView = findViewById<TextView>(R.id.quoteSection)
        val refreshButton = findViewById<Button>(R.id.refreshButton)

        var viewModel = ViewModelProvider(this).get(QuotesViewModel::class.java)

        viewModel.quotes.observe(this) {

            textView.text = it[0]

        }

        refreshButton.setOnClickListener {
            viewModel.fetchQuotes()
        }

        val greetingMessageTextView = findViewById<TextView>(R.id.greetingMessage)
        val master = findViewById<Button>(R.id.master)
        val visa = findViewById<Button>(R.id.visa)
        val visaCardFragment = VisaCardFragment()
        val fragmentCard = MasterCardFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameFragment, visaCardFragment)
            commit()
        }


        master.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameFragment, visaCardFragment)
                commit()
            }
        }
        visa.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameFragment, fragmentCard)
                commit()
            }
        }

        val firstName = sharedPreferences.getString("firstName", "")
        greetingMessageTextView.text = "Hi, $firstName"
    }
}








