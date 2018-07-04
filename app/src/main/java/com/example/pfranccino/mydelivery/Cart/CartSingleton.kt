package com.example.pfranccino.mydelivery.Cart

import android.app.Application
import android.util.Log
import com.example.pfranccino.mydelivery.Models.Cart
import com.example.pfranccino.mydelivery.Models.FoodDetails

class CartSingleton : Application() {
    private var _cart: Cart? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val cart: Cart?
        get() {
            if (_cart == null) {
                Log.d("creating new field again", "true")
                _cart = Cart(ArrayList<FoodDetails>())
            }
            return _cart
        }


    fun hasProductInCart(item : FoodDetails) : Boolean {
        var hasItem = false

        for (i in 0.._cart!!.categoriesList.size - 1) {

            if (_cart!!.categoriesList.get(i).uuid == item.uuid) {
                hasItem = true
            }

        }

        return hasItem
    }

    fun addItem(item: FoodDetails): Boolean {
        val result = this._cart?.categoriesList!!.add(item)

        return result
    }

    fun getQuantity(): Int {
        return this._cart?.categoriesList!!.size
    }

    companion object {
        private val TAG = CartSingleton::class.java.simpleName
        @get:Synchronized var instance: CartSingleton? = null
            private set
    }
}