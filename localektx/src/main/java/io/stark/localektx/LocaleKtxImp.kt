package io.stark.localektx

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import io.stark.localektx.impl.UpdateLocale
import io.stark.localektx.store.LocaleStore
import java.util.*

/**
 * LocaleKtx is a tool to manage your application locale and language.
 *
 * Once you set a desired locale using [setLocale] method, LocaleKtx will enforce your application
 * to provide correctly localized data via [Resources] class.
 */
internal class LocaleKtxImp internal constructor(private val store: LocaleStore,
                                                 private val delegate: UpdateLocale) : LocaleKtx {

    //internal var systemLocale: Locale = defaultLocale
    private val defaultLocale: Locale = Locale.getDefault()

    /**
     * the active [Locale].
     */
    override val locale: Locale
        get() = store.getLocale()

    /**
     * A language code which is a part of the active [Locale].
     */
    override val lang: String
        get() = locale.language

    override val systemLocale: Locale
        get() = defaultLocale

    /**
     * Creates and sets a [Locale] using language, country and variant information.
     *
     * See the [Locale] class description for more information about valid language, country
     * and variant values.
     */
    override fun setLocale(language: String, country: String, variant: String) {
        setLocale(Locale(language, country, variant))
    }


    /**
     * Sets a [Locale] which will be used to localize all data coming from [Resources] class.
     *
     * <p>Note that you need to update all already fetched locale-based data manually.
     * [LocaleKtx] is not responsible for that.
     *
     * <p>Note that any call to [setLocale] stops following the system locale and resets
     * [isFollowingSystemLocale] setting.
     */

    override fun setLocale(locale: Locale, keepCounty: Boolean) {
        val newLocale = if (keepCounty && !defaultLocale.country.isNullOrBlank()) {
            Locale(locale.language, defaultLocale.country, defaultLocale.variant)
        } else locale

        store.setFollowSystemLocale(false)
        store.persistLocale(newLocale)
    }


    /**
     * Applies the system locale and starts following it whenever it changes.
     */
    override fun setFollowSystemLocale() {
        store.setFollowSystemLocale(true)
        store.persistLocale(defaultLocale)
    }

    /**
     * Indicates whether the system locale is currently applied.
     */
    override fun isFollowingSystemLocale() = store.isFollowingSystemLocale()

    override fun wrapContext(baseContext: Context?): Context? {
        val locale = getPersistLocale()
        return delegate.wrapContext(context = baseContext, locale = locale)
    }

    override fun wrapConfiguration(config: Configuration): Configuration {
        val locale = getPersistLocale()
        return delegate.wrapConfiguration(config = config, locale = locale)
    }

    private fun getPersistLocale(): Locale {
        return if (store.isFollowingSystemLocale()) {
            defaultLocale
        } else store.getLocale()
    }
}
