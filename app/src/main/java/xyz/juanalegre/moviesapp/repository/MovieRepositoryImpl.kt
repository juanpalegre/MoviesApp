package xyz.juanalegre.moviesapp.repository

import xyz.juanalegre.moviesapp.data.local.LocalMovieDataSource
import xyz.juanalegre.moviesapp.data.model.MovieList
import xyz.juanalegre.moviesapp.data.model.toMovieEntitie
import xyz.juanalegre.moviesapp.data.remote.MovieDataSource

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource)
    : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        dataSource.getUpcomingMovieList().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntitie("upcoming"))
        }
        return dataSourceLocal.getUpcomingMovieList()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSource.getTopRatedMovieList().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntitie("toprated"))
        }
        return dataSourceLocal.getTopRatedMovieList()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSource.getPopularMovieList().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntitie("popular"))
        }
        return dataSourceLocal.getPopularMovieList()
    }
}