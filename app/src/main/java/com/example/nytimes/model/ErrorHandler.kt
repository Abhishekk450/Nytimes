package com.example.nytimes.model

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.nytimes.R
import com.google.android.material.snackbar.Snackbar

class ErrorHandler(private val mActivity: Activity, private val mView: View, private val mObject: Any?) {

    fun handleErrorResponse() {
        if (mObject == null) {
            return
        }

        if (mObject is NetworkInterceptor.NoConnectivityException) {
            SnackBarFactory.createSnackBar(
                mActivity,
                mView,
                mActivity.resources.getString(R.string.no_internet)
            )
        } else if (mObject is Throwable)
            SnackBarFactory.createSnackBar(mActivity, mView, mObject.localizedMessage)

    }

    object SnackBarFactory {

        fun createSnackBar(context: Context?, view: View, message: String): Snackbar {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            val sbView = snackbar.view
            val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
            textView.setTextColor(context?.resources!!.getColor(R.color.colorWhite))
            snackbar.show()
            return snackbar

        }
    }
}