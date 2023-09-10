package com.refanzzzz.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponseItem(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("type")
    val type: String
)