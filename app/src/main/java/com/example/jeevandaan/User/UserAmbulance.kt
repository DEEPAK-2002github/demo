package com.example.jeevandaan.User

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ZoomControls
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.jeevandaan.Model.User
import com.example.jeevandaan.R
import com.example.jeevandaan.Response.DriverResponse
import com.example.jeevandaan.Retrofit.Retrofit
import com.example.jeevandaan.databinding.ActivityUserAmbulanceBinding
import com.example.jeevandaan.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.internal.bind.MapTypeAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class UserAmbulance : AppCompatActivity(), OnMapReadyCallback {
private lateinit var fused:FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityUserAmbulanceBinding
    private lateinit var constraint:ConstraintLayout
    private lateinit var imageView:ImageView
    private lateinit var callnum:TextView
    lateinit var array:ArrayList<User>
    lateinit var pro:ProgressDialog
    lateinit var book:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserAmbulanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        array= ArrayList()
        constraint=findViewById(R.id.constraind)
        constraint.isVisible=false
        book=findViewById(R.id.myview)
        pro=ProgressDialog(this).apply {
            setTitle("\uD83D\uDCCD")
            setMessage("We are here to help you!!!")
            setCancelable(false)
            show()
        }
        imageView=findViewById(R.id.imageView)
        callnum=findViewById(R.id.callnum)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        constraint=findViewById(R.id.constraind)
        fused=LocationServices.getFusedLocationProviderClient(this)
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var num=""

        fused.lastLocation.addOnSuccessListener {
            val you = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(you).title("Your Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(you,16.0f))
            val geo=Geocoder(this).getFromLocation(it.latitude,it.longitude,1).get(0)
            val location=geo.subLocality+","+geo.locality
            CoroutineScope(IO).launch {
                Retrofit.instance.getdreiverarea(location.toLowerCase(Locale.ROOT)).enqueue(object :Callback<DriverResponse>{
                    override fun onResponse(call: Call<DriverResponse>, response: Response<DriverResponse>) {
                        val respo=response.body()!!.message
                        constraint.isVisible=true

                        pro.dismiss()
                         if(respo=="Success"){
                             val k= response.body()!!.data
                            array=k
                             k.forEach {
                       if(it.location.isNotEmpty()){
                           val split=it.latlon.split(",")
                          val lat=LatLng(split[0].toDouble(),split[1].toDouble())
                           mMap.addMarker(MarkerOptions().position(lat).title(it.name).icon(mybitmap(R.drawable.loc)))
                       }

                   }
             }
                    }

                    override fun onFailure(call: Call<DriverResponse>, t: Throwable) {
                        pro.dismiss()
                        Toast.makeText(this@UserAmbulance, "${t.message}", Toast.LENGTH_SHORT).show()

                    }
                })
            }
            val k=ArrayList<String>()
        mMap.setOnMarkerClickListener(object :GoogleMap.OnMarkerClickListener{
            @SuppressLint("SetTextI18n")
            override fun onMarkerClick(p0: Marker): Boolean {

                array.forEach {
                 if(p0.title==it.name) {
                k.add(it.id.toString())
                k.add(it.name)
         callnum.setText(it.name)
                     num=it.mobile
                 }
                }
                if(p0.title=="Your Location"){
                num=""
                callnum.setText("Call : 108")
                }

                 return false
            }
        })
        }


        imageView.setOnClickListener {
            if(num==""){
                val int=Intent(Intent.ACTION_CALL, Uri.parse("tel:108"))
                startActivity(int)
            }else{
                val int=Intent(Intent.ACTION_CALL, Uri.parse("tel:$num"))
                startActivity(int)
            }
        }


        fused.lastLocation.addOnFailureListener {
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }


    }
    fun mybitmap(id:Int):BitmapDescriptor {
        val drwable = ContextCompat.getDrawable(this@UserAmbulance, id)
        drwable!!.setBounds(0, 0, drwable.intrinsicWidth, drwable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            drwable.intrinsicWidth,
            drwable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drwable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}