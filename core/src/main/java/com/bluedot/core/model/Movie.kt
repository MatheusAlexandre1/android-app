package com.bluedot.core.model

import com.bluedot.core.extensions.getGenres
import com.bluedot.data.model.MovieResponse
import com.bluedot.data.model.entity.MovieEntity
import com.google.gson.Gson

data class Movie(
    val popularity: Double? = 0.0,
    val voteCount: Int? = 0,
    val posterPath: String? = "",
    val id: Int = 0,
    val backdropPath: String? = "",
    val title: String? = "",
    val voteAverage: Double? = 0.0,
    val overview: String? = "",
    val releaseDate: String? = "",
    val runtime: String? = "",
    val originalLanguage: String? = "",
    val genres: List<Genre> = listOf(),
    var isFavorite: Boolean = false
) {
    constructor(response: MovieResponse) : this(
        id = response.id,
        title = response.title,
        popularity = response.popularity,
        voteCount = response.voteCount,
        posterPath = response.posterPath,
        backdropPath = response.backdropPath,
        voteAverage = response.voteAverage,
        overview = response.overview,
        releaseDate = response.releaseDate,
        runtime = response.runtime,
        originalLanguage = response.originalLanguage,
        genres = response.genres?.map(::Genre) ?: listOf()
    )

    constructor(entity: MovieEntity) : this(
        id = entity.id,
        title = entity.title,
        popularity = entity.popularity,
        voteCount = entity.voteCount,
        posterPath = entity.posterPath,
        backdropPath = entity.backdropPath,
        voteAverage = entity.voteAverage,
        overview = entity.overview,
        releaseDate = entity.releaseDate,
        runtime = entity.runtime,
        originalLanguage = entity.originalLanguage,
        genres = entity.genres?.getGenres() ?: emptyList()
    )
}

fun Movie.toEntityRequest(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        popularity = this.popularity,
        voteCount = this.voteCount,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        voteAverage = this.voteAverage,
        overview = this.overview,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        originalLanguage = this.originalLanguage,
        genres = Gson().toJson(this.genres)
    )
}