package com.portal.githubservices.data.base


import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeApiCall(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else if (response.errorBody() != null) {
            val res = response.errorBody()?.string()
            val errorResponse = convertErrorBody(res)
            NetworkResult.Error(
                message = errorResponse?.message
            )
        } else {
            NetworkResult.Error(
                message = response.message()
            )
        }
    } catch (e: HttpException) {
        NetworkResult.Error(
            message = e.message()
        )
    } catch (e: Throwable) {
        NetworkResult.Error(message = e.message)
    }
}


private fun convertErrorBody(res: String?): ErrorResponse? {
    return try {
        res.let {
            Gson().fromJson(it, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}