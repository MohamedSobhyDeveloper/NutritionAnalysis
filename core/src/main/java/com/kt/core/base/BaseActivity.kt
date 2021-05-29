package com.kt.core.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kt.core.R
import com.kt.core.databinding.ActivityBaseBinding
import com.kt.core.utlities.DialogRetry
import com.kt.core.utlities.Loading
import com.kt.core.utlities.extensions.hideKeyboard
import com.usecase.network.*
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

     val binding by lazy { initBinding() }
    private val baseBinding by lazy { ActivityBaseBinding.inflate(layoutInflater) }
    private val loadingDialog by lazy { Loading(this) }
    private var progressDialog: Dialog? = null
    

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        setContent()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initBinding(): VB {
        val superclass = javaClass.genericSuperclass as ParameterizedType
        val method = (superclass.actualTypeArguments[0] as Class<Any>)
            .getDeclaredMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }

    private fun setContent() {
        baseBinding.flContainer.addView(binding.root)
        setContentView(baseBinding.root)
    }

    override fun onPause() {
        hideKeyboard(currentFocus)
        super.onPause()
    }





    private fun showProgressDialog() {

        if (progressDialog == null) {

            progressDialog = Loading(this)

        }
        progressDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }

    }

    private fun hideProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }


    }

     fun showHideDialog(show: Boolean) {
        if (show) {
            Log.e("show", "progress")
            showProgressDialog()
        } else {
            Log.e("hide", "progress")
            hideProgressDialog()
        }

    }

    fun <T> callApi(it: Resource<T>, onRetry: () -> Unit): Boolean {
        var isSucess: Boolean = false
        when (it.status) {
            LOADING -> showHideDialog(true)
            SUCCESS -> {
                showHideDialog(false)
                isSucess = true
            }
            ERRORNet -> {
                showHideDialog(false)
             //==========//
             val e=it.exception
             val msg= e?.let { it1 -> getErrorMsg(it1) }
                if(e is IOException){

                  DialogRetry(getString(R.string.no_connection),msg?:"Error"){onRetry.invoke()}
                }else{
                    DialogRetry(getString(R.string.error),msg?:"Error"){onRetry.invoke()}
                }
                //======================//
            }
            ERROR -> {
                showHideDialog(false)
                DialogRetry(getString(R.string.error),it.message?:"Error"){onRetry.invoke()}
            }
            LogIn -> {
                showHideDialog(false)

            }
            serverMsg -> {
                Toast.makeText(this, it.message?:"Error", Toast.LENGTH_SHORT).show()
                showHideDialog(false)
            }
        }
        return isSucess
    }




    fun getErrorMsg(e: Exception) = when (e) {

        is HttpException -> {
            val body = e.response()?.errorBody()
            body?.let { Log.e("error", it.string()) }
            ""
        }
        is IOException ->
            // Timeout
           getString(R.string.network_check_connection)

        is RuntimeException -> // Unexpected Json response from server
            //  getStringByLocal(R.string.network_unexpected_response)
           getString(R.string.network_unexpected_response)

        else -> // Other error
            // getStringByLocal(R.string.network_server_unreachable)
           getString(R.string.network_server_unreachable)

    }
}