package com.example.jeevandaan.Admin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jeevandaan.R
import com.example.jeevandaan.Response.CommonResponse
import com.example.jeevandaan.Retrofit.Retrofit
import com.example.jeevandaan.toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAmbulance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ambulance)


        val dname=findViewById<TextInputEditText>(R.id.dname)
        val hname=findViewById<TextInputEditText>(R.id.hname)

        val daddress=findViewById<TextInputEditText>(R.id.daddress)
        val haddress=findViewById<TextInputEditText>(R.id.haddress)
        val licennum=findViewById<TextInputEditText>(R.id.licennum)
        val age=findViewById<TextInputEditText>(R.id.age)
        val addbtn=findViewById<Button>(R.id.addbtn)
        val mobiles=findViewById<TextInputEditText>(R.id.mobiles)
        val pass=findViewById<TextInputEditText>(R.id.pass)
        val emails=findViewById<TextInputEditText>(R.id.emails)

        addbtn.setOnClickListener {
            val p=ProgressDialog(this).apply {
                setTitle("Adding Driver ⏳..................")
                setCancelable(false)
            }
            val dname1=dname.text.toString()
            val hname1=hname.text.toString()
            val daddress1=daddress.text.toString()
            val haddress1=haddress.text.toString()
            val licennum1=licennum.text.toString()
            val age1=age.text.toString()
            val mobiles1=mobiles.text.toString()
            val pass1=pass.text.toString()
            val emails1=emails.text.toString()
            if( dname1.isEmpty()||
                hname1.isEmpty()||
                daddress1.isEmpty()||
                haddress1.isEmpty()||
                licennum1.isEmpty()||
                age1.isEmpty()||
                mobiles1.isEmpty()||
                pass1.isEmpty()||
                emails1.isEmpty()){it.toast("Empty")}else {
                p.show()
                CoroutineScope(IO).launch {
                    Retrofit.instance.addinghospital(dname1,emails1, pass1, mobiles1, "driver", daddress1,hname1 + "/" + haddress1 + "/" + licennum1 + "/" + age1).enqueue(object : Callback<CommonResponse> {
                        override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>){
                            it.toast(response.body()!!.message)
                    p.dismiss()
                        finish()
                    }

                        override fun onFailure(call: Call<CommonResponse>, t: Throwable){
                            it.toast("${t.message}")

                        p.dismiss()
                        }
                    })
                }


            }
        }
    }
}