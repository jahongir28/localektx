package io.stark.locale

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.stark.locale.databinding.ActivityMainBinding
import io.stark.localektx.LocaleKtx
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val localeKtx: LocaleKtx by inject()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        applyOverrideConfiguration(Configuration())
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {
        val newConfig = localeKtx.wrapConfiguration(overrideConfiguration)
        super.applyOverrideConfiguration(newConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}