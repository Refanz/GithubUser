package com.refanzzzz.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser

@Dao
interface FavoriteGithubUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteUser(user: FavoriteGithubUser)

    @Update
    fun updateFavoriteUser(user: FavoriteGithubUser)

    @Delete
    fun deleteFavoriteUser(user: FavoriteGithubUser)

    @Query("SELECT * FROM favoritegithubuser ORDER BY id ASC")
    fun getAllFavoriteUser(): LiveData<List<FavoriteGithubUser>>

    @Query("SELECT * FROM favoritegithubuser WHERE username=:username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteGithubUser>

}