package com.dmitry.taxiapp.data.api

import com.dmitry.taxiapp.model.Order
import retrofit2.Response
import retrofit2.http.GET

interface TaxiApi {
    @GET("orders.json")
    suspend fun getOrdersList() : Response<ArrayList<Order>>
}