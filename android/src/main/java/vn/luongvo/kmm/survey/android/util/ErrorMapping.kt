package vn.luongvo.kmm.survey.android.util

import android.content.Context
import android.widget.Toast
import vn.luongvo.kmm.survey.android.R

fun Throwable.userReadableMessage(context: Context): String =
    message ?: context.getString(R.string.generic_error)

fun Throwable.showToast(context: Context) =
    showToast(context, userReadableMessage(context))

fun showToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
