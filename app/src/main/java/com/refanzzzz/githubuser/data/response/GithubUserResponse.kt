package com.refanzzzz.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(

    @field:SerializedName("GithubUserResponse")
    val githubUserResponse: List<GithubUserResponseItem>
)


