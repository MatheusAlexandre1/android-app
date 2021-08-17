package com.bluedot.core.extensions

import com.bluedot.core.model.Genre
import com.google.gson.Gson

fun String.getGenres(): List<Genre> {
    return if (this.isEmpty()) emptyList()
    else Gson().fromJson(this, Array<Genre>::class.java).asList()
}