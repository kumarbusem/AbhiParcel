package com.sendfast.abhiparcel.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sendfast.abhiparcel.R

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.title = "Previous Orders"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
