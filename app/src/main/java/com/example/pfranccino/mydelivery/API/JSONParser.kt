package com.example.pfranccino.mydelivery.API

import android.util.JsonToken
import com.example.pfranccino.mydelivery.Models.Users
import org.json.JSONObject


class JSONParser{

    fun getUser(response : JSONObject): Users {

        val userJSON  = response.getJSONObject("user")

        with(userJSON){
            val user = Users(

                             getString("first_name"),
                             getString("last_name"),
                             getString("email"),
                             getString("password"),
                             getString("password_confirmation"))

                    return user


    }

    }
    }