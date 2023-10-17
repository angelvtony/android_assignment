package com.acabes.loginscreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.acabes.loginscreen.quotapi.QuotesApi
import com.acabes.loginscreen.quotapi.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        val textView = findViewById<TextView>(R.id.textView)
        val refreshButton = findViewById<Button>(R.id.refreshButton)
        refreshButton.setOnClickListener {
            GlobalScope.launch {
                val result = quotesApi.getQuotes()
                if (result.isSuccessful) {
                    val quotes = result.body()
                    if (!quotes.isNullOrEmpty()) {
                        runOnUiThread {
                            textView.text = quotes[0]
                        }
                    }
                }
                GlobalScope.launch {
                    val result = quotesApi.getQuotes()
                    if (result.isSuccessful) {
                        val quotes = result.body()
                        if (!quotes.isNullOrEmpty()) {
                            runOnUiThread {
                                textView.text = quotes[0]
                            }
                        }


                        val greetingMessageTextView = findViewById<TextView>(R.id.greetingMessage)
                        val master = findViewById<Button>(R.id.master)
                        val visa = findViewById<Button>(R.id.visa)
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

                        val username = intent.getStringExtra("enteredUser")
                        greetingMessageTextView.text = "Hi, $username"
                    }
                }
            }


        }
    }
}
