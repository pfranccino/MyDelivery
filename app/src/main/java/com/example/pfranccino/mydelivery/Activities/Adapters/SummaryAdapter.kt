package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.R


class SummaryAdapter(private val context: Activity, internal var food : MutableList<List<FoodDetails>>) : ArrayAdapter<List<FoodDetails>>(context, R.layout.layout_list_summary, food) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_summary, null, true)

        val textViewTitle = listViewItem.findViewById<TextView>(R.id.textTitle)
        val textViewCount  = listViewItem.findViewById<TextView>(R.id.textCount)
        val textViewTotal = listViewItem.findViewById<TextView>(R.id.textTotal)


        val food = food[position]

        val item = food.first()

        val totalPriceInList: Int = food.map { item.price }.sum()

        textViewTitle.text = item.title

        textViewCount.text = food.size.toString()

        textViewTotal.text = "$ ${totalPriceInList.toString()}"

        //textViewTitle.text = food


        return listViewItem
    }


    override fun getItem(position: Int): List<FoodDetails> {
        return getItem(position)
    }


}