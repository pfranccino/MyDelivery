package com.example.pfranccino.mydelivery.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem

import android.view.View
import android.widget.ListView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.Activities.Adapters.CategoryList
import com.example.pfranccino.mydelivery.Activities.Adapters.SummaryAdapter
import com.example.pfranccino.mydelivery.Cart.CartSingleton
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.FoodCart
import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import kotlinx.android.synthetic.main.activity_summary_order.*
import org.json.JSONArray

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
        listView = this.findViewById(R.id.summaryList)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = " Detalles "





        buttonPagar.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val url = "http://13.68.139.247/api/auth/login"
            val data= CartSingleton.instance!!.cart!!.categoriesList
            val jsonArray = JSONArray()

            jsonArray.put(data)


            val request = object: JsonArrayRequest(Request.Method.POST, url,jsonArray,
                    Response.Listener { response ->

                    },
                    Response.ErrorListener {



                    }) {


                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()

                    headers.put("Accept", "application/json")

                    return headers
                }
            }

            queue.add(request)


        }






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

        val total = data.sumBy { item-> item.price }

        txtTotal1.text = " $ ${total.toString()}"


        summary.forEach { (key, value) -> summaryList!!.add(value); val adapter = SummaryAdapter(this@SummaryOrderActivity, summaryList!!); listView!!.adapter = adapter }





    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){

            android.R.id.home ->{
                drawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
