package com.example.imdb_test.utils

import com.example.imdb_test.data.ErrorResponse
import com.example.imdb_test.data.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class SafeApiCaller {

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.GenericError("خطای نامشخص رخ داده است.")
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.NetworkError(code, errorResponse)
                    }
                    is IllegalArgumentException -> ResultWrapper.GenericError("لطفا از کاراکترهای مجاز استفاده کنید.")
                    else -> {
                        ResultWrapper.NetworkError(-1, ErrorResponse(-1,"خطای نامشخص اتفاق افتاده است."))
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody().let {
                val jsonS: String = it!!.string()
                val jObject = JSONObject(jsonS)
                val status = jObject.getString("status")
                val message = jObject.getString("message")
                //val status = throwable.code()
                //val message = throwable.message()
                ErrorResponse(
                    status = status.toInt(),
                    message = message
                )
            }
        } catch (exception: Exception) {
            null
        }
    }

}