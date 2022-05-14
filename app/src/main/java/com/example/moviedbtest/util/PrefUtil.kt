package com.example.moviedbtest.util

import com.example.moviedbtest.application.BaseApplication

object PrefUtil {
    fun setData(keyword: String?, value: String?) {
        BaseApplication.getInstance().getSharedPreference().edit().putString(keyword, value).apply()
    }

    fun getData(keyword: String): String {
        return BaseApplication.getInstance().getSharedPreference().getString(keyword, "")!!
    }

    fun removeData(keyword: String?) {
        BaseApplication.getInstance().getSharedPreference().edit().remove(keyword).apply()
    }
}