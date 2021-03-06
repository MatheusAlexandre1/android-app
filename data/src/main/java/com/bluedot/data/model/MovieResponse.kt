package com.bluedot.data.model

import com.bluedot.data.model.GenreResponse
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("genres") val genres: List<GenreResponse>?
)