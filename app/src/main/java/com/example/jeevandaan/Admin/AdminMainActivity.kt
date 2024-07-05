package com.example.jeevandaan.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.jeevandaan.LoginPage
import com.example.jeevandaan.MainActivity
import com.example.jeevandaan.R

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        if(intent.getStringExtra("data")!=null) {
            finishAffinity()
            startActivity(Intent(this, AdminMainActivity::class.java))
        }
        findViewById<CardView>(R.id.addambulance).setOnClickListener {
startActivity(Intent(this,AddAmbulance::class.java))
        }
        findViewById<CardView>(R.id.adminlog).setOnClickListener {
            startActivity(Intent(this,LoginPage::class.java))
            finish()
            getSharedPreferences("user",0).edit().clear().apply()
        }

}
}