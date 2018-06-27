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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.CategoryJsonParser
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.header.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class newActivity : AppCompatActivity() {

    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView? = null
    var cate: ArrayList<Category> =  ArrayList()

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        //Prueba




        //Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        //Activar menus y toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        //Request
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url = "http://13.68.139.247/api/food_categories?business_id=eaa75bf2-250b-4856-97b8-3b5f65809e7a"
        val arrayRequest = jsonArrayRequest(url)
        queue.add(arrayRequest)






        //Data usuario
        val user = intent.getSerializableExtra("objeto") as User
        loadDataUser(user)


        getCategories(object: VolleyCallback {
            override fun onSuccess(result: JSONArray) {
                Log.d("calling onSuccess", "1");
                val par = CategoryJsonParser()

                for (i in 0..(result.length() - 1)) {
                    cate.add(par.getCategory(result.getJSONObject(i)))

                    Log.d("iteracion", i.toString())
                }
            }
        })




        Log.d("tamaño", cate.size.toString())



        //Menu
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
                    startActivity(Intent(this,MainActivity::class.java).putExtra("user",user))
                }
                R.id.menu_ayuda ->{

                }
                R.id.menu_contacto ->{

                }

            }

            true
        }

    }

    private fun getCategories(callback: VolleyCallback) {
        val request = JsonArrayRequest(Request.Method.GET, "http://13.68.139.247/api/food_categories?business_id=eaa75bf2-250b-4856-97b8-3b5f65809e7a", null,


        Response.Listener { response ->
            Log.d("llamando getcategories", "asd")
            callback.onSuccess(response)

        },
        Response.ErrorListener {


        })
    }

    private fun jsonArrayRequest(url: String): JsonArrayRequest {
        val ArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    val par = CategoryJsonParser()


                    for (i in 0..(response.length() - 1)) {

                        cate.add(par.getCategory(response.getJSONObject(i)))



                    }






                },
                Response.ErrorListener {


                })
        return ArrayRequest
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




    private fun loadDataUser(user:User) {

        // this method extracts data from the mainActivity
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        val headerView = navigationView.getHeaderView(0)
        headerView.userName.text = "${ user.first_name } ${ user.last_name }"

    }



    fun getArray(){}



}


interface VolleyCallback {
    fun onSuccess(result : JSONArray)
}