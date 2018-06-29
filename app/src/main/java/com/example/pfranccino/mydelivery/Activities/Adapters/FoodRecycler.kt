package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.R



class FoodRecycler(private val context: Activity, internal var food: List<FoodDetails>) : RecyclerView.Adapter<FoodRecycler.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = context.layoutInflater
        return ViewHolder(inflater.inflate(R.layout.layout_list_food,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        food?.let{

        holder?.txtTitle?.text = food.get(position).title
        holder?.txtShort?.text = food.get(position).short_description
            Glide.with(context).load(food.get(position).image_short).into(holder.imagefood)

        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return food.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.textViewTitleFood)
        val txtShort = view.findViewById<TextView>(R.id.textViewShortDescription)
        val imagefood = view.findViewById<ImageView>(R.id.imageViewFood)
    }
}









