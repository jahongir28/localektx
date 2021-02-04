package io.stark.localektx

import android.content.Context
import android.content.res.Configuration
import io.stark.localektx.impl.UpdateLocaleImpl
import io.stark.localektx.store.LocaleStore
import io.stark.localektx.store.LocaleStorePreference
import java.util.*

/** Created by Jahongir on 04/02/2021.*/
interface LocaleKtx {

    fun setLocale(language: String, country: String = "", variant: String = "")
    fun setLocale(locale: Locale, keepCounty: Boolean = true)
    val locale: Locale
    val lang: String
    val systemLocale: Locale

    fun setFollowSystemLocale()
    fun isFollowingSystemLocale(): Boolean

    fun wrapContext(baseContext: Context?): Context?

    fun wrapConfiguration(config: Configuration): Configuration

    companion object {

        private lateinit var instance: LocaleKtx

        /**
         * Returns the global instance of [LocaleKtx] created via init method.
         *
         * @throws IllegalStateException if it was not initialized properly.
         */
        @JvmStatic
        fun getInstance(): LocaleKtx {
            check(::instance.isInitialized) { "LocaleKtx should be initialized first" }
            return instance
        }

        /**
         * Creates and sets up the global instance using a provided language and the default store.
         */
        @JvmStatic
        fun init(context: Context, defaultLanguage: String): LocaleKtx {
            return init(context, Locale(defaultLanguage))
        }

        /**
         * Creates and sets up the global instance using a provided locale and the default store.
         */
        @JvmStatic
        @JvmOverloads
        fun init(context: Context, defaultLocale: Locale = Locale.getDefault()): LocaleKtx {
            return init(LocaleStorePreference(context, defaultLocale))
        }

        /**
         * Creates and sets up the global instance.
         *
         * This method must be called before any calls to [LocaleKtx] and may only be called once.
         */
        @JvmStatic
        fun init(store: LocaleStore): LocaleKtx {
            if (::instance.isInitialized) return instance
            val localeKtx = LocaleKtxImp(store, UpdateLocaleImpl())
            instance = localeKtx
            return instance
        }
    }
}