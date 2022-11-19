package co.proexe.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

object Utils {

    fun Fragment.showTost(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}