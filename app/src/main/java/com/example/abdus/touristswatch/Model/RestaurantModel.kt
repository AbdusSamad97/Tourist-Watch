package com.example.abdus.touristswatch.Model

class RestaurantModel {
   private var restaurantname:String?=null
    private var vicinity:String?=null
    private var openhours:String?=null
    private var image:String?=null
    private var ratings:String?=null

    constructor(restaurantname: String, vicinity: String, openhours: Boolean, image: String,rating:String) {
        this.restaurantname = restaurantname
        this.vicinity = vicinity

        if(openhours!!){
            this.openhours="open"
        }
        else{
            this.openhours="false"
        }
        this.image = image

        this.ratings=rating
    }

    fun setName(name:String){
        this.restaurantname=name
    }
    fun setVicinity(vicin:String){
        this.vicinity=vicin
    }
    fun setAvailable(available:String){
        this.openhours=vicinity
    }
    fun setImage(img:String){
        this.image=img
    }
    fun setRatings(rating:String){
        this.ratings=rating
    }
    fun getName():String{
        return restaurantname!!
    }
    fun getVicinity():String{
        return vicinity!!
    }
    fun getAvailabilty():String{
        return openhours!!
    }
    fun getImage():String{
        return image!!
    }
    fun getRatings():String{
        return ratings!!
    }
}