package com.bluedot.core.extensions

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import coil.load
import com.bluedot.core.R
import com.bluedot.data.BuildConfig
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadFromUrl(image: String?) {
    this.load(BuildConfig.BASE_IMAGE_URL.plus(image)) {
        crossfade(true)
        placeholder(R.drawable.core_img_default)
    }
}

fun Activity.showSnack(@StringRes resId: Int) {
    Snackbar.make(getView(), resId, Snackbar.LENGTH_SHORT).show()
}

private fun Activity.getView(): View = findViewById(android.R.id.content)