package xyz.juanalegre.moviesapp.repository

import xyz.juanalegre.moviesapp.core.InternetCheck
import xyz.juanalegre.moviesapp.data.local.LocalMovieDataSource
import xyz.juanalegre.moviesapp.data.model.MovieList
import xyz.juanalegre.moviesapp.data.model.toMovieEntitie
import xyz.juanalegre.moviesapp.data.remote.MovieDataSource

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource)
    : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovieList().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntitie("upcoming"))
            }
            dataSourceLocal.getUpcomingMovieList()
        } else {
            dataSourceLocal.getUpcomingMovieList()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getTopRatedMovieList().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntitie("toprated"))
            }
            dataSourceLocal.getTopRatedMovieList()
        } else {
            dataSourceLocal.getTopRatedMovieList()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getPopularMovieList().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntitie("popular"))
            }
            dataSourceLocal.getPopularMovieList()
        } else {
            dataSourceLocal.getPopularMovieList()
        }
    }
}