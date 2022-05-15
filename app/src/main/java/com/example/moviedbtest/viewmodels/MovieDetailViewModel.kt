package com.example.moviedbtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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