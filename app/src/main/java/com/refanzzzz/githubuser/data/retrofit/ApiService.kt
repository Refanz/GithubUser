package com.refanzzzz.githubuser.data.retrofit

import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.data.response.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getAllUserGithub(): Call<List<GithubUserResponseItem>>

    @GET("users")
    fun getUserGithubByName(@Query("q") q: String): Call<GithubUserSearchResponse>
}