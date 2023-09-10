package com.refanzzzz.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponseSearch(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val items: List<GithubUserResponseItem>
)
