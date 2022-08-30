package xyz.juanalegre.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.juanalegre.moviesapp.data.model.MovieEntitie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentitie")
    suspend fun getAllMovies(): List<MovieEntitie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntitie)
}