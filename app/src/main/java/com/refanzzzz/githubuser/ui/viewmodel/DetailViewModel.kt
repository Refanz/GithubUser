package com.refanzzzz.githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseDetail
import com.refanzzzz.githubuser.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val TAG = "DetailViewModel"
    }

    private val _detailGithubUser = MutableLiveData<GithubUserResponseDetail>()
    val detailGithubUser: LiveData<GithubUserResponseDetail> = _detailGithubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getGithubUserDetail(name: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserGithubDetail(name)
        client.enqueue(object : Callback<GithubUserResponseDetail> {
            override fun onResponse(
                call: Call<GithubUserResponseDetail>,
                response: Response<GithubUserResponseDetail>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _detailGithubUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserResponseDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}