package xyz.juanalegre.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int = -1,
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val imageBackdrop: String = "",
    @SerializedName("genre_ids") val genresIds: List<Int> = listOf(),
    @SerializedName("original_language") val language: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("popularity") val popularity: String = "",
    @SerializedName("poster_path") val imagePoster: String = "",
    @SerializedName("release_date") val realeaseDate: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Double = -1.0,
    @SerializedName("vote_count") val voteCount: Int = -1
)

data class MovieList(
    val results: List<Movie> = listOf()
)
