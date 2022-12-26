package vn.luongvo.kmm.survey.android.util

import android.content.Context
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.extension.showToast

fun Throwable.userReadableMessage(context: Context): String =
    message ?: context.getString(R.string.generic_error)

fun Throwable.showToast(context: Context) =
    context.showToast(userReadableMessage(context))
