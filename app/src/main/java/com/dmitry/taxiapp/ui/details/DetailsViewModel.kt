package com.dmitry.taxiapp.ui.details

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.taxiapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


class DetailsViewModel : ViewModel() {

    private val _autoBitmap: MutableLiveData<Bitmap> = MutableLiveData()
    val autoBitmap: LiveData<Bitmap> get() = _autoBitmap
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    fun getAutoImage(imageLink: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = downloadBitmap(Constants.IMAGE_URL + imageLink)
            bitmap?.let { _autoBitmap.postValue(it) }
            _loading.postValue(false)
        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val connection = URL(imageUrl).openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}