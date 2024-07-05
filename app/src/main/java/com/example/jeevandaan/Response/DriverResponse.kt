package com.example.jeevandaan.Response

import com.example.jeevandaan.Model.User
import java.lang.Error

data class DriverResponse (val error: Boolean,var message:String,var data:ArrayList<User>)