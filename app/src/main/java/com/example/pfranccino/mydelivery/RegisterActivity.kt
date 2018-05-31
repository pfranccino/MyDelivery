package com.example.pfranccino.mydelivery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.UserJsonParser
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val url = "https://floating-basin-93872.herokuapp.com/api/auth/register"
        val JSONParser = UserJsonParser()

        val json  = JSONObject()


        imageButton.setOnClickListener { starActivity(this,MainActivity::class.java)  }

        createButton.setOnClickListener {



            json.put("first_name",nombreTxt.text.toString())
            json.put("last_name",apellidoTxt.text.toString())
            json.put("email",emailTxt.text.toString())
            json.put("password",passTxt.text.toString())
            json.put("password_confirmation",pass2Txt.text.toString())


            textView11.text = passTxt.text.toString()



            val que   = Volley.newRequestQueue(this@RegisterActivity)
            val req = object : JsonObjectRequest(Request.Method.POST,url,json,
                    Response.Listener { response->


                    },
                    Response.ErrorListener {

                        Toast.makeText(this,"LA LIAMOS",Toast.LENGTH_LONG).show()
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

    fun starActivity(activity : Activity, nexActivity: Class<*>){
        val intent  = Intent(activity,nexActivity)
        activity.startActivity(intent)
    }
}
