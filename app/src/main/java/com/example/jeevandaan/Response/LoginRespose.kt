package com.example.jeevandaan.Response

import com.example.jeevandaan.Model.User

data class LoginRespose (val error:Boolean,val message:String,val data: User)