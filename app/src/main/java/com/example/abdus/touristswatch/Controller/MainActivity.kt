package com.example.abdus.touristswatch.Controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.abdus.touristswatch.Model.checkState
import com.example.abdus.touristswatch.R
import kotlinx.android.synthetic.main.activity_main.*

var preferences: SharedPreferences?=null
class MainActivity : AppCompatActivity() {
    var firstState:Boolean?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        consultState()

        startedid.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var intent: Intent =Intent(getApplicationContext(), selection::class.java)
                startActivity(intent)
                finish()
            }

        })

    }
    fun consultState(){
        var c= checkState()
        if(firstState==null){
            preferences =this.getSharedPreferences(" com.example.abdus.touristwatch.Controller", Context.MODE_PRIVATE)

            firstState= preferences!!.getBoolean("first",true)

            if(firstState==true) {
                c.setState(true)
            }

            else{
                var intent: Intent =Intent(this, selection::class.java)
                startActivity(intent)
                finish()
            }

        }

        c.setState(false)



    }
}
