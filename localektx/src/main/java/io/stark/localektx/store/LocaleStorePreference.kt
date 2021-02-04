package io.stark.localektx.store

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.json.JSONObject
import java.util.*

/** Created by Jahongir on 04/02/2021.*/
/**
 * Default implementation of [LocaleStore] using [SharedPreferences].
 */
class LocaleStorePreference @JvmOverloads constructor(context: Context, private val defaultLocale: Locale = Locale.getDefault()) : LocaleStore {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(DEFAULT_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    override fun getLocale(): Locale {
        // there is no predefined way to serialize/deserialize Locale object
        val localeJson = prefs.getString(LANGUAGE_KEY, null)
        return if (!localeJson.isNullOrBlank()) {
            val json = JSONObject(localeJson)
            val language = json.getString(LANGUAGE_JSON_KEY)
            val country = json.getString(COUNTRY_JSON_KEY)
            val variant = json.getString(VARIANT_JSON_KEY)
            Locale(language, country, variant)
        } else {
            defaultLocale
        }
    }

    override fun persistLocale(locale: Locale) {
        val json = JSONObject().apply {
            put(LANGUAGE_JSON_KEY, locale.language)
            put(COUNTRY_JSON_KEY, locale.country)
            put(VARIANT_JSON_KEY, locale.variant)
        }
        prefs.edit {
            putString(LANGUAGE_KEY, json.toString())
        }
    }

    override fun setFollowSystemLocale(value: Boolean) {
        prefs.edit {
            putBoolean(FOLLOW_SYSTEM_LOCALE_KEY, value)
        }
    }

    override fun isFollowingSystemLocale(): Boolean {
        return prefs.getBoolean(FOLLOW_SYSTEM_LOCALE_KEY, false)
    }

    companion object {
        private const val LANGUAGE_KEY = "language_key"
        private const val FOLLOW_SYSTEM_LOCALE_KEY = "follow_system_locale_key"
        private const val DEFAULT_PREFERENCE_NAME = "locale_ktx_preference"
        private const val LANGUAGE_JSON_KEY = "language"
        private const val COUNTRY_JSON_KEY = "country"
        private const val VARIANT_JSON_KEY = "variant"
    }
}