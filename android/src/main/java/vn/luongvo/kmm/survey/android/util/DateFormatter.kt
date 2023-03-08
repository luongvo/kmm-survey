package vn.luongvo.kmm.survey.android.util

import java.text.SimpleDateFormat
import java.util.*

interface DateFormatter {
    fun format(date: Date, dateFormat: String): String
}

class DateFormatterImpl : DateFormatter {

    override fun format(date: Date, dateFormat: String): String {
        return SimpleDateFormat(dateFormat, Locale.getDefault()).format(date)
    }
}
