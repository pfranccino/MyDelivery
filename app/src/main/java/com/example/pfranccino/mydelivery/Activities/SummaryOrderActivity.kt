package com.example.pfranccino.mydelivery.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ListView
import com.example.pfranccino.mydelivery.Activities.Adapters.CategoryList
import com.example.pfranccino.mydelivery.Activities.Adapters.SummaryAdapter
import com.example.pfranccino.mydelivery.Cart.CartSingleton
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R

class SummaryOrderActivity : AppCompatActivity() {

    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView? = null
    private var listView: ListView? = null
    var summaryList: MutableList<List<FoodDetails>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_order)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        //Enable menu y toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        listView = findViewById(R.id.summaryList)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)




        // set Menu
        navigationView!!.setNavigationItemSelectedListener { item ->


            when(item.itemId){

                R.id.menu_perfil ->{

                }
                R.id.menu_compras ->{

                }
                R.id.menu_pagos->{

                }
                R.id.menu_promocion ->{

                }
                R.id.menu_factura ->{

                }
                R.id.menu_ayuda ->{

                }
                R.id.menu_contacto ->{

                }

            }

            true
        }

        summaryList = mutableListOf<List<FoodDetails>>()

        loadSummary()



    }

    fun loadSummary() {
        val data= CartSingleton.instance!!.cart!!.categoriesList


        val summary = data.groupBy { item ->
            item.uuid
        }


        summary.forEach { (key, value) -> summaryList!!.add(value); val adapter = SummaryAdapter(this@SummaryOrderActivity, summaryList!!); listView!!.adapter = adapter }



    }
}
