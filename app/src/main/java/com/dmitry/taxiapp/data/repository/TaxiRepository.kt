package com.dmitry.taxiapp.data.repository

import com.dmitry.taxiapp.model.Orders
import retrofit2.Response

interface TaxiRepository {
    suspend fun getOrdersList(): Response<Orders>
}