package com.dmitry.taxiapp.ui.details

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.taxiapp.utils.Constants
import com.dmitry.taxiapp.utils.ImageCache
import com.dmitry.taxiapp.utils.downloadBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsViewModel : ViewModel() {

    private val _autoBitmap: MutableLiveData<Bitmap> = MutableLiveData()
    val autoBitmap: LiveData<Bitmap> get() = _autoBitmap

    fun getAutoImage(imageLink: String, orderId: Int){

        val cachedImage = ImageCache.autoMap[orderId]
        if (cachedImage != null) {
            cachedImage.let { _autoBitmap.postValue(it) }
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = downloadBitmap(Constants.IMAGE_URL + imageLink)
            bitmap?.let { _autoBitmap.postValue(it) }
            bitmap?.let { ImageCache.autoMap[orderId] = it }
        }
    }
}