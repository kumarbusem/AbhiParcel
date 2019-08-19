package com.sendfast.abhiparcel.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import com.sendfast.abhiparcel.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.model.LatLng
import com.sendfast.abhiparcel.utils.Constants
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.dialog_select_itemtype.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var insideCity: Boolean = true
    var outsideCity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        take_permissions(this)
        view_flipper.setInAnimation(this, R.anim.view_transition_in_left)
        view_flipper.setOutAnimation(this, R.anim.view_transition_out_left)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        pickupCard.setOnClickListener {
            if(insideCity){
                placePickerMap(3)
            }
            if(outsideCity){
                val intent = Intent(this, SelectCityActivity::class.java)
                this.startActivityForResult(intent, 1)
            }
        }
        destinationCard.setOnClickListener {
            if(insideCity){
                placePickerMap(4)
            }
            if(outsideCity){
                val intent = Intent(this, SelectCityActivity::class.java)
                this.startActivityForResult(intent, 2)
            }
        }
        val headerview = navView.getHeaderView(0)
        val header = headerview.findViewById(R.id.nav_header) as LinearLayout
        header.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        item_type_card.setOnClickListener {
            itemTypeSelect()
        }
        search_button.setOnClickListener {

            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra("insideCity", insideCity)
            intent.putExtra("outsideCity", outsideCity)
            startActivity(intent)
        }
        text_view_all_offers.setOnClickListener {
            val intent = Intent(this, OffersActivity::class.java)
            startActivity(intent)
        }
        layout_inside_city.setOnClickListener {
            if(!insideCity){
                layout_inside_city.setBackgroundColor(Color.parseColor("#D51A1A"))
                image_inside_city.setImageResource(R.drawable.inside_city_white)
                text_inside_city.setTextColor(Color.parseColor("#FFFFFF"))

                layout_outside_city.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image_outside_city.setImageResource(R.drawable.express_delivery_icon)
                text_outside_city.setTextColor(Color.parseColor("#9C9C9C"))
                insideCity = true
                outsideCity = false

                pickup_text.text = "Pickup From"
                destination_text.text = "Delivery To"
                pickup_text.visibility = View.VISIBLE
                destination_text.visibility = View.VISIBLE
                pickup_location_text.visibility = View.GONE
                destination_location_text.visibility = View.GONE
            }
        }
        layout_outside_city.setOnClickListener {
            if(!outsideCity){
                layout_outside_city.setBackgroundColor(Color.parseColor("#D51A1A"))
                image_outside_city.setImageResource(R.drawable.express_delivery_icon_white)
                text_outside_city.setTextColor(Color.parseColor("#FFFFFF"))

                layout_inside_city.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image_inside_city.setImageResource(R.drawable.inside_city_icon)
                text_inside_city.setTextColor(Color.parseColor("#9C9C9C"))
                insideCity = false
                outsideCity = true

                pickup_text.text = "Pickup From"
                destination_text.text = "Delivery To"
                pickup_text.visibility = View.VISIBLE
                destination_text.visibility = View.VISIBLE
                pickup_location_text.visibility = View.GONE
                destination_location_text.visibility = View.GONE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                pickup_text.text = data?.getStringExtra("city")
                pickup_text.visibility = View.VISIBLE
                pickup_location_text.text = data?.getStringExtra("address")
                pickup_location_text.visibility = View.VISIBLE
                val latitude = data?.getDoubleExtra("lat", 0.0)
                val longitude = data?.getDoubleExtra("long", 0.0)
                Constants.PickupLatlang = LatLng(latitude!!, longitude!!)
            }
            if (requestCode == 2) {
                destination_text.text = data?.getStringExtra("city")
                destination_text.visibility = View.VISIBLE
                destination_location_text.text = data?.getStringExtra("address")
                destination_location_text.visibility = View.VISIBLE
                val latitude = data?.getDoubleExtra("lat", 0.0)
                val longitude = data?.getDoubleExtra("long", 0.0)
                Constants.DestinationLatlang = LatLng(latitude!!, longitude!!)
            }
            if (requestCode == 3) {
                val tempplace = PlacePicker.getPlace(data, this)
                if (tempplace.name.length <= 1) run {
                    Toast.makeText(this, "Not valid place", Toast.LENGTH_LONG).show()
                } else {
                    val place = PlacePicker.getPlace(data, this)
                    if (place.name.toString()[0].toInt() <= 57 && (place.name.toString()[0].toInt() >= 48) and (place.name.toString()[1].toInt() <= 57) && place.name.toString()[1].toInt() >= 48) {
                        pickup_location_text.text = place.address as String
                        pickup_location_text.visibility = View.VISIBLE
                        pickup_text.visibility = View.GONE
                    } else {
                        pickup_location_text.text = place.name.toString() + ", " + place.address as String
                        pickup_location_text.visibility = View.VISIBLE
                        pickup_text.visibility = View.GONE
                    }

                }
            }
            if (requestCode == 4) {
                val tempplace = PlacePicker.getPlace(data, this)
                if (tempplace.name.length <= 1) run {
                    Toast.makeText(this, "Not valid place", Toast.LENGTH_LONG).show()
                } else {
                    val place = PlacePicker.getPlace(data, this)
                    if (place.name.toString()[0].toInt() <= 57 && (place.name.toString()[0].toInt() >= 48) and (place.name.toString()[1].toInt() <= 57) && place.name.toString()[1].toInt() >= 48) {
                        destination_location_text.text = place.address as String
                        destination_location_text.visibility = View.VISIBLE
                        destination_text.visibility = View.GONE
                    } else {
                        destination_location_text.text = place.name.toString() + ", " + place.address as String
                        destination_location_text.visibility = View.VISIBLE
                        destination_text.visibility = View.GONE
                    }

                }
            }
        }
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun placePickerMap(placePickerNumber: Int){
        val builder = PlacePicker.IntentBuilder()
        try
        {
            Log.e("PlacePicker", "Before starting")
            this.startActivityForResult(builder.build(this), placePickerNumber)
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_offers -> {
                val intent = Intent(this, OffersActivity::class.java)
                startActivity(intent)
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_payments -> {
                val intent = Intent(this, PaymentsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orders -> {
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.nav_header -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("SetTextI18n")
    fun itemTypeSelect() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_select_itemtype)
        dialog.setTitle("Select package content")
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        dialog.window!!.attributes = lp
        Constants.Items_type = ""
        val d = dialog.documents_checkbox
        val c = dialog.cloths_checkbox
        val e = dialog.electronics
        val f = dialog.food
        val s = dialog.sports
        val h = dialog.house

        val dialogButton = dialog.itemtypeDone
        dialogButton.setOnClickListener {
            if (d.isChecked)
                Constants.Items_type = Constants.Items_type + "Documents/Books, "
            if (c.isChecked)
                Constants.Items_type = Constants.Items_type + "Cloths/Accessories, "
            if (e.isChecked)
                Constants.Items_type = Constants.Items_type + "Electronics/Mobiles, "
            if (f.isChecked)
                Constants.Items_type = Constants.Items_type + "Food/Flowers, "
            if (s.isChecked)
                Constants.Items_type = Constants.Items_type + "Sports/Equipments, "
            if (h.isChecked)
                Constants.Items_type = Constants.Items_type + "Household Items"
            Log.e("\nMessage2::", Constants.Items_type)
            if (Constants.Items_type.equals("")) {
                Toast.makeText(this, "Please Select Package Type", Toast.LENGTH_LONG).show()
                item_type_text.text = "Item type & Weight"
            } else{
                item_type_text.text = Constants.Items_type + " 10Kg"
                dialog.dismiss()
            }
        }

        dialog.show()

    }
    fun take_permissions(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //            Toasty.info(this, "Please Give Permissions", Toast.LENGTH_LONG, true).show();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.GET_ACCOUNTS
                ),
                100
            )

        }
    }
}
