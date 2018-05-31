package com.example.pfranccino.mydelivery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.UserJsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val url = "https://floating-basin-93872.herokuapp.com/api/auth/login"

        val jsonp = UserJsonParser()
        val loginUserPostBody = JSONObject()



        loginButton.setOnClickListener {


            loginUserPostBody.put("email",emailText.text)
            loginUserPostBody.put("password",passwordText.text)


            val que   = Volley.newRequestQueue(this@LoginActivity)
            val req = jsonObjectRequest(url, loginUserPostBody, jsonp)

            que.add(req)


        }


    }





    private fun jsonObjectRequest(url: String, json: JSONObject, jsonp: UserJsonParser): JsonObjectRequest {
        return object : JsonObjectRequest(Method.POST, url, json, Response.Listener {
            response ->
            // Success

            jsonp.getUser(response)
            starActivity(this, MainActivity::class.java)

        },
            Response.ErrorListener {
                // Handle error

            }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()

                headers.put("Accept", "application/json")

                return headers
            }
        }
    }

    fun starActivity(activity : Activity, nexActivity: Class<*>){
        val intent  = Intent(activity,nexActivity)
        activity.startActivity(intent)
    }
}
