package com.example.abdus.touristswatch.Controller

import android.content.Context
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.abdus.touristswatch.Model.RestaurantModel
import com.example.abdus.touristswatch.R
import com.example.abdus.touristswatch.R.id.*
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class RestaurantAdapter():RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    private var arraylist: ArrayList<RestaurantModel>?=null
    private var context: Context?=null
    constructor(model:ArrayList<RestaurantModel>,context:Context):this(){
        this.arraylist=model
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v:View=LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_fragment,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  arraylist!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          var model:RestaurantModel=arraylist!!.get(position)

        holder.name.setText(model.getName())
        holder.vicinity.setText("Address : "+model.getVicinity())
        holder.available.setText("Availability : "+model.getAvailabilty())
        Picasso.with(context)
                .load(model.getImage()).into(holder.image)
        holder.rating.setText(model.getRatings())

    }


    class ViewHolder: RecyclerView.ViewHolder {
        constructor(itemView: View?) : super(itemView)
        var name: TextView =itemView. findViewById(R.id.nameid) as TextView
        var vicinity: TextView =itemView.findViewById(R.id.vicinityid )as TextView
        var available: TextView =itemView.findViewById(R.id.availableid) as TextView
        var image: CircleImageView = itemView.findViewById(R.id.image) as CircleImageView
        var rating:TextView=itemView.findViewById(R.id.ratingid) as TextView



    }
}