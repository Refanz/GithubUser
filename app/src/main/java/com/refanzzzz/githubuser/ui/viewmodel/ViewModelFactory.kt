package com.refanzzzz.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.refanzzzz.githubuser.data.local.datastore.SettingPreferences

class ViewModelFactory: ViewModelProvider.NewInstanceFactory {

    private var mApplication:Application? = null
    private var pref:SettingPreferences? = null
     constructor(mApplication: Application) {
         this.mApplication = mApplication
     }

    constructor(pref: SettingPreferences) {
        this.pref = pref
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE  = ViewModelFactory(application)
                }
            }

            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteGithubUserViewModel::class.java)) {
            return FavoriteGithubUserViewModel(mApplication!!) as T
        } else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref!!) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}