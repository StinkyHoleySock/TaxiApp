package com.dmitry.taxiapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class Order(
    @SerializedName("endAddress") val endAddress: EndAddress,
    @SerializedName("id") val id: Int,
    @SerializedName("orderTime") val orderTime: String,
    @SerializedName("price") val price: Price,
    @SerializedName("startAddress") val startAddress: StartAddress,
    @SerializedName("vehicle") val vehicle: Vehicle
) : Serializable