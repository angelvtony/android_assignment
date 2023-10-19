package com.acabes.loginscreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.acabes.loginscreen.apicred.ApiInterface
import com.acabes.loginscreen.apicred.models.Cred
import com.acabes.loginscreen.apicred.RetrofitHelper
import com.acabes.loginscreen.apicred.models.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences


    private fun Context.startNewActivity(destination: Class<*>, key: String, value: String) {
        val intent = Intent(this, destination)
        intent.putExtra(key, value)
        startActivity(intent)
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = findViewById<EditText>(R.id.userName)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val signUpTextView = findViewById<TextView>(R.id.sign)

        if (sharedPreferences.getBoolean("isLoggedIn",false)){
            startNewActivity(Home::class.java,"enteredUser","")
        }
        login.setOnClickListener {
            val enteredUser = userName.text.toString()
            val enteredPass = password.text.toString()

            val userApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)



            GlobalScope.launch {
                val data = Cred(enteredUser, enteredPass )
                val result = userApi.postData(data)
                if (result.isSuccessful) {
                    Log.d("Angel ", result.body().toString())
                    val editor = sharedPreferences.edit()
                    editor.putString("username", enteredUser)
                    editor.putString("password",enteredPass)
                    editor.putString("firstName",result.body()?.firstName)
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()


                    runOnUiThread {
                        startNewActivity(Home::class.java, "enteredUser", enteredUser)
                    }

                }
            }
        }
        signUpTextView.setOnClickListener {
            startNewActivity(SignUp::class.java, "enteredUser", "message",)
        }
    }


}




