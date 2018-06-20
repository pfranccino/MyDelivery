package com.example.pfranccino.mydelivery.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import kotlinx.android.synthetic.main.header.view.*


class newActivity : AppCompatActivity() {






    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)



        loadName()


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)


        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


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



    private fun loadName() {

        // this method extracts data from the mainActivity

        val user = intent.getSerializableExtra("objeto") as User
        val navigationView = findViewById(R.id.navigation_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        headerView.userName.text = "${ user.first_name } ${ user.last_name }"

        }
    }

