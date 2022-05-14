package com.example.moviedbtest.network

import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.data.model.remote.response.BaseResponse
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.util.Constants.API_KEY
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

object API {
    const val POPULAR_MOVIE = "movie/popular"
    const val TOP_RATED_MOVIE = "movie/top_rated"
    const val NOW_PLAYING_MOVIE = "movie/now_playing"
}