package com.example.moviedbtest.data.repository

import androidx.annotation.WorkerThread
import com.example.moviedbtest.data.ErrorResponseMapper
import com.example.moviedbtest.data.RemoteDataSource
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.uiresponse.BaseUiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun getPopularMovies() = flow {
        var result: BaseUiResponse<List<MovieResponse>>
        val response = remoteDataSource.getPopularMovies()
        response.suspendOnSuccess {
            val responseData = data.results
            result = if (responseData != null) {
                BaseUiResponse.success(responseData, statusCode.code)
            } else {
                BaseUiResponse.error("Data is empty", responseData, statusCode.code)
            }

            emit(result)
        }.suspendOnError {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }.suspendOnException {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }
    }.onStart {
        val loading = BaseUiResponse.loading<List<MovieResponse>>(null)
        emit(loading)
    }.flowOn(ioDispatcher)

    @WorkerThread
    fun getTopRatedMovies() = flow {
        var result: BaseUiResponse<List<MovieResponse>>
        val response = remoteDataSource.getTopRatedMovies()
        response.suspendOnSuccess {
            val responseData = data.results
            result = if (responseData != null) {
                BaseUiResponse.success(responseData, statusCode.code)
            } else {
                BaseUiResponse.error("Data is empty", responseData, statusCode.code)
            }

            emit(result)
        }.suspendOnError {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }.suspendOnException {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }
    }.onStart {
        val loading = BaseUiResponse.loading<List<MovieResponse>>(null)
        emit(loading)
    }.flowOn(ioDispatcher)

    @WorkerThread
    fun getNowPlayingMovies() = flow {
        var result: BaseUiResponse<List<MovieResponse>>
        val response = remoteDataSource.getNowPlayingMovies()
        response.suspendOnSuccess {
            val responseData = data.results
            result = if (responseData != null) {
                BaseUiResponse.success(responseData, statusCode.code)
            } else {
                BaseUiResponse.error("Data is empty", responseData, statusCode.code)
            }

            emit(result)
        }.suspendOnError {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }.suspendOnException {
            val response = ErrorResponseMapper<List<MovieResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }
    }.onStart {
        val loading = BaseUiResponse.loading<List<MovieResponse>>(null)
        emit(loading)
    }.flowOn(ioDispatcher)
}