package io.stark.localektx.impl

import android.content.Context
import android.content.res.Configuration
import java.util.*

/** Created by Jahongir on 04/02/2021.*/
interface UpdateLocale {
    fun wrapContext(context: Context?, locale: Locale): Context?
    fun wrapConfiguration(config: Configuration, locale: Locale): Configuration
}