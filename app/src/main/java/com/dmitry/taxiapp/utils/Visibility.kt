package com.dmitry.taxiapp.utils

import android.view.View

class Visibility {

    fun applyVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}