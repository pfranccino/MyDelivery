package com.example.pfranccino.mydelivery.Activities.Adapters

import android.app.Activity
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.R
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL


class CategoryList(private val context: Activity, internal var artists: List<Category>) : ArrayAdapter<Category>(context, R.layout.layout_list_category, artists) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_category, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
     val ImageViewCategory = listViewItem.findViewById(R.id.imageViewCategory)  as ImageView



        val artist = artists[position]
        textViewName.text = artist.title
        Glide.with(context).load(artist.images_large).into(ImageViewCategory)
        return listViewItem
    }


    override fun getItem(position: Int): Category {
        return getItem(position)
    }


}