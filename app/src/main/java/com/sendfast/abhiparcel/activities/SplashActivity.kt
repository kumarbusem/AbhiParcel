package com.sendfast.abhiparcel.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sendfast.abhiparcel.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
            this@SplashActivity.finish()
            this@SplashActivity.startActivity(intent)
        }, 2000)

    }
}
