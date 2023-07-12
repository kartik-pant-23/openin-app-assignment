package com.example.openinassignment.core.utils

sealed class Resource<T>(
    val data: T?,
    val message: String
) {

    class Success<T>(data: T, message: String? = null) :
        Resource<T>(data = data, message = message ?: "Task successful.")

    class Failure<T>(message: String, data: T? = null) :
        Resource<T>(data = data, message = message)

    class LoggedOut<T>(): Resource<T>(data = null, message = "User logged out.")
}
