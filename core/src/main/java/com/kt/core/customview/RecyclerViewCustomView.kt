package com.kt.core.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksi.interactive.hamzetwasl.utlities.toStringOrEmpty
import com.kt.core.R
import com.wang.avi.AVLoadingIndicatorView

/**
 * Created by sobhy.
 */

class RecyclerViewCustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

  var next:String=""
    var listview: RecyclerView
    var progress: AVLoadingIndicatorView
    var errormessageTv: TextView
    var btnRetry: Button
    var tv_no_data:TextView
    var retry_layout: LinearLayout
    var llManager: LinearLayoutManager
    private var onLoadMore: (() -> Unit)? = null

    fun setonLoadMore(onLoadMore: () -> Unit) {
        this.onLoadMore = onLoadMore
    }
    fun setNextPage(nxtPage:String){
        next=nxtPage
    }
    fun getNextPage(): String {
        return next.toStringOrEmpty()
    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_custom_view, this, true)
        listview = view.findViewById(R.id.public_recycler)
        tv_no_data= view.findViewById(R.id.tvNoDat)
        progress = view.findViewById(R.id.aviLoading)
        errormessageTv = view.findViewById(R.id.retry_tv)
        btnRetry = view.findViewById(R.id.retry_btn)
        retry_layout = view.findViewById(R.id.retry_layout)
        llManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        listview.layoutManager = llManager
        setRecyclerViewScrollListener()
    }
    fun showNoDat(){
        tv_no_data.visibility=View.VISIBLE
    }
    fun hideNoDat(){
        tv_no_data.visibility=View.GONE
    }


    private fun setRecyclerViewScrollListener() {
        listview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = llManager.itemCount
                val lastVisibleItemPosition: Int = llManager.findLastVisibleItemPosition()
                if (progress.visibility == View.GONE && totalItemCount-1 == lastVisibleItemPosition ) {
                  Log.e("loadmore","yasss")
                    onLoadMore?.invoke()
                }
            }
        })
    }

    fun setRecOrientation(orientation: Int) {
        llManager = LinearLayoutManager(context, orientation, false)
        listview.layoutManager = llManager

    }

    fun setRecOrientationHorizental() {
        setRecOrientation(RecyclerView.HORIZONTAL)
    }

    fun showProgress(show: Boolean) {
        if (show) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    fun showError(show: Boolean) {
        if (show) {
            retry_layout.visibility = View.VISIBLE
        } else {
            retry_layout.visibility = View.GONE

        }
    }

    fun setErrorMessage(message: String) {

        errormessageTv.text = message

    }


}