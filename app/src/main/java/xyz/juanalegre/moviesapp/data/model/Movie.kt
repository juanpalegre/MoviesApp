package xyz.juanalegre.moviesapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int = -1,
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val imageBackdrop: String = "",
    @SerializedName("original_language") val language: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("popularity") val popularity: String = "",
    @SerializedName("poster_path") val imagePoster: String = "",
    @SerializedName("release_date") val realeaseDate: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Double = -1.0,
    @SerializedName("vote_count") val voteCount: Int = -1,
    @SerializedName("movie_type") val movieType: String = ""
)

data class MovieList(
    val results: List<Movie> = listOf()
)

//Room
@Entity
data class MovieEntitie(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "image_backdrop")
    val imageBackdrop: String = "",
    @ColumnInfo(name = "language")
    val language: String = "",
    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: String = "",
    @ColumnInfo(name = "image_poster")
    val imagePoster: String = "",
    @ColumnInfo(name = "release_date")
    val realeaseDate: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movieType: String = ""
)

fun List<MovieEntitie>.toMovieList(): MovieList{
    val resultList = mutableListOf<Movie>()
    this.forEach {movieEntitie->
        resultList.add(movieEntitie.toMovie())
    }
    return MovieList(resultList)
}

fun MovieEntitie.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.imageBackdrop,
    this.language,
    this.originalTitle,
    this.overview,
    this.popularity,
    this.imagePoster,
    this.realeaseDate,
    this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
    this.movieType
)

fun Movie.toMovieEntitie(movieType: String): MovieEntitie = MovieEntitie(
    this.id,
    this.adult,
    this.imageBackdrop,
    this.language,
    this.originalTitle,
    this.overview,
    this.popularity,
    this.imagePoster,
    this.realeaseDate,
    this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
    movieType = movieType
)

