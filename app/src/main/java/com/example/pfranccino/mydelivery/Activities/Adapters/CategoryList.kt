package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.R
import com.bumptech.glide.Glide


class CategoryList(private val context: Activity, internal var categories: List<Category>) : ArrayAdapter<Category>(context, R.layout.layout_list_category, categories) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_category, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
        val imageViewCategory = listViewItem.findViewById(R.id.imageViewCategory)  as ImageView



        val category = categories[position]
        textViewName.text = category.title
        Glide.with(context).load(category.image_large).into(imageViewCategory)
        return listViewItem
    }


    override fun getItem(position: Int): Category {
        return getItem(position)
    }


}