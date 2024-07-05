package com.example.jeevandaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.jeevandaan.Admin.AdminMainActivity
import com.example.jeevandaan.Driver.DriverActivity
import com.example.jeevandaan.User.UserMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loti=findViewById<LottieAnimationView>(R.id.lotifie)
        loti.playAnimation()
        loti.alpha=0f
        var type=""
        getSharedPreferences("user", 0).apply {
            type= getString("type","")!!
        }

        loti.animate().setDuration(1000).alpha(1f).withStartAction {
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        }.withEndAction {

            finish()
            if(type=="admin") {startActivity(Intent(this,AdminMainActivity::class.java))}
            else if(type=="user"){
                startActivity(Intent(this,UserMainActivity::class.java))
            }else if(type=="driver"){
                startActivity(Intent(this,DriverActivity::class.java))

            }
            else {
                startActivity(Intent(this, LoginPage::class.java))
            }



            loti.pauseAnimation()
        }

    }
}