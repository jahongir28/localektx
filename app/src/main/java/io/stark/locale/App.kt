package io.stark.locale

import android.app.Application
import io.stark.locale.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/** Created by Jahongir on 04/02/2021.*/
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}