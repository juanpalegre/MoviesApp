package xyz.juanalegre.moviesapp.data.local

import xyz.juanalegre.moviesapp.data.model.MovieEntitie
import xyz.juanalegre.moviesapp.data.model.MovieList
import xyz.juanalegre.moviesapp.data.model.toMovieList


class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovieList(): MovieList {
        return movieDao.getAllMovies().filter { it.movieType == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovieList(): MovieList {
        return movieDao.getAllMovies().filter { it.movieType == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovieList(): MovieList {
        return movieDao.getAllMovies().filter { it.movieType == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntitie){
        movieDao.saveMovie(movie)
    }
}