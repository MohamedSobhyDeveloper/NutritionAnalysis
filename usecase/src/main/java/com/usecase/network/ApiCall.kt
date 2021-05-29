package com.usecase.network


import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.usecase.network.Resource.Companion.failure
import com.usecase.network.Resource.Companion.failureConnect
import com.usecase.network.Resource.Companion.loading
import com.usecase.network.Resource.Companion.success
import com.usecase.network.Resource.Companion.unauthorized
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


fun <T> loadDataGeneric(request: suspend () -> Response<T>) = liveData(Dispatchers.IO) {
    emit(loading())
    try {
        val response = request.invoke()
        val rsm = if (response.isSuccessful) response.body() else {
            val type = object : TypeToken<T>() {}.type
            Gson().fromJson(response.errorBody()!!.charStream(), type) as T
        }

        if (response.isSuccessful && rsm != null)
            emit(success(rsm))
        else if (response.code() == 401 && rsm != null){
            unauthorized("")
         //   response.errorBody()?.string()?.let { Log.e("respoo", it) }
          // emit(failure( "Error"))
        }
        // response.errorBody()?.let { Log.e("respnddd", it.charStream().toString()) }
        // emit(logout())

        else  emit(failure( "Error"))
    } catch (e: Exception) {
        emit(failureConnect(e))


    }
}


