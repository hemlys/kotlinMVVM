package com.view.mvvmdemo.core.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.view.mvvmdemo.R

class LoadingIndicatorDialog(context: Context?) : AlertDialog(context) {
    private val mMessageView: TextView
    private var count = 0
    override fun setMessage(message: CharSequence) {
        mMessageView.text = message
    }

    init {
        val view: View = LayoutInflater.from(getContext()).inflate(R.layout.progress_avld, null)
        mMessageView = view.findViewById<View>(R.id.message) as TextView
        mMessageView.text = getContext().resources.getString(R.string.prompt1)
        Handler().postDelayed({
            count++
        }, 1000)
        setView(view)
    }
}