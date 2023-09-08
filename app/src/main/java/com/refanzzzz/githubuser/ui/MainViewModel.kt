package com.refanzzzz.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refanzzzz.githubuser.data.response.GithubUserItems
import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.data.response.GithubUserSearchResponse
import com.refanzzzz.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _userGithub = MutableLiveData<List<GithubUserResponseItem>>()
    val userGithub: LiveData<List<GithubUserResponseItem>> = _userGithub

    private val _userGithubSearch = MutableLiveData<List<GithubUserItems>>()
    val userGithubSearch: LiveData<List<GithubUserItems>> = _userGithubSearch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllGithubUser() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getAllUserGithub()
        client.enqueue(object: Callback<List<GithubUserResponseItem>> {

            override fun onResponse(
                call: Call<List<GithubUserResponseItem>>,
                response: Response<List<GithubUserResponseItem>>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _userGithub.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<GithubUserResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getUserGithubByName(query: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserGithubByName(query)
        client.enqueue(object: Callback<GithubUserSearchResponse> {
            override fun onResponse(
                call: Call<GithubUserSearchResponse>,
                response: Response<GithubUserSearchResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _userGithubSearch.value = response.body()?.items
                    Log.d(TAG, _userGithubSearch.value.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<GithubUserSearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, t.message.toString())
            }

        })
    }

}