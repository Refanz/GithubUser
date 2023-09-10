package com.refanzzzz.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponseFollowers(

	@field:SerializedName("GithubUserResponseFollowers")
	val githubUserResponseFollowers: List<GithubUserResponseItem>
)
