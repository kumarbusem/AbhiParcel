package com.sendfast.abhiparcel.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.sendfast.abhiparcel.R
import com.sendfast.abhiparcel.utils.Constants
import kotlinx.android.synthetic.main.activity_select_city.*

class SelectCityActivity : AppCompatActivity() {

    var city : String = ""
     var address = ""
     var latLng: LatLng? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_city)
        supportActionBar?.title = "Select City"
//        actionBar.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        layout_city1.setOnClickListener {
            city = "Hyderabad"
            placePickerMap()
        }
        layout_city2.setOnClickListener {
            city = "Bangalore"
            placePickerMap()
        }
        layout_city3.setOnClickListener {
            city = "Chennai"
            placePickerMap()
        }
        layout_city4.setOnClickListener {
            city = "Kolkata"
            placePickerMap()
        }
        layout_city5.setOnClickListener {
            city = "Kerala"
            placePickerMap()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.e("OnActiivityResult", "starting")
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Log.e("OnActiivityResult", "Inside1")
                val tempplace = PlacePicker.getPlace(data, this)
                if (tempplace.name.length <= 1) run {
                    Toast.makeText(this, "Not valid place", Toast.LENGTH_LONG).show()
                } else {
                    val place = PlacePicker.getPlace(data, this)
                    if (place.name.toString()[0].toInt() <= 57 && (place.name.toString()[0].toInt() >= 48) and (place.name.toString()[1].toInt() <= 57) && place.name.toString()[1].toInt() >= 48) {
                        address = place.address as String
                    } else {
                        address = place.name.toString() + ", " + place.address as String
                    }
                    latLng = place.latLng
                    val returnIntent = Intent()
                    returnIntent.putExtra("address", address)
                    returnIntent.putExtra("lat", latLng?.latitude)
                    returnIntent.putExtra("long", latLng?.longitude)
                    returnIntent.putExtra("city", city)
                    setResult(Activity.RESULT_OK, returnIntent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }
            }
        }
        Log.e("OnActiivityResult", "Ending")
    }


    private fun placePickerMap(){
        val builder = PlacePicker.IntentBuilder()
        try
        {
            Log.e("PlacePicker", "Before starting")
            this.startActivityForResult(builder.build(this), 1)
            Log.e("PlacePicker", "After starting")
        }
        catch (e: GooglePlayServicesRepairableException)
        {
            e.printStackTrace()
        }
        catch (e: GooglePlayServicesNotAvailableException)
        {
            e.printStackTrace()
        }
    }

}
