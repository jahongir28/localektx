package io.stark.locale.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import io.stark.locale.common.BindingFragment
import io.stark.locale.databinding.MainFragmentBinding
import io.stark.locale.utils.LocaleVariants
import io.stark.localektx.LocaleKtx
import io.stark.localektx.utils.RecreateHelper
import org.koin.android.ext.android.inject
import java.util.*

class MainFragment : BindingFragment<MainFragmentBinding>() {

    private val localeKtx: LocaleKtx by inject()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding {
        return MainFragmentBinding.inflate(inflater, container, false)
    }

    override fun setupViews() = with(binding) {
        english.setOnClickListener {
            onChangeLocale(LocaleVariants.English)
        }
        russian.setOnClickListener {
            onChangeLocale(LocaleVariants.Russian)
        }
        turkish.setOnClickListener {
            onChangeLocale(LocaleVariants.Turkish)
        }
    }

    private fun onChangeLocale(locale: Locale) {
        if (localeKtx.locale != locale) {
            localeKtx.setLocale(locale = locale, keepCounty = false)
            RecreateHelper.recreate(activity)
        }
    }

    fun changeLocale(locale: Locale) {
        localeKtx.setLocale(locale = locale)
    }
}