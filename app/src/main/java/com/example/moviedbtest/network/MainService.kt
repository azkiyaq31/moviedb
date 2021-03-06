package com.example.moviedbtest.network

import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.data.model.remote.response.BaseResponse
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.model.remote.response.MovieReviewResponse
import com.example.moviedbtest.network.API.MOVIE_DETAIL
import com.example.moviedbtest.network.API.MOVIE_REVIEWS
import com.example.moviedbtest.network.API.NOW_PLAYING_MOVIE
import com.example.moviedbtest.network.API.POPULAR_MOVIE
import com.example.moviedbtest.network.API.TOP_RATED_MOVIE
import com.example.moviedbtest.util.Constants
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {

    @GET(POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.MOVIE_API_KEY
    ): ApiResponse<BaseResponse<List<MovieResponse>>>

    @GET(TOP_RATED_MOVIE)
    suspend fun getTopRatedMovies(
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.MOVIE_API_KEY
    ): ApiResponse<BaseResponse<List<MovieResponse>>>

    @GET(NOW_PLAYING_MOVIE)
    suspend fun getNowPlayingMovies(
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.MOVIE_API_KEY
    ): ApiResponse<BaseResponse<List<MovieResponse>>>

    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.MOVIE_API_KEY
    ): ApiResponse<MovieResponse>

    @GET(MOVIE_REVIEWS)
    suspend fun getMovieReview(
        @Path("movieId") movieId: Int,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.MOVIE_API_KEY
    ): ApiResponse<BaseResponse<List<MovieReviewResponse>>>


}