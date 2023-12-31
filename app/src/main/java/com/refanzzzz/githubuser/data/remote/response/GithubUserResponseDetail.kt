package com.refanzzzz.githubuser.data.remote.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponseDetail(

	@field:SerializedName("twitter_username")
	val twitterUsername: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("blog")
	val blog: String,

	@field:SerializedName("company")
	val company: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("email")
	val email: Any,

	@field:SerializedName("public_gists")
	val publicGists: Int,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: String,
)
