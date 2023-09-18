package com.refanzzzz.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.refanzzzz.githubuser.data.local.datastore.SettingPreferences
import com.refanzzzz.githubuser.data.local.datastore.dataStore
import com.refanzzzz.githubuser.databinding.ActivitySettingBinding
import com.refanzzzz.githubuser.ui.viewmodel.SettingViewModel
import com.refanzzzz.githubuser.ui.viewmodel.ViewModelFactory

class SettingActivity : AppCompatActivity() {

    private var _activitySettingBinding: ActivitySettingBinding? = null
    private val binding get() = _activitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.swNightMode?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.swNightMode?.isChecked = false
            }
        }

        with(binding!!) {
            appBarSetting.topBarApp.title = "Settings"
            appBarSetting.topBarApp.setOnClickListener {
                val mainIntent = Intent(this@SettingActivity, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
            swNightMode.setOnCheckedChangeListener { _, isChecked ->
                settingsViewModel.saveThemeSetting(isChecked)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activitySettingBinding = null
    }
}