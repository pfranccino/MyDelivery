package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pfranccino.mydelivery.Cart.CartSingleton
import com.example.pfranccino.mydelivery.Models.Cart
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.R
import android.R.attr.data





class FoodRecycler(private val context: Activity, var food: List<FoodDetails>) : RecyclerView.Adapter<FoodRecycler.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = context.layoutInflater
        return ViewHolder(inflater.inflate(R.layout.layout_list_food, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        food.let {

            holder.txtTitle?.text = food[position].title
            holder.txtShort?.text = food.get(position).short_description
            Glide.with(context).load(food.get(position).image_short).into(holder.imagefood)

           holder.buttonAdd.setOnClickListener {

                val variable = holder.adapterPosition

                val foodSelected = food[variable]

                val data= CartSingleton.instance!!.cart!!.categoriesList

                if (CartSingleton.instance != null) {


                    val result = data.add(foodSelected)

                    if (data != null) {

                        Log.d("lista", "existe")

                        Log.d("añadido", result.toString())

                        Log.d("total", data.size.toString())
                    }

                }

                for (i in 0..data.size - 1) {
                    Log.d("elemento", data.get(i).title)
                }


                //CartSingleton.instance!!.cart!!.categoriesList!!.add(foodSelected)

                //CartSingleton.instance!!.addItemToCart(foodSelected)

                //Log.d("elemento", CartSingleton.instance.cart.categoriesList)

            }


        }
    }

    override fun getItemCount(): Int {
        return food.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.textViewTitleFood)
        val txtShort = view.findViewById<TextView>(R.id.textViewShortDescription)
        val imagefood = view.findViewById<ImageView>(R.id.imageViewFood)
        val buttonAdd = view.findViewById<Button>(R.id.imageButtonAdd)
        val buttonDelete = view.findViewById<Button>(R.id.imageButtonDelete)
    }

}








