package com.example.jeevandaan

import android.util.Log
import android.view.View
import android.widget.Toast

fun View.toast(message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
fun logsuccess(success: String){
    Log.i("jevvandaan",success)
}
fun logfail(fail: String){
    Log.i("jevvandaan",fail)
}
