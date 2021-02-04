package io.stark.localektx.utils

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import java.util.*

/** Created by Jahongir on 04/02/2021.*/

@SuppressLint("ObsoleteSdkInt")
internal fun Configuration.setCurrentLocale(locale: Locale) {
    // Configuration.setLocale is added after 17 and Configuration.locale is deprecated
    // after 24
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setLocale(locale)
    } else {
        @Suppress("DEPRECATION")
        this.locale = locale
    }
}

@SuppressLint("ObsoleteSdkInt")
internal fun Configuration.updateLayoutDirection(locale: Locale) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setLayoutDirection(locale)
    }
}


internal val Configuration.currentLocale: Locale?
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (locales.isEmpty){
            null
        }else{
            locales[0]
        }
    } else {
        @Suppress("DEPRECATION")
        locale
    }

internal fun Configuration.checkLocale(): Locale? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (this.locales.isEmpty) return null
        this.locales.get(0)
    } else {
        @Suppress("DEPRECATION")
        this.locale
    }
}