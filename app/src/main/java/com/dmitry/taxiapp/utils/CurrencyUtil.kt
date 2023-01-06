package com.dmitry.taxiapp.utils

import java.util.Currency

fun String.getCurrencySymbol(): String {
    return Currency.getInstance(this).symbol
}