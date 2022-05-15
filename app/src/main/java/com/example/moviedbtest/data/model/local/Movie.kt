package com.example.moviedbtest.data.model.local

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String = "",
    @ColumnInfo(name = "genre")
    var genre: String = "",
    @ColumnInfo(name = "overview")
    var overview: String = ""
) : Parcelable
