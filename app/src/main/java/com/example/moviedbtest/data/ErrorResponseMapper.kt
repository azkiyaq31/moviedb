package com.example.moviedbtest.data

import com.example.moviedbtest.data.model.remote.response.BaseResponse
import com.google.gson.Gson
import com.skydoves.sandwich.ApiResponse

class ErrorResponseMapper<T> {
    fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): BaseResponse<T> {
        val response = Gson().fromJson<BaseResponse<T>>(
            apiErrorResponse.errorBody?.charStream(),
            BaseResponse::class.java
        )
        val code = apiErrorResponse.statusCode.code
        return BaseResponse(
            response.page,
            response.results
        )
    }

    fun map(apiErrorResponse: ApiResponse.Failure.Exception<*>): BaseResponse<T> {
        return BaseResponse(0, null)
    }
}
