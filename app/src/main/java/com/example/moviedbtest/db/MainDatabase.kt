package com.example.moviedbtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedbtest.data.model.local.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}