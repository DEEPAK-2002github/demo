package com.example.jeevandaan.User

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.jeevandaan.R
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class AmbulanceBooking : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_ambulance_booking)
        val data=intent.getStringArrayListExtra("data")
        val id=findViewById<TextView>(R.id.thetext)
        val estimatedho=findViewById<TextInputEditText>(R.id.estimatedho)
        val from=findViewById<TextInputEditText>(R.id.froms)
        val to=findViewById<TextInputEditText>(R.id.to)
val time1=findViewById<ImageView>(R.id.time1)
val timeselect=findViewById<TextView>(R.id.timeselect)
       val k=Calendar.getInstance()
        val o=TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            timeselect.setText("$hourOfDay : $minute")
        },k.get(Calendar.HOUR),k.get(Calendar.MINUTE),true)
        o.setCancelable(false)
o.window!!.setTitleColor(Color.BLACK)
        time1.setOnClickListener{
            o.show()
        }
        timeselect.setOnClickListener {
            o.show()
        }




    }
}