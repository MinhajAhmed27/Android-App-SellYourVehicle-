package com.example.app_38249

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler= Handler()
        handler.postDelayed({

            val inttent =Intent(this,MainActivity::class.java)
            startActivity(inttent)
            finish()
        },3000)
    }
}
