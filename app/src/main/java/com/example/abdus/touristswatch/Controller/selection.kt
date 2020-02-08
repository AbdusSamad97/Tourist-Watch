package com.example.abdus.touristswatch.Controller

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.abdus.touristswatch.R
import kotlinx.android.synthetic.main.activity_selection.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


class selection : AppCompatActivity() {
      var selected:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        setSupportActionBar(toolbar)
        relativeId.setOnClickListener(null)

        textlay.setCounterEnabled(true)
        textlay.counterMaxLength = 20
        textid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (textid.getText().toString() == "") {
                    textlay.setErrorEnabled(true)
                    textlay.setError("Please Enter the Loction")
                } else {
                    textlay.setErrorEnabled(false)
                }
            }

            override fun afterTextChanged(s: Editable?) {


            }

        })


        textid.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (textid.getText().toString() == "") {
                    textlay.setErrorEnabled(true)
                    textlay.setError("Please Enter the Loction")
                } else {
                    textlay.setErrorEnabled(false)
                }


            }
        })

        radio.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.GPS -> {
                        textlay.setVisibility(View.INVISIBLE)
                        selected = "gps"
                    }
                    R.id.locate -> {
                        selected = "locate"
                        textlay.setVisibility(View.VISIBLE)
                    }
                }
            }

        })
        proceedID.setOnClickListener {
            if (isConnectedToInternet()) {
                if (selected == "gps") {
                    var intent: Intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                } else if (selected == "locate") {
                    var location: String = textid.getText().toString()
                    if (location == null) {
                        Toast.makeText(this, "Please Enter the location name first", Toast.LENGTH_LONG).show()
                    } else {
                        var geocoder: Geocoder = Geocoder(this)
                        var address: List<Address>
                        address = geocoder.getFromLocationName(location, 1)
                        if (address.size > 0 && address.get(0) != null) {
                            var locationInfo: String = address.get(0).toString()
                            Log.i("Address", locationInfo)
                            var intent: Intent = Intent(getApplicationContext(), MapsActivity2::class.java)
                            intent.putExtra("Latitude", address.get(0).getLatitude().toString())
                            intent.putExtra("Longitude", address.get(0).getLongitude().toString())
                            intent.putExtra("Name", address.get(0).getLocality().toString())
                            startActivity(intent)
                        }
                    }

                }
            }
            else{
                Toast.makeText(applicationContext,"No Internet Connection!!",Toast.LENGTH_LONG).show()
            }


        }
    }

    fun isConnectedToInternet(): Boolean {
        val connectivity = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }

        }
        return false
    }
}
