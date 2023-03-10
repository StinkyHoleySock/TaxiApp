package com.dmitry.taxiapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.taxiapp.data.repository.TaxiRepositoryImpl
import com.dmitry.taxiapp.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: TaxiRepositoryImpl,
) : ViewModel() {

    private val _listOrders: MutableLiveData<ArrayList<Order>> = MutableLiveData()
    val listOrders: LiveData<ArrayList<Order>> get() = _listOrders

    fun getOrders() {
        viewModelScope.launch {
            _listOrders.value = (repository.getOrdersList().body())
        }
    }

}