package com.example.pfranccino.mydelivery.Models

import java.io.Serializable


class FoodDetails(var uuid:String, var title:String,var short_description:String,var long_description:String,var image_short:String,var image_large:String,var price:Int):Serializable{}

