package com.example.moviedbtest.listener

import com.example.moviedbtest.data.model.remote.response.MovieResponse

interface ItemMovieListener {
    fun onItemClick(obj: MovieResponse)
}