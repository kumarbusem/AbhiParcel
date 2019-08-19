package com.sendfast.abhiparcel.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sendfast.abhiparcel.R

class OffersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)
        supportActionBar?.title = "Offers"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
