package com.example.moviedbtest.util

import com.example.moviedbtest.util.Constants.BEARER_TOKEN
import com.example.moviedbtest.util.PrefUtil.getData

object NetworkUtil {
    fun getAuthHeader(): HashMap<String, String> {
        val header = HashMap<String, String>()
        header["Authorization"] = getData(BEARER_TOKEN)
        return header
    }
}