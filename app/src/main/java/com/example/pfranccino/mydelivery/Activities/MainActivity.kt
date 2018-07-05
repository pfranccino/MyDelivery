package com.example.pfranccino.mydelivery.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pfranccino.mydelivery.R
import com.example.pfranccino.mydelivery.R.id.loginButton
import com.example.pfranccino.mydelivery.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<View>(R.id.layout)


        loginButton.setOnClickListener {
            starActivity(this, LoginActivity::class.java)

        }


        registerButton.setOnClickListener{

                starActivity(this, RegisterActivity::class.java)

        }


        status(layout)








    }

    private fun status(layout: View) :Boolean{
        if (!isOnline()) {

            registerButton.isEnabled = false
            loginButton.isEnabled = false

            Snackbar.make(layout, "CONECTAR INTERNET Y REFRESCAR", Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(Color.RED)
                    .setAction("REFRESCAR") {
                        if (isOnline()) {
                            registerButton.isEnabled = true
                            loginButton.isEnabled = true
                        }
                    }
                    .show()

        }
        return true
    }

    fun starActivity(activity : Activity , nexActivity: Class<*>){
         val intent  = Intent(activity,nexActivity)
         activity.startActivity(intent)

     }

    fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }



}
