package vn.luongvo.kmm.survey.android.util

import android.content.Context
import vn.luongvo.kmm.survey.android.R

fun Throwable.userReadableMessage(context: Context): String =
    message ?: context.getString(R.string.generic_error)
