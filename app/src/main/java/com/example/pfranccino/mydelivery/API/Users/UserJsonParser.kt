package com.example.pfranccino.mydelivery.API.Users

import android.util.JsonToken
import com.example.pfranccino.mydelivery.Models.User

import org.json.JSONObject


class UserJsonParser
{
    fun getUser(response : JSONObject): User {
        val userJSON = response

        with (userJSON) {
            val userObject = getJSONObject("user")

            val user = User(
                    getString("token"),
                    userObject.getString("first_name"),
                    userObject.getString("last_name"),
                    userObject.getString("email")
            )

            return user
        }
    }
}