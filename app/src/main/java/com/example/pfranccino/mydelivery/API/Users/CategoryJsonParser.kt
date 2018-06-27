package com.example.pfranccino.mydelivery.API.Users
import com.example.pfranccino.mydelivery.Models.Category
import org.json.JSONObject

class CategoryJsonParser {

    fun getCategory(response : JSONObject): Category {
        val categoryJSON = response

        with (categoryJSON) {
            val imageObject = getJSONObject("images")

            val category = Category(
                    getString("uuid"),
                    getString("business_id"),
                    getString("title"),
                    getString("short_description"),
                    getString("long_description"),
                    imageObject.getString("small"),
                    imageObject.getString("large")

            )

            return category
        }
    }

}