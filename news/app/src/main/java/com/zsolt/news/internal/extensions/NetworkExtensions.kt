package com.zsolt.news.internal.extensions

import com.zsolt.news.internal.model.BaseModel
import com.zsolt.news.network.NetworkErrorResponse
import com.zsolt.news.network.NetworkResponse
import com.zsolt.news.network.data.BaseDto
import io.reactivex.Maybe
import retrofit2.Call
import java.net.UnknownHostException

fun <T : BaseDto, E : BaseModel> Call<T>.toMaybeCall(mapper: (T) -> E) =
    Maybe.create<NetworkResponse<E>> { emitter ->
        enqueue {
            onResponse = { response ->
                response.body()?.let {
                    val processedResponse = try {
                        NetworkResponse.Success(mapper(it))
                    } catch (e: Exception) {
                        NetworkResponse.Failure(NetworkErrorResponse.OtherError(e))
                    }
                    emitter.onSuccess(processedResponse)
                }
                response.errorBody()?.let {
                    val failure = if (response.code() == 401) {
                        NetworkErrorResponse.AuthenticationError()
                    } else {
                        NetworkErrorResponse.ApiError(it, response.code())
                    }
                    emitter.onSuccess(NetworkResponse.Failure(failure))
                }
            }
            onFailure = { e ->
                val failure = if (e is UnknownHostException) {
                    NetworkErrorResponse.NetworkError(e)
                } else {
                    NetworkErrorResponse.OtherError(e)
                }
                emitter.onSuccess(NetworkResponse.Failure(failure))
            }
        }
    }