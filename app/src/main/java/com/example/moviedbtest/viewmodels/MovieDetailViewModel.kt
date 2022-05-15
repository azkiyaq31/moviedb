package com.example.moviedbtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.model.remote.response.MovieReviewResponse
import com.example.moviedbtest.data.repository.MainRepository
import com.example.moviedbtest.data.uiresponse.Status
import com.example.moviedbtest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var movieId = 0
    val pageReq = SingleLiveEvent<Boolean>()
    val contentResult = SingleLiveEvent<MovieResponse?>()
    val reviewResult = SingleLiveEvent<List<MovieReviewResponse>?>()
    val favoriteState = SingleLiveEvent<Boolean>()

    fun addToFavorite() {
        val movie = Movie(
            contentResult.value?.id ?: 0,
            contentResult.value?.title ?: "",
            contentResult.value?.backdropPath ?: "",
            contentResult.value?.genreResponses?.firstOrNull()?.name ?: "",
            contentResult.value?.overview ?: ""
        )
        viewModelScope.launch {
            mainRepository.addMovieToFavorite(movie).collect {
                when (it.status) {
                    Status.LOADING -> {
                    }

                    Status.SUCCESS -> {
                        favoriteState.postValue(true)
                    }

                    Status.ERROR -> {
                    }
                }
            }
        }
    }

    fun removeFromFavorite() {
        viewModelScope.launch {
            mainRepository.removeMovieFromFavorite(contentResult.value?.id ?: 0).collect {
                when (it.status) {
                    Status.LOADING -> {
                    }

                    Status.SUCCESS -> {
                        favoriteState.postValue(false)
                    }

                    Status.ERROR -> {
                    }
                }
            }
        }
    }

    fun start() {
        viewModelScope.launch {
            mainRepository.getMovieDetail(movieId).collect {
                when (it.status) {
                    Status.LOADING -> {
                        pageReq.postValue(true)
                    }

                    Status.SUCCESS -> {
                        pageReq.postValue(false)
                        contentResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                        pageReq.postValue(false)
                    }
                }
            }

            mainRepository.getFavoriteMovieById(movieId).collect {
                when (it.status) {
                    Status.LOADING -> {
                    }

                    Status.SUCCESS -> {
                        pageReq.postValue(false)
                        favoriteState.postValue(it.data != null)
                    }

                    Status.ERROR -> {
                    }
                }
            }

            mainRepository.getMovieReview(movieId).collect {
                when (it.status) {
                    Status.LOADING -> {
                        pageReq.postValue(true)
                    }

                    Status.SUCCESS -> {
                        pageReq.postValue(false)
                        reviewResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                        pageReq.postValue(false)
                    }
                }
            }
        }
    }
}