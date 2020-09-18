package com.example.imdb_test.data

/**
 * Created by M.Ganjian on 5/23/2019.
 */


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class NetworkError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    data class GenericError(val error: String? = "") : ResultWrapper<Nothing>()
}