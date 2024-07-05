package com.example.jeevandaan

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlin.math.log

class AdapterTab(val context: Context,val supoet:FragmentManager,var tabcount:Int):FragmentPagerAdapter(supoet) {
    override fun getCount(): Int {
return tabcount
    }

    override fun getItem(position: Int): Fragment {
when(position){
    0-> {
        val login = LoginFrag()
return login
    }
1->{
    val signup=SignUp()
    return signup
}else->return LoginFrag()
}
   }

}
