package com.example.pfranccino.mydelivery.API.Users

import com.example.pfranccino.mydelivery.Models.FoodDetails
import org.json.JSONObject

class FoodDetailsJsonParser{


    fun getFood(response : JSONObject): FoodDetails {
        val detailFoodJson = response

        with (detailFoodJson) {
            val detailsFoodImage = getJSONObject("images")

            return FoodDetails(
                    getString("uuid"),
                    getString("title"),
                    getString("short_description"),
                    getString("long_description"),
                    detailsFoodImage.getString("small"),
                    detailsFoodImage.getString("large"),
                    getInt("price")

            )
        }
    }


}