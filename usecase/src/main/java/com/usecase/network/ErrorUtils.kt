package com.usecase.network

import com.usecase.network.RestClient.apiService
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


/**
 * Muhammad Altohamy
 * eng.gazzar.1985@gmail.com
 * 24/01/2021
 */
/*
class ErrorUtils {
    fun parseError(response: Response<*>): String? {
       val retrofit: Retrofit

        val conversorDeErro = retrofit
            .responseBodyConverter<String>(String()::class.java, arrayOfNulls(0))
        var errorResponce: String? = null
        try {
            if (response.errorBody() != null) {
                errorResponce = conversorDeErro.convert(response.errorBody()!!)
            }
        } catch (e: IOException) {
            return "ErrorResponce()"
        } finally {
            return errorResponce
        }
    }
}*/
