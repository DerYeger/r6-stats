package eu.yeger.r6_stats

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import androidx.fragment.app.Fragment

const val SHARED_PREFERENCES_NAME = "eu.yeger.r6-stats"

fun Fragment.saveToSharedPreferences(key: String, value: String?) {
    activity?.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)?.edit {
        putString(key, value)
    }
}

fun Fragment.fromSharedPreferences(key: String): String? {
    return activity?.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        ?.getString(key, null)
}

fun ratio(dividend: Number, divider: Number): Double = when (divider) {
    0 -> dividend.toDouble()
    else -> dividend.toDouble() / divider.toDouble()
}
