package com.dmitry.taxiapp.ui.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.taxiapp.data.repository.TaxiRepositoryImpl
import com.dmitry.taxiapp.utils.Communication
import javax.inject.Inject
import com.dmitry.taxiapp.model.Orders
import com.dmitry.taxiapp.utils.Observe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListViewModel @Inject constructor(
    private val repository: TaxiRepositoryImpl,
    private val communication: Communication
): Observe, ViewModel() {
    init {
        viewModelScope.launch {
            getOrders()?.let { orders -> communication.map(orders) }
        }
    }

    private suspend fun getOrders(): Orders? = withContext(Dispatchers.Default) {
        repository.getOrdersList().body()
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Orders>) {
        communication.observe(owner, observer)
    }
}