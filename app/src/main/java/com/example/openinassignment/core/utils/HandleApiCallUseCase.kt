package com.example.openinassignment.core.utils

import android.util.Log
import retrofit2.Response

class HandleApiCallUseCase {

    suspend operator fun <T> invoke(
        apiFunction: suspend () -> Response<T>,
        TAG: String
    ): Resource<T> {
        try {
            val response = apiFunction()
            if (response.code() == 401)
                return Resource.LoggedOut()
            if (response.isSuccessful)
                return Resource.Success(response.body()!!)
            // TODO - If have a pre-defined shape of Response object, can send a meaningful message
            return Resource.Failure(message = "$TAG: Failed API call", data = response.body())
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            return Resource.Failure("Something went wrong.")
        }
    }

}