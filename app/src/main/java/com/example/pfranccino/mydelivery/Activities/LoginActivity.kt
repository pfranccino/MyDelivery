package com.example.pfranccino.mydelivery.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.UserJsonParser
import com.example.pfranccino.mydelivery.Models.User
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import com.example.pfranccino.mydelivery.R


class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val url = "http://13.68.139.247/api/auth/login"

        val jsonp = UserJsonParser()
        val loginUserPostBody = JSONObject()
        val objeto:User




        loginButton.setOnClickListener {


            loginUserPostBody.put("email",emailText.text)
            loginUserPostBody.put("password",passwordText.text)


            val que   = Volley.newRequestQueue(this@LoginActivity)
            val req = jsonObjectRequest(url, loginUserPostBody, jsonp)

            que.add(req)


        }


    }





    private fun jsonObjectRequest(url: String, json: JSONObject, jsonp: UserJsonParser): JsonObjectRequest {
        return object : JsonObjectRequest(Method.POST, url, json, Response.Listener { response ->
            // Success



            val objeto:User = jsonp.getUser(response)

            startActivity(Intent(this,CategoryActivity::class.java).putExtra("objeto",objeto))
            finish()

        },
            Response.ErrorListener { response ->

                val response = response.networkResponse
                when (response.statusCode) {
                    422 -> {

                        val body = String(response.data)

                        val errorObject = JSONObject(body)


                        val errors = errorObject.getJSONObject("errors")

                        val keys = errors.keys()

                        for(i in keys){

                                Log.d("help",i)


                        }

                    }


                    400 -> {

                    }

                    else -> {

                    }
                }



            }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()

                headers.put("Accept", "application/json")

                return headers
            }
        }
    }


}
