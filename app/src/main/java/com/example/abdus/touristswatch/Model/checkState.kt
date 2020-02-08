package com.example.abdus.touristswatch.Model

import com.example.abdus.touristswatch.Controller.MainActivity
import com.example.abdus.touristswatch.Controller.preferences

class checkState {


    var mainobj= MainActivity()




    fun setState(bool:Boolean):Unit{
        preferences!!.edit().putBoolean("first",bool).apply()


    }

}