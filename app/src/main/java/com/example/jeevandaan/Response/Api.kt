package com.example.jeevandaan.Response

import android.util.Log
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("signup.php")
    fun usercreate(
        @Field("name")name:String,
        @Field("mail")mail:String,
        @Field("password")password:String,
        @Field("mobile")mobile:String,
        @Field("location")location:String,
        @Field("type")type:String
    ):Call<CommonResponse>
    @FormUrlEncoded
    @POST("signup.php")
    fun loginpage(
        @Field("condition")condition:String,
        @Field("email")email:String,
        @Field("password")password:String
    ):Call<LoginRespose>
    @FormUrlEncoded
    @POST("signup.php")
    fun addinghospital(
        @Field("name")name:String,
        @Field("mail")mail:String,
        @Field("password")password:String,
        @Field("mobile")mobile:String,
        @Field("type")type:String,
        @Field("location")location:String,
        @Field("hospital")hospital:String
    ):Call<CommonResponse>

    @FormUrlEncoded
    @POST("driverloc.php")
    fun getdreiverarea(
        @Field("address")address:String,
        ):Call<DriverResponse>


}