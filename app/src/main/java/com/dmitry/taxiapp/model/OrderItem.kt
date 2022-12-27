package com.dmitry.taxiapp.model

data class OrderItem(
    val endAddress: EndAddress,
    val id: Int,
    val orderTime: String,
    val price: Price,
    val startAddress: StartAddress,
    val vehicle: Vehicle
)