package com.example.moviedbtest.util

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.findNavController

object NavigationUtil {
    fun navigateFragment(
        view: View,
        id: Int,
        navOptions: NavOptions? = null,
        arguments: Bundle? = null
    ) {
        view.findNavController().navigate(id, arguments, navOptions ?: WidgetUtil.getNavOptions())
    }
}