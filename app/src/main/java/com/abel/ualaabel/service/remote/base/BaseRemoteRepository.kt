package com.abel.ualaabel.service.remote.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


abstract class BaseRemoteRepository {

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }


    suspend fun <T> getResult(call: suspend () -> Response<T>): T? {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Log.e("lista bodiiiii",body.toString())

                    return body

                }

            }
        } catch (e: Exception) {
            Log.e("lista", e.message.toString())
        }
        return null
    }

    fun <T> error(message: String) {
        Log.e("lista", message.toString())

    }

}