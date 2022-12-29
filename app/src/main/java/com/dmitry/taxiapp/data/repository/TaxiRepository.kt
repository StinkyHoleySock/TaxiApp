package com.dmitry.taxiapp.data.repository

import com.dmitry.taxiapp.model.Order
import retrofit2.Response

interface TaxiRepository {
    suspend fun getOrdersList(): Response<ArrayList<Order>>
}