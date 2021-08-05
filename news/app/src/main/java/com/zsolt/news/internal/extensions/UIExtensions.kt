package com.zsolt.news.internal.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun String.shortSnackbar(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}

fun String.longSnackbar(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_LONG).show()
}

fun String.indefiniteSnackbar(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_INDEFINITE).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}