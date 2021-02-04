package io.stark.localektx.impl

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import io.stark.localektx.utils.checkLocale
import io.stark.localektx.utils.currentLocale
import io.stark.localektx.utils.setCurrentLocale
import io.stark.localektx.utils.updateLayoutDirection
import java.util.*

/** Created by Jahongir on 04/02/2021.*/
class UpdateLocaleImpl:UpdateLocale {
    override fun wrapContext(context: Context?, locale: Locale): Context? {
        if (context == null) return null

        val systemLocale = context.resources.configuration.currentLocale

        if (systemLocale == locale) return context

        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = resources.configuration

        configuration.setCurrentLocale(locale)
        configuration.updateLayoutDirection(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration)
        } else {
            @Suppress("DEPRECATION")
            resources.updateConfiguration(configuration, context.resources.displayMetrics)
            context
        }
    }

    override fun wrapConfiguration(config: Configuration, locale: Locale): Configuration {
        val configLocale = config.checkLocale()
        if (configLocale != null) {
            return config
        }
        config.setCurrentLocale(locale)
        return config
    }
}