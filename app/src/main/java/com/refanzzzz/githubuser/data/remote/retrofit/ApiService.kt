package com.refanzzzz.githubuser.data.remote.retrofit

import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseDetail
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseItem
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getAllUserGithub(): Call<List<GithubUserResponseItem>>

    @GET("search/users")
    fun getUserGithubByName(@Query("q") q: String): Call<GithubUserResponseSearch>

    @GET("users/{name}")
    fun getUserGithubDetail(@Path("name") name: String): Call<GithubUserResponseDetail>

    @GET("users/{name}/followers")
    fun getUserGithubFollowers(@Path("name") name: String): Call<List<GithubUserResponseItem>>

    @GET("users/{name}/following")
    fun getUserGithubFollowing(@Path("name") name: String): Call<List<GithubUserResponseItem>>
}