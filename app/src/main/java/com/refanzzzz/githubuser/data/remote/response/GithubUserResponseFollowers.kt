package com.refanzzzz.githubuser.data.remote.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponseFollowers(

	@field:SerializedName("GithubUserResponseFollowers")
	val githubUserResponseFollowers: List<GithubUserResponseItem>
)
