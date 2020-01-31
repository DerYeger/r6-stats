package eu.yeger.r6_stats

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class R6StatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Timber.plant(Timber.DebugTree())
    }
}
