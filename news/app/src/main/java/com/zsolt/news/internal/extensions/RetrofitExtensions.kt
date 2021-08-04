package com.zsolt.news.internal.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueue(callback: CallbackKt<T>.() -> Unit) {
    val callbackKt = CallbackKt<T>()
    callback.invoke(callbackKt)
    this.enqueue(callbackKt)
}

class CallbackKt<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable) -> Unit)? = null

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

}
