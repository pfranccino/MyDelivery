package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pfranccino.mydelivery.Cart.CartSingleton
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.R
import android.content.Intent
import android.widget.*
import com.example.pfranccino.mydelivery.Activities.SummaryOrderActivity


class FoodRecycler(private val context: Activity, var food: List<FoodDetails>) : RecyclerView.Adapter<FoodRecycler.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = context.layoutInflater
        return ViewHolder(inflater.inflate(R.layout.layout_list_food, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        food.let {

            holder.txtTitle?.text = food[position].title
            holder.txtShort?.text = food[position].short_description
            Glide.with(context).load(food[position].image_large).into(holder.imagefood)


            Log.d("calling holder", "hold")

            val adapterPos = holder.adapterPosition

            if (food.get(adapterPos) != null) {
                val foodFocus = food[adapterPos]

                if (CartSingleton.instance != null) {
                    if (CartSingleton.instance!!.cart!!.categoriesList != null) {
                        if (foodFocus != null) {
                            if (CartSingleton.instance!!.hasProductInCart(foodFocus)) {
                                holder.buttonDelete.visibility = View.VISIBLE
                            } else {
                                holder.buttonDelete.visibility = View.INVISIBLE
                            }
                        }
                    }

                }

            }

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

                    if (CartSingleton.instance!!.hasProductInCart(foodSelected)) {
                        holder.buttonDelete.visibility = View.VISIBLE

                        Toast.makeText(context, foodSelected.title + " agregado al carrito de compras", Toast.LENGTH_SHORT).show()
                    }
                }


                for (i in 0..data.size - 1) {
                    Log.d("elemento", data.get(i).title)


                    var item =  data.get(i)

                    Log.d("elemento existe", CartSingleton.instance!!.hasProductInCart(item).toString())
                }





            }

            holder.buttonDelete.setOnClickListener {

                val variable = holder.adapterPosition

                val foodSelected = food[variable]


                if (CartSingleton.instance != null) {
                    if (CartSingleton.instance!!.hasProductInCart(foodSelected)) {

                        CartSingleton.instance!!.removeItem(foodSelected)

                        Log.d("eliminando prod", "delete")

                        Toast.makeText(context, foodSelected.title + " quitado correctamente del carrito de compras", Toast.LENGTH_SHORT).show()

                        if (!CartSingleton.instance!!.hasProductInCart(foodSelected)) {

                            holder.buttonDelete.visibility = View.INVISIBLE

                            Log.d("set visib", "zero")

                        }
                    }
                }
            }


            holder.buttonOrder.setOnClickListener{

               val  intent = Intent(context, SummaryOrderActivity::class.java)

                context.startActivity(intent)


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
        val buttonAdd = view.findViewById<ImageButton>(R.id.imageButtonAdd)
        val buttonDelete = view.findViewById<ImageButton>(R.id.imageButtonDelete)
        val buttonOrder = view.findViewById<Button>(R.id.buttonOrden)
    }

}








