package io.stark.localektx.store

import java.util.Locale

/** Created by Jahongir on 04/02/2021.*/
interface LocaleStore {
    fun getLocale(): Locale
    fun persistLocale(locale: Locale)
    fun setFollowSystemLocale(value: Boolean)
    fun isFollowingSystemLocale(): Boolean
}