package com.example.moviedbtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.data.model.remote.response.MovieResponse

@Dao
interface MovieDao {


    @Query("DELETE FROM Movie WHERE id == :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)


    @Query("SELECT * FROM MOVIE")
    suspend fun getFavoriteMovie(): List<Movie>?

    @Query("SELECT * FROM MOVIE WHERE id == :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): Movie?
}