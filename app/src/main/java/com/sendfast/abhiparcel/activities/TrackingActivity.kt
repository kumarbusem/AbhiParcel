package com.sendfast.abhiparcel.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.sendfast.abhiparcel.R
import com.sendfast.abhiparcel.utils.Constants
import kotlinx.android.synthetic.main.activity_tracking.*

class TrackingActivity : AppCompatActivity(), OnMapReadyCallback {

    var bitmapdraw_biker: BitmapDrawable? = null
    var bm_big_biker: Bitmap? = null
    var bm_small_biker: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)
        supportActionBar?.title = "Order Accepted"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        callRiderButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            val phn_num = "tel:" + "04023351333"
            callIntent.data = Uri.parse(phn_num)
            startActivity(callIntent)
        }

        initMap()
    }
    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.status_location) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap?) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        googleMap?.isMyLocationEnabled = true
        googleMap?.uiSettings?.isCompassEnabled = false
        googleMap?.uiSettings?.isMyLocationButtonEnabled = false
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
        val latLng = LatLng(17.426489, 78.4385)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.toFloat()))
        bitmapdraw_biker = resources.getDrawable(R.drawable.biker_250) as BitmapDrawable
        bm_big_biker = bitmapdraw_biker!!.bitmap
        bm_small_biker = Bitmap.createScaledBitmap(bm_big_biker, 110, 110, false)
        googleMap?.addMarker(MarkerOptions()
                .flat(false)
                .icon(BitmapDescriptorFactory.fromBitmap(bm_small_biker))
                .anchor(0.5f, 0.5f)
                .position(latLng)
        )
        bitmapdraw_biker = resources.getDrawable(R.drawable.green_marker) as BitmapDrawable
        bm_big_biker = bitmapdraw_biker!!.bitmap
        bm_small_biker = Bitmap.createScaledBitmap(bm_big_biker, 110, 110, false)
        googleMap?.addMarker(MarkerOptions()
            .flat(false)
            .icon(BitmapDescriptorFactory.fromBitmap(bm_small_biker))
            .anchor(0.5f, 0.95f)
            .position(Constants.PickupLatlang)
        )
        bitmapdraw_biker = resources.getDrawable(R.drawable.red_marker) as BitmapDrawable
        bm_big_biker = bitmapdraw_biker!!.bitmap
        bm_small_biker = Bitmap.createScaledBitmap(bm_big_biker, 110, 110, false)
        googleMap?.addMarker(MarkerOptions()
            .flat(false)
            .icon(BitmapDescriptorFactory.fromBitmap(bm_small_biker))
            .anchor(0.5f, 0.95f)
            .position(Constants.DestinationLatlang))
        val bc = LatLngBounds.Builder()
        bc.include(Constants.PickupLatlang)
        bc.include(Constants.DestinationLatlang)
        bc.include(latLng)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 70))
//        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(),
//                (this.resources.displayMetrics.widthPixels * 0.6) as Int,
//                (this.resources.displayMetrics.heightPixels * 0.35) as Int, 0)
//        )
    }
}


