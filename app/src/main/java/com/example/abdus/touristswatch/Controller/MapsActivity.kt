package com.example.abdus.touristswatch.Controller

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.abdus.touristswatch.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_selection.*
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
     var locationManager:LocationManager?=null
    var locationListener:LocationListener?=null
    var latitude:String?=null
    var longitude:String?=null
    var name:String?=null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        selectedKey.setOnClickListener{


         try {
             var intent: Intent =Intent(applicationContext,results::class.java)
             intent.putExtra("Latitude",latitude)
             intent.putExtra("Longitude",longitude)
             startActivity(intent)


//             task.exec("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=1500&type=restaurant&key=AIzaSyDnQeraxterOrf3KEvranbeKak9FXwrlGs")
         }
         catch(exc:Exception){
             exc.printStackTrace()
         }

        }




    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                var location:Location=locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                updateMap(location)

               }


        }


    }

    fun updateMap(location:Location) {
        latitude=location.getLatitude().toString()
        longitude=location.getLongitude().toString()

        val userLocation = LatLng(location.getLatitude(), location.getLongitude())
        mMap.clear()



       var geocoder: Geocoder = Geocoder(this)
        var address: List<Address>
        address = geocoder.getFromLocation(
                location.getLatitude(),
                location.getLongitude(),
                1
        )
      if (address.size > 0 && address.get(0) != null) {
          var locationInfo: String = address.get(0).toString()
          Log.i("Address", locationInfo)
           var title: String = address.get(0).getLocality().toString()

          name=title
            mMap.addMarker(MarkerOptions().position(userLocation).title(title))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 20f))
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
         locationManager=getApplicationContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
         locationListener=(object:LocationListener{
             override fun onLocationChanged(location: Location?) {
                 updateMap(location!!)
             }

             override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

             }

             override fun onProviderEnabled(provider: String?) {

             }

             override fun onProviderDisabled(provider: String?) {

             }

         })
          if(Build.VERSION.SDK_INT<23){
              if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
              locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)}
          }
          else{
              if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                 var permissionlist=arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION)
                  ActivityCompat.requestPermissions(this,permissionlist,1)
              }
              else{
                  locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                  try{
                  var location:Location=locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                      updateMap(location)}
                  catch(exc:Exception){
                      Log.i("error","mother fuck")
                      exc.printStackTrace()
                  }


              }
          }

        // Add a marker in Sydney and move the camera

    }

}
