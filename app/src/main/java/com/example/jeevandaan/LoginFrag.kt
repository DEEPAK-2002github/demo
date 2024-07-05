package com.example.jeevandaan

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jeevandaan.Admin.AdminMainActivity
import com.example.jeevandaan.Response.LoginRespose
import com.example.jeevandaan.Retrofit.Retrofit
import com.example.jeevandaan.User.UserMainActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   LoginFrag : Fragment() {
    lateinit var email:TextInputEditText
    lateinit var password:TextInputEditText
    lateinit var btn:Button
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view=inflater.inflate(R.layout.activity_login_frag,container,false)

       email=view.findViewById(R.id.email)
       password=view.findViewById(R.id.passwo)
       btn=view.findViewById(R.id.next)
        val p=ProgressDialog(context).apply {
            setCancelable(false)
            setTitle("\uD83D\uDE0A")
            setMessage("Wait a Moment......................")
        }
        btn.setOnClickListener {

val mail=email.text.toString()
            val password=password.text.toString()
            if(mail.isEmpty()||password.isEmpty()){
                it.toast("something Went Wrong")
            }else if(mail=="admin"&&password=="admin"){
                val int=Intent(context,AdminMainActivity::class.java)
                int.putExtra("data","sd")
                context!!.getSharedPreferences("user", 0).edit().apply {
                    putString("type", "admin")
                    apply()
                }
                startActivity(int)
                p.dismiss()
            }else{
                p.show()
                CoroutineScope(IO).launch {
                    Retrofit.instance.loginpage("login",mail,password).enqueue(object :Callback<LoginRespose>{

                        override fun onResponse(call: Call<LoginRespose>, response: Response<LoginRespose>) {
                        val res=response.body()
                            it.toast(response.body()!!.message)
                        if(res!=null){
                            val k=res.data
                            if(k.type=="user") {
                                context!!.getSharedPreferences("user", 0).edit().apply {
                                    putString("id",k.id.toString())
                                    putString("name", k.name)
                                    putString("mail", k.mail)
                                    putString("password", k.password)
                                    putString("mobile", k.mobile)
                                    putString("type", k.type)
                                    putString("location", k.location)
                                    putString("location", k.hospital)
                                    apply()
                                }
                                startActivity(Intent(context, UserMainActivity::class.java))
                            }
                        }
                            p.dismiss()
                        }
                        override fun onFailure(call: Call<LoginRespose>, t: Throwable) {
                        logfail("${t.message}")
                            if("java.lang.NumberFormatException: For input string: "+"eroor"=="${t.message}"){
                                btn.toast("Incorrect password")
                            }

                            p.dismiss()
                        }
                    })
                }
            }
        }

        return view
    }
}