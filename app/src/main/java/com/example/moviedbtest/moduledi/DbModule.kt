package com.example.moviedbtest.moduledi

import android.app.Application
import androidx.room.Room
import com.example.moviedbtest.db.MainDatabase
import com.example.moviedbtest.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MainDatabase {
        return Room.databaseBuilder(application, MainDatabase::class.java, "moviedb.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(mainDatabase: MainDatabase): MovieDao {
        return mainDatabase.movieDao()
    }
}