package com.refanzzzz.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser
import com.refanzzzz.githubuser.data.repository.FavoriteGithubUserRepository

class FavoriteGithubUserViewModel(application: Application): ViewModel() {
    private val favoriteGithubUser: FavoriteGithubUserRepository = FavoriteGithubUserRepository(application)

    fun insertFavoriteGithubUser(user: FavoriteGithubUser) {
        favoriteGithubUser.insertFavoriteGithubUser(user)
    }

    fun updateFavoriteGithubUser(user: FavoriteGithubUser) {
        favoriteGithubUser.updateFavoriteGithubUser(user)
    }

    fun deleteFavoriteGithubUser(user: FavoriteGithubUser) {
        favoriteGithubUser.deleteFavoriteGithubUser(user)
    }

    fun getAllFavoriteGithubUser(): LiveData<List<FavoriteGithubUser>> = favoriteGithubUser.getAllFavoriteGithubUser()

    fun getFavoriteGithubUserByUsername(username: String): LiveData<FavoriteGithubUser> = favoriteGithubUser.getFavoriteGithubUserByUsername(username)



}