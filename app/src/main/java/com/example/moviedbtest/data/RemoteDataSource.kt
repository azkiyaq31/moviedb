package com.example.moviedbtest.data

import com.example.moviedbtest.data.model.remote.response.BaseResponse
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.network.MainService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: MainService,
) {

    suspend fun getPopularMovies(): ApiResponse<BaseResponse<List<MovieResponse>>> {
        return appService.getPopularMovies()
    }

    suspend fun getTopRatedMovies(): ApiResponse<BaseResponse<List<MovieResponse>>> {
        return appService.getTopRatedMovies()
    }

    suspend fun getNowPlayingMovies(): ApiResponse<BaseResponse<List<MovieResponse>>> {
        return appService.getNowPlayingMovies()
    }
}

