package xyz.juanalegre.moviesapp.data.remote

import xyz.juanalegre.moviesapp.application.AppConstants
import xyz.juanalegre.moviesapp.data.model.MovieList
import xyz.juanalegre.moviesapp.repository.RetrofitClient.webService
import xyz.juanalegre.moviesapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovieList(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovieList(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovieList(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}