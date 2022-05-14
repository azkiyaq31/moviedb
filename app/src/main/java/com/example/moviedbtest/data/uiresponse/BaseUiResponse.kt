package com.example.moviedbtest.data.uiresponse

data class BaseUiResponse<T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val code: Int
) {
    companion object {
        fun <T> success(data: T?, code: Int = 200): BaseUiResponse<T> =
            BaseUiResponse(Status.SUCCESS, data, null, code)

        fun <T> error(msg: String?, data: T? = null, code: Int = 0): BaseUiResponse<T> =
            BaseUiResponse(Status.ERROR, data, msg, code)

        fun <T> loading(data: T? = null): BaseUiResponse<T> =
            BaseUiResponse(Status.LOADING, data, null, 0)
    }
}
