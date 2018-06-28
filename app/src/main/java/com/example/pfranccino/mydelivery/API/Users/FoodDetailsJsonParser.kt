package com.example.pfranccino.mydelivery.API.Users

import com.example.pfranccino.mydelivery.Models.FoodDetails
import com.example.pfranccino.mydelivery.Models.User
import org.json.JSONObject

class FoodDetailsJsonParser{


    fun getFood(response : JSONObject): FoodDetails {
        val detailFoodJson = response

        with (detailFoodJson) {
            val detailsFoodImage = getJSONObject("images")

            val foodDetails = FoodDetails(
                    getString("title"),
                    getString("short_description"),
                    getString("long_description"),
                    detailsFoodImage.getString("small"),
                    detailsFoodImage.getString("large")

            )

            return foodDetails
        }
    }


}