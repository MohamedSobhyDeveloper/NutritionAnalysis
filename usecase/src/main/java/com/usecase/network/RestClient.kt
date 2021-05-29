@file:Suppress("DEPRECATION")

package com.usecase.network



import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestClient {

  private const val BASE_URL = "https://api.edamam.com/api"

  val apiService: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build().create(ApiService::class.java)
  }

  private val httpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(headerInterceptor())
        .addInterceptor(loggingInterceptor())
        .build()
  }

  private fun headerInterceptor(): Interceptor {
      //todo add app version dynamic token apitoken
      return Interceptor {
          val original = it.request()
              val request = original.newBuilder()
                  .header("Platform", "android")
                  .header("Accept", "application/json")
                  .header("Content-Type", "application/json")

                  .build()

              it.proceed(request)

      }
  }
  private fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = if (true) BODY else BODY
    }
  }
}