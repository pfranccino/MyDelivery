package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.R

class CategoryList(private val context: Activity, internal var artists: List<Category>) : ArrayAdapter<Category>(context, R.layout.layout_list_category, artists) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_category, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
        val textViewGenre = listViewItem.findViewById(R.id.textViewShort) as TextView

        val artist = artists[position]
        textViewName.text = artist.title
        textViewGenre.text = artist.short_description

        return listViewItem
    }
}