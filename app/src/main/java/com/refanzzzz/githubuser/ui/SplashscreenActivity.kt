package com.refanzzzz.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.data.local.datastore.SettingPreferences
import com.refanzzzz.githubuser.data.local.datastore.dataStore
import com.refanzzzz.githubuser.databinding.ActivitySplashscreenBinding
import com.refanzzzz.githubuser.ui.viewmodel.SettingViewModel
import com.refanzzzz.githubuser.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashscreenActivity : AppCompatActivity() {

    private var _activitySplashscreenBinding: ActivitySplashscreenBinding? = null
    private val binding get() = _activitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        val text = getString(R.string.splashscreen_text)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.getThemeSettings().observe(this@SplashscreenActivity) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Glide.with(this).load(R.drawable.ic_github_light).into(binding!!.ivSplashscreen)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Glide.with(this).load(R.drawable.ic_github).into(binding!!.ivSplashscreen)
            }
        }

        lifecycleScope.launch(Dispatchers.Default) {
            for (i in 0..text.length) {
                delay(70)

                withContext(Dispatchers.Main) {
                    if (i == text.length) {
                        val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        binding?.tvSplachscreen?.append(text[i].toString())
                    }
                }

            }
        }
    }

    private fun init() {
        _activitySplashscreenBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activitySplashscreenBinding = null
    }
}