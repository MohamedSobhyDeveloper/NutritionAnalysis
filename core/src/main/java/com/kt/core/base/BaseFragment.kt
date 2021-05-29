package com.kt.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kt.core.databinding.FragmentBaseBinding
import com.kt.core.utlities.Loading


import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

   val binding by lazy { initBinding() }
  private val baseBinding by lazy { FragmentBaseBinding.inflate(layoutInflater) }
  private val loadingDialog by lazy { activity?.let { Loading(it) } }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    baseBinding.flContainer.apply {
      removeAllViews()
      addView(binding.root)
    }

    return baseBinding.root
  }




  @Suppress("UNCHECKED_CAST")
  private fun initBinding(): VB {
    val superclass = javaClass.genericSuperclass as ParameterizedType
    val method = (superclass.actualTypeArguments[0] as Class<Any>)
        .getDeclaredMethod("inflate", LayoutInflater::class.java)
    return method.invoke(null, layoutInflater) as VB
  }


}