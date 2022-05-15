package com.example.moviedbtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.data.repository.MainRepository
import com.example.moviedbtest.data.uiresponse.Status
import com.example.moviedbtest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val favMovieResult = SingleLiveEvent<List<Movie>?>()
    fun start() {
        viewModelScope.launch {
            mainRepository.getFavoriteMovies().collect {
                when (it.status) {
                    Status.LOADING -> {
                    }

                    Status.SUCCESS -> {
                        favMovieResult.postValue(it.data)
                    }

                    Status.ERROR -> {
                    }
                }
            }
        }
    }
}