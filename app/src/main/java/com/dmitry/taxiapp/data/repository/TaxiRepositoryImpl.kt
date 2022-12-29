package com.dmitry.taxiapp.data.repository

import com.dmitry.taxiapp.data.api.TaxiApi
import com.dmitry.taxiapp.model.Order
import retrofit2.Response
import javax.inject.Inject

class TaxiRepositoryImpl @Inject constructor(
    private val service: TaxiApi
) : TaxiRepository {

    override suspend fun getOrdersList(): Response<ArrayList<Order>> {
        return service.getOrdersList()
    }
}