package com.example.jeevandaan.Retrofit

import android.util.Base64
import com.example.jeevandaan.Response.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val AUTH="Basic"+ Base64.encodeToString("sss".toByteArray(),Base64.NO_WRAP)
    private const val BASE_URL="https://www.wizzie.online/jeevandaan/"
    private val okHTTPS=okhttp3.OkHttpClient.Builder()
        .addInterceptor {chain->
            val original=chain.request()
val rebuild=original.newBuilder()
    .method(original.method(),original.body())
    .addHeader("Authorization",AUTH)
val request=rebuild.build()
            chain.proceed(request)
        }.build()

val instance:Api by lazy {
val retrofit=Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHTTPS)
    .build()
    retrofit.create(Api::class.java)
}
}