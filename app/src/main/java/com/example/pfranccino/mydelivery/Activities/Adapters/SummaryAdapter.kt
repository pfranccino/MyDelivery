package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.R


class SummaryAdapter(private val context: Activity, internal var food : List<Category>) : ArrayAdapter<Category>(context, R.layout.layout_list_category, food) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_category, null, true)

        val textViewTitle = listViewItem.findViewById<TextView>(R.id.textTitle)
        val textViewCount  = listViewItem.findViewById<TextView>(R.id.textCount)
        val textViewTotal = listViewItem.findViewById<TextView>(R.id.textTotal)


        return listViewItem
    }


    override fun getItem(position: Int): Category {
        return getItem(position)
    }


}