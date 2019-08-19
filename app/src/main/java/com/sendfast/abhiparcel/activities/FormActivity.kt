package com.sendfast.abhiparcel.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sendfast.abhiparcel.R
import kotlinx.android.synthetic.main.activity_form.*
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.util.*
import java.text.SimpleDateFormat
import android.widget.TimePicker
import android.app.TimePickerDialog

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.title = "Order Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        var insideCity: Boolean = intent.getBooleanExtra("insideCity", false)
        var outsideCity: Boolean = intent.getBooleanExtra("outsideCity", false)

        requestDeliveryBoyButtton.setOnClickListener {
            val intent = Intent(this, TrackingActivity::class.java)
            startActivity(intent)
            this.finish()
        }


        var myCalendar: Calendar = Calendar.getInstance();
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "MMMM - yy" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            text_month_year.setText(sdf.format(myCalendar.time))

            text_day.text = dayOfMonth.toString()

            val c = Calendar.getInstance()
            var mHour = c.get(Calendar.HOUR_OF_DAY)
            var mMinute = c.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    if(hourOfDay < 12)
                        text_time.text = ", $hourOfDay:$minute AM"
                    else
                        text_time.text = ", $hourOfDay:$minute PM"
                },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()

        }

        layout_date_picker.setOnClickListener {

            DatePickerDialog(
                this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }



        if (insideCity) {
            layout_delivery_speed.visibility = View.GONE
            layout_line_delivery_speed.visibility = View.GONE
        }

        layout_regular_delivery.setOnClickListener {
            layout_regular_delivery.setBackgroundColor(parseColor("#D51A1A"))
            image_regular_delivery.setImageResource(R.drawable.regular_delivery_icon_white)
            text_regular_delivery.setTextColor(parseColor("#FFFFFF"))
            price_regular_delivery.setTextColor(parseColor("#FFFFFF"))

            layout_express_delivery.setBackgroundColor(parseColor("#FFFFFF"))
            image_express_delivery.setImageResource(R.drawable.express_delivery_icon)
            text_express_delivery.setTextColor(parseColor("#9C9C9C"))
            price_express_delivery.setTextColor(parseColor("#9C9C9C"))

            text_delivery_charge.text = " ₹100"
            text_grand_total.text = " ₹60"
        }
        layout_express_delivery.setOnClickListener {
            layout_express_delivery.setBackgroundColor(parseColor("#D51A1A"))
            image_express_delivery.setImageResource(R.drawable.express_delivery_icon_white)
            text_express_delivery.setTextColor(parseColor("#FFFFFF"))
            price_express_delivery.setTextColor(parseColor("#FFFFFF"))

            layout_regular_delivery.setBackgroundColor(parseColor("#FFFFFF"))
            image_regular_delivery.setImageResource(R.drawable.regular_delivery_icon)
            text_regular_delivery.setTextColor(parseColor("#9C9C9C"))
            price_regular_delivery.setTextColor(parseColor("#9C9C9C"))

            text_delivery_charge.text = " ₹150"
            text_grand_total.text = " ₹110"
        }
    }
}
