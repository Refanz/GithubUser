package com.refanzzzz.githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseItem
import com.refanzzzz.githubuser.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFollViewModel : ViewModel() {

    companion object {
        const val TAG = "UserFollViewModel"
    }

    private val _listUserGithubFollowers = MutableLiveData<List<GithubUserResponseItem>>()
    val listUserGithubFollowers: LiveData<List<GithubUserResponseItem>> = _listUserGithubFollowers

    private val _listUserGithubFollowing = MutableLiveData<List<GithubUserResponseItem>>()
    val listUserGithubFollowing: LiveData<List<GithubUserResponseItem>> = _listUserGithubFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUserGithubFollowers(name: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserGithubFollowers(name)
        client.enqueue(object : Callback<List<GithubUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GithubUserResponseItem>>,
                response: Response<List<GithubUserResponseItem>>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listUserGithubFollowers.value = response.body()
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

    fun getUserGithubFollowing(name: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserGithubFollowing(name)
        client.enqueue(object: Callback<List<GithubUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GithubUserResponseItem>>,
                response: Response<List<GithubUserResponseItem>>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listUserGithubFollowing.value = response.body()
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



}