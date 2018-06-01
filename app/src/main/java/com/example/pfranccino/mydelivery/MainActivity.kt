package com.example.pfranccino.mydelivery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginButton.setOnClickListener {
            starActivity(this,newActivity::class.java)
        }

        registerButton.setOnClickListener{
            starActivity(this,RegisterActivity::class.java)
        }

    }






     fun starActivity(activity : Activity , nexActivity: Class<*>){
         val intent  = Intent(activity,nexActivity)
         activity.startActivity(intent)
     }
}
