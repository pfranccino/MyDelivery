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
import android.widget.AdapterView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.CategoryJsonParser
import com.example.pfranccino.mydelivery.Models.Category
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import kotlinx.android.synthetic.main.header.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import org.json.JSONException
import android.widget.ListView
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.example.pfranccino.mydelivery.Activities.Adapters.CategoryList
import com.example.pfranccino.mydelivery.Interfaces.VolleyCallback
import com.example.pfranccino.mydelivery.Volley.VolleySingleton
import com.example.pfranccino.mydelivery.Endpoints.EndPoints

class CategoryActivity : AppCompatActivity() {

    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView? = null
    var categoriesList: MutableList<Category>? = null
    private var listView: ListView? = null




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        // Load Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        //Enable menu y toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        listView = findViewById(R.id.categoriesList)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)



        // Load user data
        val user = intent.getSerializableExtra("objeto") as User
        loadDataUser(user)



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
                    startActivity(Intent(this,MainActivity::class.java).putExtra("user",user))
                }
                R.id.menu_ayuda ->{

                }
                R.id.menu_contacto ->{

                }

            }

            true
        }




        categoriesList = mutableListOf<Category>()

        loadCategories()

        //Click Item
        listView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->


            var category = categoriesList!!.get(position)

            startActivity(Intent(this,FoodDetailsActivity::class.java).putExtra("user",user).putExtra("category",category))

        }


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


    fun getResponse(method: Int, url: String, jsonValue: JSONObject, callback: VolleyCallback) {
        Log.d("call getresponse", "a")

        val strreq = object : StringRequest(Request.Method.GET, url, Response.Listener { Response -> callback.onSuccessResponse(Response) }, Response.ErrorListener { e ->
            e.printStackTrace()
        }) {
            // set headers
            @Throws(com.android.volley.AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Authorization: Basic"] = "a"
                return params
            }
        }

        VolleySingleton.instance?.addToRequestQueue(strreq)
    }


    private fun loadCategories() {
        val stringRequest = StringRequest(Request.Method.GET,
                "http://13.68.139.247/api/food_categories?business_id=eaa75bf2-250b-4856-97b8-3b5f65809e7a",
                Response.Listener<String> { s ->
                    try {
                        val obj = JSONArray(s)

                        val par = CategoryJsonParser()


                        for (i in 0..(obj.length() - 1)) {
                            categoriesList!!.add(par.getCategory(obj.getJSONObject(i)))


                            val adapter = CategoryList(this@CategoryActivity, categoriesList!!)
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


}
