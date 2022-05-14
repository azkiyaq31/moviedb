package com.example.moviedbtest.moduledi

import android.app.Application
import androidx.room.Room
import com.example.moviedbtest.db.MainDatabase
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
    fun provideDatabase(application:Application):MainDatabase{
        return Room.databaseBuilder(application,MainDatabase::class.java,"moviedb.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}