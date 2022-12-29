package com.dmitry.taxiapp.model

import com.google.gson.annotations.SerializedName

data class EndAddress(
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String
)