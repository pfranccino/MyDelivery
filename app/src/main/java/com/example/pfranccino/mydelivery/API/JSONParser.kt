package com.example.pfranccino.mydelivery.API

import android.util.JsonToken
import com.example.pfranccino.mydelivery.Models.User

import org.json.JSONObject


class JSONParser{

    fun getUser(response : JSONObject): User {

        val userJSON  = response

        with(userJSON){
                      val user = User(  getString("success"),
                                        getString("token"),
                                        getJSONObject("user").getString("first_name"),
                                        getJSONObject("user").getString("last_name"),
                                        getJSONObject("user").getString("email"))

                    return user


    }

    }
    }