package com.example.moviedbtest.data.model.remote.response

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val page: Int,
    val results: T?,
)
