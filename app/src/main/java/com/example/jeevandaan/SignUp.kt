package com.example.jeevandaan

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TabHost
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.jeevandaan.Response.CommonResponse
import com.example.jeevandaan.Retrofit.Retrofit
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : Fragment() {
    lateinit var name:EditText
    lateinit var mail:EditText
    lateinit var mobile:EditText
    lateinit var password:EditText
    lateinit var btn:Button
    lateinit var adress:EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.activity_sign_up,container,false)
        name=view.findViewById(R.id.name)
        mail=view.findViewById(R.id.mailx)
        mobile=view.findViewById(R.id.mobile)
        password=view.findViewById(R.id.password)
        btn=view.findViewById(R.id.signup)
        adress=view.findViewById(R.id.adress)

        btn.setOnClickListener {
            val p=ProgressDialog(context).apply {
                setTitle("Creating \uD83D\uDE42..............")
                setCancelable(false)
            }

           val name1=name.text.toString()
            val mail1=mail.text.toString()
            val mobile1=mobile.text.toString()
            val password1=password.text.toString()
            val adress1=adress.text.toString()
        if(name1.isEmpty()||
            mail1.isEmpty()||
            mobile1.isEmpty()||adress1.isEmpty()||
            password1.isEmpty()){
            it.toast("Empty")
        }else{
            p.show()
CoroutineScope(IO).launch {
    Retrofit.instance.usercreate(name1,mail1,password1,mobile1,adress1,"user").enqueue(object :Callback<CommonResponse>{
        override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
            val choose=response.body()!!.message
            if(choose=="Thank you For Choosing this app"){
             btn.toast(choose)
                name.setText("")
                mail.setText("")
                mobile.setText("")
                password.setText("")
                adress.setText("")
    }else{
                btn.toast(choose)
            }
            p.dismiss()
        }

        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
logfail("${t.message}")
            p.dismiss()
            name.setText("")
            mail.setText("")
            mobile.setText("")
            password.setText("")
            adress.setText("")

        }
    })
}
        }

        }





        return view
    }
}