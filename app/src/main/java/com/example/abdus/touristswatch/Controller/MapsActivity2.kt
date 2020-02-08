package com.example.abdus.touristswatch.Controller

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.abdus.touristswatch.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps2.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import android.content.Context.MODE_PRIVATE



class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

     var latitude:String?=null
    var longitude:String?=null
    var userLocation:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        var intent: Intent =getIntent()
//         latitude=intent.getStringExtra("Latitude")
//         longitude=intent.getStringExtra("Longitude")
//         userLocation=intent.getStringExtra("Name")


        selectedID2.setOnClickListener {
              var intent1:Intent=Intent(applicationContext,results::class.java)
               intent1.putExtra("Latitude",latitude)
                intent1.putExtra("Longitude",longitude)
               startActivity(intent1)


        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var intent: Intent =getIntent()
        latitude=intent.getStringExtra("Latitude")
        longitude=intent.getStringExtra("Longitude")
        userLocation=intent.getStringExtra("Name")

        // Add a marker in Sydney and move the camera
        val location = LatLng(latitude!!.toDouble(),longitude!!.toDouble())
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(location).title(userLocation))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10f))


    }
}
