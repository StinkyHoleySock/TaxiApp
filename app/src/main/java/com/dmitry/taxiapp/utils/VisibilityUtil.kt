package com.dmitry.taxiapp.utils

import android.view.View

fun View.applyVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}