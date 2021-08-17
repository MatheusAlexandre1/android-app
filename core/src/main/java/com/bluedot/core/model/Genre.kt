package com.bluedot.core.model

import com.bluedot.data.model.GenreResponse

class Genre(val id: Int, val name: String) {
    constructor(response: GenreResponse) : this(
        id = response.id ?: 0,
        name = response.name ?: ""
    )
}