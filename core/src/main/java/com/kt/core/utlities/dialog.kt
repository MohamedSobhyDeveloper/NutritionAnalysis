package com.kt.core.utlities

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.kt.core.R





fun Context.DialogRetry(title: String, desc: String = "", onRetry: () -> Unit) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.custom_dialog_no_connection)

    val dtvCancel = dialog.findViewById(R.id.cancel) as TextView
    dtvCancel.visibility = View.VISIBLE
    val dtvRetry = dialog.findViewById(R.id.retry) as TextView
    val dTitle = dialog.findViewById(R.id.title) as TextView
    val derrorMsg = dialog.findViewById(R.id.errorMsg) as TextView
    dTitle.text = title
    derrorMsg.text = desc
    dtvRetry.setOnClickListener {
        onRetry.invoke()
        dialog.dismiss()
    }
    dtvCancel.setOnClickListener {

        dialog.dismiss()
    }
    val window: Window? = dialog.window
    window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    dialog.show()
}
