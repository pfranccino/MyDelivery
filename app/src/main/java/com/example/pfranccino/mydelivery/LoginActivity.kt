package com.example.pfranccino.mydelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
            val req = object : JsonObjectRequest(Request.Method.POST,url,json, Response.Listener {

                response -> Toast.makeText(this,"LOGEADO",Toast.LENGTH_LONG).show()
            },
                    Response.ErrorListener {
                        Toast.makeText(this,"CUECK",Toast.LENGTH_LONG).show()
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
