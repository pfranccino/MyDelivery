package com.example.pfranccino.mydelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.JSONParser
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val url = "https://floating-basin-93872.herokuapp.com/api/auth/login"
        val json = JSONObject()

        loginButton.setOnClickListener {


            json.put("email",emailText.text)
            json.put("password",passwordText.text)



            val que   = Volley.newRequestQueue(this@LoginActivity)
            val req =  object : JsonObjectRequest(Request.Method.POST,url,json ,Response.Listener {

                response->

                    textView2.text = response.getString("token")

            },
                    Response.ErrorListener {

                    })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Accept","application/json")
                    return headers
                }
            }
            que.add(req)

        }


    }
}
