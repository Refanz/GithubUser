package com.refanzzzz.githubuser.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteGithubUser (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "username")
    var username: String? = null,

    @ColumnInfo(name = "userType")
    var userType: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable