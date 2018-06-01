package com.example.pfranccino.mydelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View


class newActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)



        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


    }
}
