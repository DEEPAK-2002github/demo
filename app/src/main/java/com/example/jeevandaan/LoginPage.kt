package com.example.jeevandaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class LoginPage : AppCompatActivity() {
    lateinit var layout:TabLayout
lateinit var view:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        layout=findViewById(R.id.tablayout)

        view=findViewById(R.id.viewp)
        layout.addTab(layout.newTab().setText("Login").setIcon(R.drawable.enter))
        layout.addTab(layout.newTab().setText("SignUp").setIcon(R.drawable.contract))
getSharedPreferences("", MODE_PRIVATE)

 val adpter=AdapterTab(this,supportFragmentManager,layout.tabCount)
        view.adapter=adpter
view.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layout))
        layout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                view.currentItem=tab!!.position
            }

        })



    }

}