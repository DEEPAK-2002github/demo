package com.example.jeevandaan.User

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.example.jeevandaan.R
import com.example.jeevandaan.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class UserMainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        val near=findViewById<CardView>(R.id.nearme)
        val logout=findViewById<CardView>(R.id.logout)
        val uname=findViewById<TextView>(R.id.names)
        val welcome=findViewById<TextView>(R.id.welcome)
        getSharedPreferences("user", MODE_PRIVATE).apply {
            uname.setText(getString("name","!!!"))
            welcome.setText("Hi!! \uD83D\uDE42"+getString("name","!!!!"))
            }

        near.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.CALL_PHONE,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),100)
                it.toast("Please Give Permiisons")
            }else{
                startActivity(Intent(this,UserAmbulance::class.java))
            }
        }

        logout.setOnClickListener { getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply() }
    }

}