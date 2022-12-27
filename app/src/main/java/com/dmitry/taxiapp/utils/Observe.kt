package com.dmitry.taxiapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.dmitry.taxiapp.model.Orders

interface Observe {
    fun observe(owner: LifecycleOwner, observer: Observer<Orders>)
}