package com.dmitry.taxiapp.data.api

import com.dmitry.taxiapp.model.Orders
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TaxiApi {
    @GET("orders.json")
    suspend fun getOrdersList() : Response<Orders>
}