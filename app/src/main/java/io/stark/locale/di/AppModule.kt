package io.stark.locale.di

import io.stark.localektx.createLocaleKtx
import org.koin.dsl.module

/** Created by Jahongir on 04/02/2021.*/
val appModule = module {
    single { createLocaleKtx(context = get()) }
}