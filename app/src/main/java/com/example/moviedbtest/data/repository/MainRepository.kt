package com.example.moviedbtest.data.repository

import androidx.annotation.WorkerThread
import com.example.moviedbtest.data.ErrorResponseMapper
import com.example.moviedbtest.data.RemoteDataSource
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.model.remote.response.MovieReviewResponse
import com.example.moviedbtest.data.uiresponse.BaseUiResponse
import com.example.moviedbtest.db.MovieDao
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun addMovieToFavorite(movie: Movie) = flow {
        Timber.e("succes insert")
        movieDao.insertMovie(movie)
        val result: BaseUiResponse<Movie> = BaseUiResponse.success(null)
        emit(result)
    }.onStart {
        Timber.e("start insert")
        val loading: BaseUiResponse<Movie> = BaseUiResponse.loading(null)
        emit(loading)
    }.catch { errorResponse ->
        Timber.e("catch insert ${errorResponse.message}")
        val error: BaseUiResponse<Movie> = BaseUiResponse.error("Terjadi kesalahan")
        emit(error)
    }.flowOn(ioDispatcher)

    @WorkerThread
    fun removeMovieFromFavorite(movieId: Int) = flow {
        Timber.e("succes delete")
        movieDao.deleteMovieById(movieId)
        val result: BaseUiResponse<Movie> = BaseUiResponse.success(null)
        emit(result)
    }.onStart {
        Timber.e("start delete")
        val loading: BaseUiResponse<Movie> = BaseUiResponse.loading(null)
        emit(loading)
    }.catch { errorResponse ->
        Timber.e("catch delete ${errorResponse.message}")
        val error: BaseUiResponse<Movie> = BaseUiResponse.error("Terjadi kesalahan")
        emit(error)
    }.flowOn(ioDispatcher)

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

    @WorkerThread
    fun getMovieReview(movieId: Int) = flow {
        var result: BaseUiResponse<List<MovieReviewResponse>>
        val response = remoteDataSource.getMovieReview(movieId)
        response.suspendOnSuccess {
            val responseData = data.results
            result = if (responseData != null) {
                BaseUiResponse.success(responseData, statusCode.code)
            } else {
                BaseUiResponse.error("Data is empty", responseData, statusCode.code)
            }

            emit(result)
        }.suspendOnError {
            val response = ErrorResponseMapper<List<MovieReviewResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }.suspendOnException {
            val response = ErrorResponseMapper<List<MovieReviewResponse>>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }
    }.onStart {
        val loading = BaseUiResponse.loading<List<MovieReviewResponse>>(null)
        emit(loading)
    }.flowOn(ioDispatcher)

    @WorkerThread
    fun getMovieDetail(movieId: Int) = flow {
        var result: BaseUiResponse<MovieResponse>
        val response = remoteDataSource.getMovieDetail(movieId)
        response.suspendOnSuccess {
            val responseData = data
            result = BaseUiResponse.success(responseData, statusCode.code)
            emit(result)
        }.suspendOnError {
            val response = ErrorResponseMapper<MovieResponse>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }.suspendOnException {
            val response = ErrorResponseMapper<MovieResponse>().map(this)
            result = BaseUiResponse.error("Error", response.results, 0)
            emit(result)
        }
    }.onStart {
        val loading = BaseUiResponse.loading<MovieResponse>(null)
        emit(loading)
    }.flowOn(ioDispatcher)

    private suspend fun getFavMovies(): List<Movie>? = movieDao.getFavoriteMovie()

    private suspend fun getFavMovieById(movieId: Int): Movie? =
        movieDao.getFavoriteMovieById(movieId)

    fun getFavoriteMovieById(movieId: Int) = flow {
        val response = getFavMovieById(movieId)
        emit(BaseUiResponse.success(response))
    }.onStart {
        val loading = BaseUiResponse.loading<Movie>(null)
        emit(BaseUiResponse.loading())
    }.flowOn(ioDispatcher)

    fun getFavoriteMovies() = flow {
        val response = getFavMovies()
        emit(BaseUiResponse.success(response))
    }.onStart {
        val loading = BaseUiResponse.loading<List<Movie>>(null)
        emit(loading)
    }.flowOn(ioDispatcher)
}