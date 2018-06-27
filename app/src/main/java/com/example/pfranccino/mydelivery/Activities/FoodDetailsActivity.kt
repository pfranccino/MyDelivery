package com.example.pfranccino.mydelivery.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.CategoryJsonParser
import com.example.pfranccino.mydelivery.API.Users.FoodDetailsJsonParser
import com.example.pfranccino.mydelivery.Activities.Adapters.CategoryList
import com.example.pfranccino.mydelivery.Activities.Adapters.FoodList
import com.example.pfranccino.mydelivery.Models.Category

import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import org.json.JSONArray
import org.json.JSONException


class FoodDetailsActivity : AppCompatActivity() {

    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView? = null
    var foodList: MutableList<FoodDetails>? = null
    private var listView: ListView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)





        //Enable menu y toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        listView = findViewById(R.id.foodList) as ListView

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        // Load user data
        val user = intent.getSerializableExtra("user") as User
        //loadDataUser(user)

        navigationView!!.setNavigationItemSelectedListener { item ->


            when (item.itemId) {

                R.id.menu_perfil -> {

                }
                R.id.menu_compras -> {

                }
                R.id.menu_pagos -> {

                }
                R.id.menu_promocion -> {

                }
                R.id.menu_factura -> {
                    startActivity(Intent(this, MainActivity::class.java).putExtra("user", user))
                }
                R.id.menu_ayuda -> {

                }
                R.id.menu_contacto -> {

                }

            }

            true


        }


        foodList = mutableListOf<FoodDetails>()

        loadFood()

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


    private fun loadFood() {
        val stringRequest = StringRequest(Request.Method.GET,
                "http://13.68.139.247/api/foods?category_id=481e92f3-c144-44f0-bc07-c3a4fd1fe8cb",
                Response.Listener<String> { s ->
                    try {
                        val obj = JSONArray(s)

                        val par = FoodDetailsJsonParser()


                        for (i in 0..(obj.length() - 1)) {

                            foodList!!.add(par.getFood(obj.getJSONObject(i)))

                            val adapter = FoodList(this@FoodDetailsActivity, foodList!!)

                            listView!!.adapter = adapter



                            Log.d("iteracion", i.toString())
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }




       /* private fun loadDataUser(user:User) {

            // this method extracts data from the mainActivity

            val navigationView = findViewById<NavigationView>(R.id.navigation_view)

            val headerView = navigationView.getHeaderView(0)

            headerView.userName.text = "${ user.first_name } ${ user.last_name }"

        }
        */

}
