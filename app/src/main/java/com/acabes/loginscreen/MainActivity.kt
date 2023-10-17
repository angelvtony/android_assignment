package com.acabes.loginscreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.acabes.loginscreen.apicred.ApiInterface
import com.acabes.loginscreen.apicred.Cred
import com.acabes.loginscreen.apicred.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    fun Context.startNewActivity(destination: Class<*>, key: String, value: String) {
        val intent = Intent(this, destination)
        intent.putExtra(key, value)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userName = findViewById<EditText>(R.id.userName)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val signUpTextView = findViewById<TextView>(R.id.sign)
        login.setOnClickListener {
            val enteredUser = userName.text.toString()
            val enteredPass = password.text.toString()
            val userApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
            GlobalScope.launch {
                val data = Cred(enteredUser, enteredPass)
                val result = userApi.postData(data)
                if (result.isSuccessful) {
                    Log.d("Angel ", result.body().toString())
                    runOnUiThread {
                        startNewActivity(Home::class.java, "enteredUser", enteredUser)
                    }

                }
            }
        }
        signUpTextView.setOnClickListener {
            startNewActivity(SignUp::class.java, "enteredUser", "message")
        }
    }
}


