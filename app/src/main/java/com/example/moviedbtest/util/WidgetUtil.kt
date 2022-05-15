package com.example.moviedbtest.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavOptions
import com.example.moviedbtest.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object WidgetUtil {
    fun getNavOptions(
        enterAnim: Int = R.anim.enter_from_left,
        exitAnim: Int = R.anim.exit_to_right,
        popEnterAnim: Int = R.anim.enter_from_right,
        popExitAnim: Int = R.anim.exit_to_left
    ): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(enterAnim)
            .setExitAnim(exitAnim)
            .setPopEnterAnim(popEnterAnim)
            .setPopExitAnim(popExitAnim)
            .build()
    }
}
