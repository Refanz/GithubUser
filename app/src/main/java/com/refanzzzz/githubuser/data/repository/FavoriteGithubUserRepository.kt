package com.refanzzzz.githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser
import com.refanzzzz.githubuser.data.local.room.FavoriteGithubUserDao
import com.refanzzzz.githubuser.data.local.room.FavoriteGithubUserDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteGithubUserRepository(application: Application) {
    private val favoriteGithubUserDao: FavoriteGithubUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteGithubUserDatabase.getDatabase(application)
        favoriteGithubUserDao = db.githubUserDao()
    }

    fun getAllFavoriteGithubUser(): LiveData<List<FavoriteGithubUser>> = favoriteGithubUserDao.getAllFavoriteUser()

    fun getFavoriteGithubUserByUsername(username: String): LiveData<FavoriteGithubUser> = favoriteGithubUserDao.getFavoriteUserByUsername(username)

    fun insertFavoriteGithubUser(user: FavoriteGithubUser) {
        executorService.execute {
            favoriteGithubUserDao.insertFavoriteUser(user)
        }
    }

    fun deleteFavoriteGithubUser(user: FavoriteGithubUser) {
        executorService.execute {
            favoriteGithubUserDao.deleteFavoriteUser(user)
        }
    }

    fun updateFavoriteGithubUser(user: FavoriteGithubUser) {
        executorService.execute {
            favoriteGithubUserDao.updateFavoriteUser(user)
        }
    }



}