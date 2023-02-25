package com.example.simpleapp.di

import android.content.Context
import androidx.room.Room
import com.example.simpleapp.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "movies_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDataBase) = db.movieDao()
}