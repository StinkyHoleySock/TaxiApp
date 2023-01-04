package com.dmitry.taxiapp.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.taxiapp.data.repository.TaxiRepositoryImpl
import com.dmitry.taxiapp.model.Order
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: TaxiRepositoryImpl,
) : ViewModel() {

    private val _listOrders: MutableLiveData<ArrayList<Order>> = MutableLiveData()
    val listOrders: LiveData<ArrayList<Order>> get() = _listOrders

    init {
        getOrders()
    }

    private fun getOrders() {
        viewModelScope.launch {
            _listOrders.value = (repository.getOrdersList().body())
        }
    }

}