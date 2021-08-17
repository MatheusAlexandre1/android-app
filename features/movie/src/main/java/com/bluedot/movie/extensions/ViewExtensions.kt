package com.bluedot.movie.extensions

import android.widget.ImageView
import com.bluedot.movie.R

fun ImageView.isFavorite() {
    this.setImageResource(R.drawable.movie_ic_heart_filled)
}

fun ImageView.isNotFavorite() {
    this.setImageResource(R.drawable.movie_ic_heart)
}