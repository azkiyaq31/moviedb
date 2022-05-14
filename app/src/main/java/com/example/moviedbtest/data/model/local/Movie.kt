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
    var id: Int = 0
) : Parcelable
