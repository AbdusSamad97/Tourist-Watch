package com.example.abdus.touristswatch.Controller

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.abdus.touristswatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_weather.*
import org.json.JSONArray
import org.json.JSONObject

class weather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        loadWeather()

    }


    fun loadWeather(){
        var p: ProgressDialog = ProgressDialog(this)
        p.setMessage("Loading Data...")
        p.show()
        var intent: Intent =getIntent()
        var latitude:String=intent.getStringExtra("Latitude")
        var longitude:String=intent.getStringExtra("Longitude")
        val url:String="http://samples.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=1b5012cc60b2af01167e1b145190e861"
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET,url,
                Response.Listener<String>{ response->
                    p.dismiss()
                    Log.i("Response",response)
                     var jsonobject:JSONObject= JSONObject(response)
                    var array:JSONArray= jsonobject.getJSONArray("weather")

                        var arrayobj:JSONObject=array.getJSONObject(0)
                    var main:String=arrayobj.getString("description")
                    var icon:String=arrayobj.getString("icon")
                    var jsonpart:JSONObject=jsonobject.getJSONObject("main")
                    var temperature:String=jsonpart.getDouble("temp").toString()
                    var tempmax:String=jsonpart.getDouble("temp_max").toString()
                    var tempmin:String=jsonpart.getDouble("temp_min").toString()
                    var pressure:String=jsonpart.getDouble("pressure").toString()
                    var humidity:String=jsonpart.getDouble("humidity").toString()
                    mainid.setText(main)
                     tempvalueid.setText(temperature)
                    pressureid.setText(pressure)
                    Humidityid.setText(humidity)
                    multivalueid.setText("Temperature Range\nTempertaure_Max : "+tempmax+"\nTemperature_Min : "+tempmin)
                   Picasso.with(this).load(icon).into(iconid)


                }, Response.ErrorListener {
            Log.i("error","Error")
        } )

        queue.add(stringRequest)
    }

}
