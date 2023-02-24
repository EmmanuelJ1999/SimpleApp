package com.example.simpleapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleapp.database.dao.MovieDao
import com.example.simpleapp.database.entities.MovieEntity

@Database(
    entities = [
    MovieEntity::class],
    version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun movieDao(): MovieDao
}