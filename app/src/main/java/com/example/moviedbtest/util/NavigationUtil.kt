package com.example.moviedbtest.util

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
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

    fun navigateFragment(
        navController: NavController,
        id: Int,
        navOptions: NavOptions? = null,
        arguments: Bundle? = null
    ) {
        navController.navigate(id, arguments, navOptions ?: WidgetUtil.getNavOptions())
    }
}