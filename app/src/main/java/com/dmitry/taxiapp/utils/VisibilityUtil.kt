package com.dmitry.taxiapp.utils

import android.view.View

fun applyVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
