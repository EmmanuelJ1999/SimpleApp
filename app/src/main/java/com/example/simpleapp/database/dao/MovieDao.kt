package com.example.simpleapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simpleapp.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: MovieEntity)

    @Query("select * from movies")
    suspend fun getAllMovies(): List<MovieEntity>
}