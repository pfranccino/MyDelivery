package com.example.pfranccino.mydelivery.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pfranccino.mydelivery.API.Users.UserJsonParser
import com.example.pfranccino.mydelivery.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val url = "http://13.68.139.247/api/auth/register"
        val JSONParser = UserJsonParser()

        val json  = JSONObject()


           createButton.setOnClickListener {



            json.put("first_name",nombreTxt.text.toString())
            json.put("last_name",apellidoTxt.text.toString())
            json.put("email",emailTxt.text.toString())
            json.put("password",passTxt.text.toString())
            json.put("password_confirmation",pass2Txt.text.toString())


            val que   = Volley.newRequestQueue(this@RegisterActivity)
            val req = object : JsonObjectRequest(Request.Method.POST,url,json,
                    Response.Listener { response->


                    },
                    Response.ErrorListener {response->

                        val response = response.networkResponse
                        when (response.statusCode) {
                            422 -> {

                                val body = String(response.data)

                                val errorObject = JSONObject(body)


                                val errors = errorObject.getJSONObject("errors")

                                val keys = errors.keys()

                                for(i in keys){

                                    if(i.equals("first_name")){
                                        nombreTxt.error = "Corregir Campo"
                                    }
                                    if(i.equals("last_name")){
                                        apellidoTxt.error = "Corregir campo"
                                    }
                                    if(i.equals("email")){
                                        emailTxt.error = "Corregir campo"
                                    }
                                    if(i.equals("password")){
                                        passTxt.error ="Corregir campo"
                                    }
                                    if(i.equals("password_confirmation")){
                                        pass2Txt.error ="Corregir campo"
                                    }

                                    if(!pass2Txt.text.equals(passTxt.text)){

                                        pass2Txt.error = "Deben coinicir contraseñas"
                                    }

                                    if(passTxt.length()< 6){
                                        pass2Txt.error ="Minimo 6 caracteres"
                                    }

                                }

                            }


                            400 -> {

                            }

                            else -> {

                            }
                        }                    })
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
