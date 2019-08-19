package com.sendfast.abhiparcel.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sendfast.abhiparcel.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.title = "Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
