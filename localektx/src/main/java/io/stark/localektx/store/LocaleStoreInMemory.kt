package io.stark.localektx.store

import java.util.*

/** Created by Jahongir on 04/02/2021.*/
/**
 * Implementation of [LocaleStore] that keeps data in memory.
 *
 * Useful for cases like instrumentation tests, where you don't want to persist any changes
 * to the application locale.
 */
class LocaleStoreInMemory : LocaleStore {

    private var locale: Locale = Locale.getDefault()
    private var isFollowingSystemLocale = false

    override fun getLocale(): Locale = locale

    override fun persistLocale(locale: Locale) {
        this.locale = locale
    }

    override fun setFollowSystemLocale(value: Boolean) {
        isFollowingSystemLocale = value
    }

    override fun isFollowingSystemLocale(): Boolean {
        return isFollowingSystemLocale
    }
}