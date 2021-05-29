package com.kt.core.customview

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.kt.core.R

/**
 * Created by mo on 1/25/2018.
 */
class CVEditText(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    //var title: String=""
    var titleView: TextView? = null
    lateinit var edtId: EditText
    var isPassword: Boolean? = null
    fun getTitle(): String {
        return titleView?.text.toString()
    }

    fun getText(): String {
        return edtId.text.toString()
    }

    fun setTitle(title: String) {
        titleView?.text = title

    }


    // Setup views
    private fun init(
        title: String,
        hintedt: String,
        drawableReigt: Int,
        drawableEditBg: Int,
        isPassword: Boolean?,type:String
    ) {
        edtId = findViewById<View>(R.id.edt) as EditText
        edtId!!.hint = hintedt
       // edtId!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableReigt, 0)
        //edt.setBackgroundResource(drawableEditBg);
        if (isPassword!!) {
            Log.e("pasw", "true")
        }
        titleView =
            findViewById<View>(R.id.label) as TextView
        if (title == "") {
            titleView!!.visibility = View.GONE
        } else {
            titleView!!.text = title
        }
        if(type.equals("email")){
            edtId.inputType= InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        }
        else if(type.equals("phone")){
            edtId.inputType=  InputType.TYPE_CLASS_PHONE
        }
        else if(type.equals("password")){
            edtId.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        edtId.setSelection(0)


    }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context)
            .inflate(R.layout.customview, this, true)
        var title: String="dddd"
        var type:String=""
        var hintEdt: String=""
        val drawableReigt: Int
        val drawableEditBg: Int
        val a = context.theme
            .obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0)
        try {
            if(a.getString(R.styleable.CustomView_customViewTitle)!=null){
                title = a.getString(R.styleable.CustomView_customViewTitle)!!
                }

            if(a.getString(R.styleable.CustomView_customViewType)!=null){
                type= a.getString(R.styleable.CustomView_customViewType)!!

            }
            if( a.getString(R.styleable.CustomView_customViewedtHint)!=null){
                hintEdt = a.getString(R.styleable.CustomView_customViewedtHint)!!

            }

           drawableReigt = a.getResourceId(
                R.styleable.CustomView_customViewedtDrwableRight,
                R.drawable.ic_minus
            )
            drawableEditBg = a.getResourceId(
                R.styleable.CustomView_customViewedtDrwableEditBackground,
                R.drawable.cv_et_bg
            )
            isPassword =
                a.getBoolean(R.styleable.CustomView_customViewIsPassword, false)

        } finally {
            a.recycle()
        }

        // Throw an exception if required attributes are not set

        init(title, hintEdt, drawableReigt, drawableEditBg, isPassword,type)
    }
}