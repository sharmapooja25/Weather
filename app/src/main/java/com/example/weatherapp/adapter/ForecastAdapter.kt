package com.example.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.model.ListModel

class ForecastAdapter(var cartArray: List<ListModel>, val context: Context): RecyclerView.Adapter<ForecastAdapter.CartViewHolder>(){

    class CartViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txttime: TextView =view.findViewById(R.id.tv_time)
        val icon: ImageView =view.findViewById(R.id.tv_icon)
        val degree: TextView =view.findViewById(R.id.tv_deg)

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_forecast_layout,parent,false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartArray.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val weatherApp: WeatherApp? = null
        holder.txttime.text = cartArray.get(position).dtTxt.toString()
        holder.degree.text = cartArray.get(position).main.tempMax.toString()
        Glide.with(context)
            .load(weatherApp!!.getImageUrl() +  cartArray.get(position).weather.get(0).icon + "@2x.png")
            .into(holder.icon)

    }





}