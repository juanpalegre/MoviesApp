package xyz.juanalegre.moviesapp.repository

import xyz.juanalegre.moviesapp.data.model.MovieList
import xyz.juanalegre.moviesapp.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return dataSource.getUpcomingMovieList()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return dataSource.getTopRatedMovieList()
    }

    override suspend fun getPopularMovies(): MovieList {
        return dataSource.getPopularMovieList()
    }
}