package com.example.abdus.touristswatch.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.abdus.touristswatch.R
import kotlinx.android.synthetic.main.activity_results.*
import android.view.View


class results : AppCompatActivity() {
     var latitude:String?=null
    var longitude:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        var intent:Intent=getIntent()
        latitude=intent.getStringExtra("Latitude")
        longitude=intent.getStringExtra("Longitude")


        



        weatherid.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                var intent1:Intent=Intent(applicationContext, weather::class.java)
                intent1.putExtra("Latitude",latitude)
                intent1.putExtra("Longitude",longitude)
                startActivity(intent1)

            }

        })

        Restaurantid.setOnClickListener{
            var intent2:Intent=Intent(applicationContext,restaurants::class.java)
            intent2.putExtra("Latitude",latitude)
            intent2.putExtra("Longitude",longitude)
            startActivity(intent2)
        }


    }
}
