package com.dmitry.taxiapp.data.repository

import com.dmitry.taxiapp.data.api.TaxiApi
import com.dmitry.taxiapp.model.Orders
import retrofit2.Response
import javax.inject.Inject

class TaxiRepositoryImpl @Inject constructor(
    private val service: TaxiApi
) : TaxiRepository {

    override suspend fun getOrdersList(): Response<Orders> {
        return service.getOrdersList()
    }
}