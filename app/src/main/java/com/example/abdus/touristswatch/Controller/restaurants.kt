package com.example.abdus.touristswatch.Controller

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.abdus.touristswatch.Model.RestaurantModel
import com.example.abdus.touristswatch.R
import kotlinx.android.synthetic.main.activity_restaurants.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Array

class restaurants : AppCompatActivity() {
    var modellist=ArrayList<RestaurantModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        loadRecyclerView()

    }
    fun loadRecyclerView(){
        var p:ProgressDialog= ProgressDialog(this)
        p.setMessage("Loading Data...")
        p.show()
        var intent: Intent=getIntent()
        var latitude:String=intent.getStringExtra("Latitude")
        var longitude:String=intent.getStringExtra("Longitude")
        val url:String="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=3000&type=restaurant&key=AIzaSyDnQeraxterOrf3KEvranbeKak9FXwrlGs"
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET,url,
                com.android.volley.Response.Listener<String>{response->
                    p.dismiss()
                    Log.i("Response",response)


                   var rootobject:JSONObject= JSONObject(response)
                    var objectarray:JSONArray=rootobject.getJSONArray("results")
                    for(i in 0..objectarray.length()-1){
                        var part:JSONObject=objectarray.getJSONObject(i)

//                        var timepart:JSONObject=part.getJSONObject("opening_hours")
                        var model:RestaurantModel= RestaurantModel(part.getString("name"),part.getString("vicinity"),true,part.getString("icon"),part.getString("place_id"))
                        try{
                         modellist!!.add(model)}
                        catch(exc:Exception){
                            exc.printStackTrace()
                        }
                    }
                    var adapter:RestaurantAdapter= RestaurantAdapter(modellist!!,applicationContext)
                    recycler.setAdapter(adapter)
                    recycler.setHasFixedSize(true)
                    recycler.setLayoutManager(LinearLayoutManager(this))



                },com.android.volley.Response.ErrorListener {
            Log.i("error","Error")
        } )

        queue.add(stringRequest)
    }
}
