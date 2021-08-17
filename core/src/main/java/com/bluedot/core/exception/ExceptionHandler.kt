package com.bluedot.core.exception

import android.content.Context
import com.bluedot.core.helpers.NetworkHelper
import com.bluedot.core.R

fun handleException(context: Context?, error: Throwable): Int {
    return when {
        NetworkHelper.hasConnection(context).not() -> R.string.network_error
        error is Error.ServerError -> R.string.server_error
        error is Error.FeatureError -> R.string.feature_error
        else -> R.string.general_error
    }
}