package com.example.doei.domain.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object KeyboardUtils {
    fun hideKeyboard(activity: Activity?) {
        val manager = activity?.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager?
        manager
            ?.hideSoftInputFromWindow(
                activity?.currentFocus?.windowToken, 0
            )
    }
}