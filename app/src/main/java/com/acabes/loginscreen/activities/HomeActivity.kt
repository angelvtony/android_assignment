package com.acabes.loginscreen.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.acabes.loginscreen.R
import com.acabes.loginscreen.fragments.MasterCardFragment
import com.acabes.loginscreen.fragments.VisaCardFragment
import com.acabes.loginscreen.network.QuotesApi
import com.acabes.loginscreen.network.RetrofitHelperQuotes
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val quotesApi = RetrofitHelperQuotes.getInstance().create(QuotesApi::class.java)
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

                        val firstName = sharedPreferences.getString("firstName","")
                        greetingMessageTextView.text = "Hi, $firstName"
                    }
                }
            }




}


