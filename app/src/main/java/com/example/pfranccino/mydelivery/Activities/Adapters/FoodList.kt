package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.R

class FoodList(private val context: Activity, internal var food: List<FoodDetails>) : ArrayAdapter<FoodDetails>(context, R.layout.layout_list_food, food) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_food, null, true)

        val textViewTitle = listViewItem.findViewById(R.id.textVieTitleFood) as TextView
        val textViewShort = listViewItem.findViewById(R.id.textViewShortFood) as TextView

        val food = food[position]
        textViewTitle.text = food.title
        textViewShort.text = food.short_description

        return listViewItem
    }
}