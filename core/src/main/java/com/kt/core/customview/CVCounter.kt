package com.kt.core.customview

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.kt.core.R

/**
 * Created by mo on 1/25/2018.
 */
class CVCounter(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    //var title: String=""
    var tv_counter: TextView? = null
    var img_decrement: ImageView? = null
    var img_increment: ImageView? = null

    // Setup views
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_counter, this, true)
        tv_counter = view.findViewById<View>(R.id.tv_counter) as TextView
        img_decrement = view.findViewById<ImageView>(R.id.img_decrement) as ImageView
        img_decrement?.setOnClickListener { decrement() }
        img_increment = view.findViewById<ImageView>(R.id.img_increment) as ImageView
        img_increment?.setOnClickListener { increment() }

    }

    fun getCount(): String {
        return tv_counter.toString()
    }

    private fun increment() {
        var count: Int = tv_counter?.text.toString().toInt() + 1
        tv_counter?.text = count.toString()
    }

    private fun decrement() {
        if (!tv_counter?.text.toString().equals("1")) {
            var count: Int = tv_counter?.text.toString().toInt() - 1
            tv_counter?.text = count.toString()
        } else {
            //  Toast.makeText(context, "اقل عدد 1", Toast.LENGTH_LONG).show()

        }

    }


}