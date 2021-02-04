package io.stark.localektx

import android.content.Context
import io.stark.localektx.impl.UpdateLocaleImpl
import io.stark.localektx.store.LocaleStore
import io.stark.localektx.store.LocaleStorePreference
import java.util.*

/** Created by Jahongir on 04/02/2021.*/

fun createLocaleKtx(context: Context): LocaleKtx {
    return createLocaleKtx(context = context, defaultLocale = Locale.getDefault())
}

fun createLocaleKtx(context: Context, defaultLanguage: String): LocaleKtx {
    return createLocaleKtx(context = context, defaultLocale = Locale(defaultLanguage))
}

fun createLocaleKtx(context: Context, defaultLocale: Locale = Locale.getDefault()): LocaleKtx {
    return createLocaleKtx(store = LocaleStorePreference(context, defaultLocale))
}

fun createLocaleKtx(store: LocaleStore): LocaleKtx {
    return LocaleKtxImp(store = store, delegate = UpdateLocaleImpl())
}