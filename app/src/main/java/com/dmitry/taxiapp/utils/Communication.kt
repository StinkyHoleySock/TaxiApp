package com.dmitry.taxiapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dmitry.taxiapp.model.Orders

interface Communication : Observe {

    fun map(value: Orders)

    class Base(private val livedata: MutableLiveData<Orders>) : Communication {

        override fun observe(owner: LifecycleOwner, observer: Observer<Orders>) {
            livedata.observe(owner, observer)
        }

        override fun map(value: Orders) {
            livedata.value = value
        }
    }
}