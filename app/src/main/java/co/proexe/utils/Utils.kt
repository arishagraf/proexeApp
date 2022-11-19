package co.proexe.utils

import android.content.Context
import android.text.format.DateUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun Fragment.showTost(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun Context.getTime(time: String): String {
        val dateFormat: DateFormat =
            SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH)
        val expiry: Date = dateFormat.parse(time)
        return DateUtils.formatDateTime(this, expiry.time, DateUtils.FORMAT_SHOW_TIME)
    }
}