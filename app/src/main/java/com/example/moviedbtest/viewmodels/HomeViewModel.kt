package com.example.moviedbtest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.repository.MainRepository
import com.example.moviedbtest.data.uiresponse.Status
import com.example.moviedbtest.util.SingleLiveEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val nowPlayMovieRequesting = SingleLiveEvent<Boolean>()
    val popMoviesRequesting = SingleLiveEvent<Boolean>()
    val topRatedMoviesRequesting = SingleLiveEvent<Boolean>()
    val popularMoviesResult = SingleLiveEvent<List<MovieResponse>?>()
    val topRatedMoviesResult = SingleLiveEvent<List<MovieResponse>?>()
    val nowPlayResult = SingleLiveEvent<List<MovieResponse>?>()

    fun start() {
        getPopularMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            mainRepository.getNowPlayingMovies().collect {
                when (it.status) {
                    Status.LOADING -> {
                        nowPlayMovieRequesting.postValue(true)
                    }

                    Status.SUCCESS -> {
                        nowPlayMovieRequesting.postValue(false)
                        nowPlayResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                        nowPlayMovieRequesting.postValue(false)
                    }
                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            mainRepository.getTopRatedMovies().collect {
                when (it.status) {
                    Status.LOADING -> {
                        topRatedMoviesRequesting.postValue(true)
                    }

                    Status.SUCCESS -> {
                        topRatedMoviesRequesting.postValue(false)
                        topRatedMoviesResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                        topRatedMoviesRequesting.postValue(false)
                    }
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            mainRepository.getPopularMovies().collect {
                when (it.status) {
                    Status.LOADING -> {
                        popMoviesRequesting.postValue(true)
                    }

                    Status.SUCCESS -> {
                        popMoviesRequesting.postValue(false)
                        popularMoviesResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                        popMoviesRequesting.postValue(false)
                    }
                }
            }
        }
    }

}