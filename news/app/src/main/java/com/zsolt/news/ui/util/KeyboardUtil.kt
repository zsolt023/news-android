package com.zsolt.news.ui.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * This util keyboard hiding method only works, if the activity's currentFocus View object is not null.
 *
 * Which can be null if eg. a dialog is shown.
 */
fun Activity?.hideSoftKeyboard() {
    this?.let {
        val inputMethodManager =
            it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken, 0
            )
        }
    }
}